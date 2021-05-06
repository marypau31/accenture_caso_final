package mary.accenture.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mary.accenture.model.Pedido;
import mary.accenture.model.ProductoPorPedido;

@Repository
public interface ProductoPorPedidoDao extends JpaRepository<ProductoPorPedido, Integer>{

	List<ProductoPorPedido> findByPedido(Pedido pedido);
}
