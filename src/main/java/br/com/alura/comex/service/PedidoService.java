package br.com.alura.comex.service;

import br.com.alura.comex.config.BussinesException;
import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.model.TipoDesconto;
import br.com.alura.comex.model.TipoDescontoItem;
import br.com.alura.comex.model.dto.input.ItemPedidoDto;
import br.com.alura.comex.model.dto.input.PedidoInputDto;
import br.com.alura.comex.model.dto.output.PedidoNovoOutputDto;
import br.com.alura.comex.model.dto.projecao.PedidoProjecao;
import br.com.alura.comex.repository.PedidoRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteService clienteService;
    private final ItemDePedidosService itemDePedidosService;
    private static final Integer MINIMO_DE_PEDIDO_PARA_FIDELIDADE = 5;
    private static final Integer MINIMO_DE_QUANTIDADE_ITEMS_PRODUTOS_PARA_DESCONTO = 10;
    private static final Double DEZ_PORCENTO = 0.1;
    private static final Double CINCO_PORCENTO = 0.05;




    public PedidoService(PedidoRepository pedidoRepository,
                         ProdutoRepository produtoRepository,
                         ClienteService clienteService,
                         ItemDePedidosService itemDePedidosService) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteService = clienteService;
        this.itemDePedidosService = itemDePedidosService;
    }

    public List<PedidoProjecao> pedidosPorCategoria() {
        List<PedidoProjecao> list = pedidoRepository.pedidosPorCategorias();
        return list;
    }

    @Transactional
    public PedidoNovoOutputDto cadastrar(PedidoInputDto pedidoInputDto) {
        try {
            //valida se existe estoque para produtos listados.
            List<Produto> produtos = this.validaQuantidadeDeEstoqueProduto(pedidoInputDto);
            Pedido pedido = this.validaDescontosDoPedido(pedidoInputDto);
            List<ItemDePedido> listaItemDePedido = this.emitirItemPedido(pedidoInputDto, produtos, pedido);
            pedidoRepository.save(pedido);
            for (ItemDePedido itemPedido : listaItemDePedido) {
                itemDePedidosService.salvar(itemPedido);
            }
            return new PedidoNovoOutputDto(pedido);
        } catch (BussinesException e) {
            throw new BussinesException(e.getMessage());
        }
    }

    private List<Produto> validaQuantidadeDeEstoqueProduto(PedidoInputDto pedidoInputDto){
        List<Optional<Produto>> produtosOptional = new ArrayList<>();
        List<Produto> produtos = new ArrayList<>();
        for(ItemPedidoDto itemPedidoDto : pedidoInputDto.getItemPedidos()){
            produtosOptional.add(produtoRepository.findById(itemPedidoDto.getProdutoId()));
        }
        produtosOptional.stream().forEach(y -> {
            Produto produto = y.get();
            //TODO: verificar se a quatindade solicitada é maior que a de estoque do produto
            if(y.get().getQuantidadeEstoque() == 0){
                //TODO: pedir acima do estoque
                throw new BussinesException(String.format("Produto %s sem estoque.", produto.getNome()));
            }
            produtos.add(produto);
        });
        return produtos;
    }

    private Pedido validaDescontosDoPedido(PedidoInputDto pedidoInputDto){
        Pedido pedido = new Pedido();
        Boolean descontoPorQuantidade = pedidoInputDto.getItemPedidos().stream().anyMatch(prodQtd -> prodQtd.getQuantidadeVendida() > MINIMO_DE_QUANTIDADE_ITEMS_PRODUTOS_PARA_DESCONTO);
        Boolean descontoPorFidelidade = pedidoRepository.countByClienteId(pedidoInputDto.getClienteId()) >= MINIMO_DE_PEDIDO_PARA_FIDELIDADE;

        pedido.setDesconto(BigDecimal.valueOf(0.0));
        pedido.setCliente(clienteService.clientePorId(pedidoInputDto.getClienteId()).get());
        pedido.setTipoDesconto(TipoDesconto.NENHUM);

        //Valida descontos
        if(descontoPorQuantidade && !descontoPorFidelidade){
            pedido.setDesconto(BigDecimal.valueOf(DEZ_PORCENTO));
        }
        if(descontoPorFidelidade && !descontoPorQuantidade){
            pedido.setDesconto(BigDecimal.valueOf(CINCO_PORCENTO));
            pedido.setTipoDesconto(TipoDesconto.FIDELIDADE);
        }
        if(descontoPorFidelidade && descontoPorQuantidade){
            pedido.setDesconto(BigDecimal.valueOf(CINCO_PORCENTO).add(BigDecimal.valueOf(DEZ_PORCENTO)));
            pedido.setTipoDesconto(TipoDesconto.FIDELIDADE);
        }
        return pedido;
    }

    private List<ItemDePedido> emitirItemPedido(PedidoInputDto pedidoInputDto, List<Produto> produtos, Pedido pedido){

        List<ItemDePedido> listaItemDePedido = new ArrayList<>();
        Boolean descontoPorQuantidade = pedidoInputDto.getItemPedidos().stream().anyMatch(prodQtd -> prodQtd.getQuantidadeVendida() > MINIMO_DE_QUANTIDADE_ITEMS_PRODUTOS_PARA_DESCONTO);
        for (ItemPedidoDto ipd : pedidoInputDto.getItemPedidos()) {
            for (Produto p : produtos) {
                if (ipd.getProdutoId() == p.getId()) {
                    ItemDePedido itemDePedido = new ItemDePedido(ipd.getQuantidadeVendida(), p);
                    itemDePedido.setTipoDesconto(TipoDescontoItem.NENHUM);
                    itemDePedido.setPedido(pedido);
                    if (descontoPorQuantidade) {
                        itemDePedido.setTipoDesconto(TipoDescontoItem.QUANTIDADE);
                    }
                    BigDecimal porcentagem = pedido.getDesconto();
                    itemDePedido.setDesconto(itemDePedido.getValorTotalItem().multiply(porcentagem));
                    listaItemDePedido.add(itemDePedido);
                }
            }
        }
        return listaItemDePedido;
    }

}
