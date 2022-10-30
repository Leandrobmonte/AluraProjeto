package br.com.alura.comex.repository;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.dto.projecao.PedidoProjecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {

    @Query(value = "select c.nome as categoriaNome, " +
            " count(*) as quantidadeProdutos, " +
            " sum((i.preco_unitario * i.quantidade) - i.desconto) as montante " +
            " from itens_pedido i " +
            " inner join produtos p on i.produto_id = p.id " +
            " inner join categorias c on p.categoria_id = c.id " +
            " group by c.id", nativeQuery = true)
    List<PedidoProjecao> pedidosPorCategorias();

    Integer countByClienteId(Long clienteId);

}
