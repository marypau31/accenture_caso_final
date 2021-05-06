package mary.accenture.service;

import java.util.List;

import mary.accenture.model.Cliente;
import mary.accenture.model.Pedido;
import mary.accenture.model.Producto;

public interface ListarService {

	List<Cliente> cliente();
	
	List<Producto> producto();
	
	List<Pedido> pedido();
}
