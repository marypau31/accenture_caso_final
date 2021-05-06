package mary.accenture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mary.accenture.dao.ClienteDao;
import mary.accenture.dao.PedidoDao;
import mary.accenture.dao.ProductoDao;
import mary.accenture.model.Cliente;
import mary.accenture.model.Pedido;
import mary.accenture.model.Producto;
import mary.accenture.service.ListarService;

@Service
public class ListarServiceImpl implements ListarService{

	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired 
	private ProductoDao ProductoDao;
	
	@Autowired 
	private PedidoDao pedidoDao;
	
	@Override
	public List<Cliente> cliente() {
		return clienteDao.findAll();
	}

	@Override
	public List<Producto> producto() {
		return ProductoDao.findAll();
	}

	@Override
	public List<Pedido> pedido() {
		return  pedidoDao.findAll();
	}

}
