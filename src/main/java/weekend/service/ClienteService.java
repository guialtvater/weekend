package weekend.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import weekend.model.Cliente;

/**
 * Session Bean implementation class ClienteService
 */
@Stateless
public class ClienteService {

	@PersistenceContext
	private EntityManager em;    
    
    public ClienteService() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Cliente> listarPorNome(String nome) {
    	TypedQuery<Cliente> query = em.createNamedQuery("Cliente.consultarPorNome", Cliente.class);	
    	query.setParameter("nome", nome);
    	return query.getResultList();
	}    
    
    public List<Cliente> listar() {
    	return em
    	//.createQuery("FROM Cliente a", Cliente.class)
    	.createNamedQuery("Cliente.consultarTodos", Cliente.class)		
    	.getResultList();
	}    
    
    public Cliente ver(Long id) {
    	return em.find(Cliente.class, id);
    }
    
    public void remover(Cliente cliente) {
    	cliente = em.getReference(Cliente.class, cliente.getId());
    	em.remove(cliente);
	}
    		
    
    public Cliente listarPeloRA() {
    	return em
    	.createNamedQuery("Cliente.consultarPeloRA", Cliente.class)		
    	.getSingleResult();
	}   
}