package mary.accenture.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Entity
@Table
@JsonInclude(value = Include.NON_ABSENT)
public class Pedido implements Serializable {
	
	private static final long serialVersionUID = 7912437718870727861L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Transient
	private List<Producto> productoList;
	
	@ManyToOne
	@JoinColumn(nullable = false, referencedColumnName = "id")
	private Cliente cliente;
	
	@Enumerated(EnumType.STRING)
	private EstadoPedido estado;
	
	private LocalDateTime tiempo;
	
	private double subtotal;

	private double iva;
	
	private double total;
	
	private double domicilio;
}
