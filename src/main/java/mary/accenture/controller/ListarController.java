package mary.accenture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mary.accenture.model.Cliente;
import mary.accenture.model.Pedido;
import mary.accenture.model.Producto;
import mary.accenture.service.ListarService;

@RestController
@RequestMapping("/listar")
public class ListarController {
	
	@Autowired
	private ListarService service;
	
	@GetMapping("/clientes")
	public List<Cliente> listarClientes() {
		return service.cliente();
	}
	
	@GetMapping("/productos")
	public List<Producto> listarProductos() {
		return service.producto();
	}
	
	@GetMapping("/pedidos")
	public List<Pedido> listarPedidos() {
		return service.pedido();
	}

}
