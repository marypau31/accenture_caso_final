package mary.accenture.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class ProductoPorPedido implements Serializable {

	private static final long serialVersionUID = 1710746870798944936L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, referencedColumnName = "id")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(nullable = false, referencedColumnName = "id")
	private Producto producto;
	
	private int cantidad;

}
