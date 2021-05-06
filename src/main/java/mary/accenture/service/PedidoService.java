package mary.accenture.service;

import mary.accenture.model.Pedido;

public interface PedidoService {

	Pedido crear(Pedido model);
	
	Pedido ver(int id);
	
	void actualizarPedido(Pedido model);
	
	void agregarProducto(Pedido model);
	
	void eliminar(int id);
}
