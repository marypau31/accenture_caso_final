package mary.accenture.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mary.accenture.model.Cliente;

@Repository
public interface ClienteDao extends JpaRepository<Cliente, Integer>{

}
