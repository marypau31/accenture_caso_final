package mary.accenture.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mary.accenture.dao.PedidoDao;
import mary.accenture.dao.ProductoDao;
import mary.accenture.dao.ProductoPorPedidoDao;
import mary.accenture.model.EstadoPedido;
import mary.accenture.model.Pedido;
import mary.accenture.model.Producto;
import mary.accenture.model.ProductoPorPedido;
import mary.accenture.service.PedidoService;

@Service
@Transactional
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoDao pedidoDao;
	
	@Autowired 
	private ProductoPorPedidoDao productoPorPedidoDao;
	
	@Autowired
	private ProductoDao productoDao;
	

	@Override
	public Pedido crear(Pedido model) {
		model.setEstado(EstadoPedido.FACTURADO);
		model.setTiempo(LocalDateTime.now());
		
		List<Producto> productoList = new ArrayList<>();
		for (Producto producto : model.getProductoList()) {
			Producto productoOld = productoDao.findById(producto.getId()).orElse(null);
			productoOld.setCantidad(producto.getCantidad());
			productoList.add(productoOld);
		}
		model.setProductoList(productoList);
		
		model = calcularPedido(model);
		Pedido pedidonew = pedidoDao.save(model);
		pedidonew.setProductoList(model.getProductoList());
		agregarProducto(pedidonew);
		return pedidonew;
	}

	@Override
	public Pedido ver(int id) {
		Pedido pedido = pedidoDao.findById(id).orElse(null);
		List<ProductoPorPedido> productoPorPedidoList = productoPorPedidoDao.findByPedido(pedido);
		pedido.setProductoList(new ArrayList<Producto>());
		
		for (ProductoPorPedido productoPorPedido : productoPorPedidoList) {
			Producto producto = new Producto();
			producto.setId(productoPorPedido.getProducto().getId());
			producto.setNombre(productoPorPedido.getProducto().getNombre());
			producto.setPrecio(productoPorPedido.getProducto().getPrecio());
			producto.setCantidad(productoPorPedido.getCantidad());
			pedido.getProductoList().add(producto);
		}
		return pedido;
	}

	@Override
	public void agregarProducto(Pedido model) {
		for (Producto producto : model.getProductoList()) {
			ProductoPorPedido productoPorPedido = new ProductoPorPedido();
			productoPorPedido.setPedido(model);
			Producto productoOld = productoDao.findById(producto.getId()).orElse(null);
			productoPorPedido.setProducto(productoOld);
			productoPorPedido.setCantidad(producto.getCantidad());
			productoPorPedidoDao.save(productoPorPedido);
		}
	}
	
	@Override
	public void actualizarPedido(Pedido model) {
		Pedido pedido = pedidoDao.findById(model.getId()).orElse(null);
		model.setCliente(pedido.getCliente());
		model.setTiempo(pedido.getTiempo());
		model.setEstado(pedido.getEstado());
		if(calcularTiempo(pedido.getTiempo(), 5)) {
			
			//productos actuales
			List<ProductoPorPedido> productoPorPedidoList = productoPorPedidoDao.findByPedido(pedido);
			pedido.setProductoList(new ArrayList<Producto>());
			
			for (ProductoPorPedido productoPorPedido : productoPorPedidoList) {
				Producto producto = new Producto();
				producto.setId(productoPorPedido.getProducto().getId());
				producto.setNombre(productoPorPedido.getProducto().getNombre());
				producto.setPrecio(productoPorPedido.getProducto().getPrecio());
				producto.setCantidad(productoPorPedido.getCantidad());
				pedido.getProductoList().add(producto);
			}
			pedido = calcularPedido(pedido);
			
			//productos nuevos
			List<Producto> productoList = new ArrayList<>();
			for (Producto producto : model.getProductoList()) {
				Producto productoOld = productoDao.findById(producto.getId()).orElse(null);
				productoOld.setCantidad(producto.getCantidad());
				productoList.add(productoOld);
			}
			model.setProductoList(productoList);
			model = calcularPedido(model);
			
			if(model.getSubtotal() >= pedido.getSubtotal()) {
				for (ProductoPorPedido productoPorPedido : productoPorPedidoList) {
					productoPorPedidoDao.deleteById(productoPorPedido.getId());
				}
				pedidoDao.save(model);
				agregarProducto(model);
			}
			
		}
	}

	@Override
	public void eliminar(int id) {
		Pedido pedido = pedidoDao.findById(id).orElse(null);
		if(calcularTiempo(pedido.getTiempo(), 12)) {
			List<ProductoPorPedido> productoPorPedidoList = productoPorPedidoDao.findByPedido(pedido);
			for (ProductoPorPedido productoPorPedido : productoPorPedidoList) {
				productoPorPedidoDao.deleteById(productoPorPedido.getId());
			}
			pedidoDao.deleteById(id);
		} else {
			pedido.setEstado(EstadoPedido.CANCELADO);

			double subtotal = 0;
			double total = 0;
			List<ProductoPorPedido> productoPorPedidoList = productoPorPedidoDao.findByPedido(pedido);
			for (ProductoPorPedido producto : productoPorPedidoList) {
				subtotal += producto.getProducto().getPrecio()*producto.getCantidad();
			}
			subtotal = subtotal * 0.10;
			total = subtotal;
			pedido.setSubtotal(subtotal);
			pedido.setIva(0);
			pedido.setDomicilio(0);
			pedido.setTotal(total);
		}
	}
	
	private boolean calcularTiempo(LocalDateTime tiempoPedido, int horas) {
		LocalDateTime time = LocalDateTime.now();
		Duration duracion = Duration.between(tiempoPedido, time);
		return duracion.toHours() < horas;
	}
	
	private Pedido calcularPedido(Pedido pedido) {
		double subtotal = 0;
		double iva = 0;
		double domicilio = 0;
		double total = 0;
		for (Producto producto : pedido.getProductoList()) {
			subtotal += producto.getPrecio()*producto.getCantidad();
		}
		if(subtotal > 70000) iva = subtotal * 0.19;
		
		if(subtotal > 100000) domicilio = 0.0;
		else domicilio = 3000;

		total = subtotal + iva + domicilio;
		pedido.setSubtotal(subtotal);
		pedido.setIva(iva);
		pedido.setDomicilio(domicilio);
		pedido.setTotal(total);
		
		return pedido;
	}
}
