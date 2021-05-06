package mary.accenture.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mary.accenture.model.Pedido;
import mary.accenture.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@PostMapping("/crear")
	public Pedido crear(@RequestBody Pedido pedido) {
		return service.crear(pedido);
	}
	
	@GetMapping("/ver")
	public Pedido ver(@PathParam(value = "id") int id) {
		return service.ver(id);
	}
	
	@PutMapping("/agregarProducto")
	public void agregar(@RequestBody Pedido pedido) {
		service.actualizarPedido(pedido);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id") int id) {
		service.eliminar(id);
	}

}
