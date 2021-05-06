package mary.accenture.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mary.accenture.model.Pedido;

@Repository
public interface PedidoDao extends JpaRepository<Pedido, Integer>{

}
