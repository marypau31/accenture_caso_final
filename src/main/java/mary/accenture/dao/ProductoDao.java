package mary.accenture.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mary.accenture.model.Producto;

@Repository
public interface ProductoDao extends JpaRepository<Producto, Integer>{

}
