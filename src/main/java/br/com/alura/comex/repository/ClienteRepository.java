package br.com.alura.comex.repository;

import br.com.alura.comex.model.Cliente;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente,Long> {

}
