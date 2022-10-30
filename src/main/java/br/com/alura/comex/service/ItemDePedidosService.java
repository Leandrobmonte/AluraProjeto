package br.com.alura.comex.service;

import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.repository.ItemDePedidosRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemDePedidosService {

    private final ItemDePedidosRepository itemDePedidosRepository;

    public ItemDePedidosService(ItemDePedidosRepository itemDePedidosRepository) {
        this.itemDePedidosRepository = itemDePedidosRepository;
    }

    public ItemDePedido salvar(ItemDePedido itemDePedido){
        return itemDePedidosRepository.save(itemDePedido);
    }
}
