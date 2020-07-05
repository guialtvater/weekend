package weekend.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import weekend.model.Pedido;

/**
 * Session Bean implementation class PedidoService
 */
@Stateless
public class PedidoService {
	@PersistenceContext
	private EntityManager em;    

    /**
     * Default constructor. 
     */
    public PedidoService() {
        // TODO Auto-generated constructor stub
    }
    
    public void persistir(Pedido pedido) {
    	em.persist(pedido);
	}
    
    public void atualizar(Pedido pedido) {
    	em.merge(pedido);
	}
    
    
    public List<Pedido> listar() {
    	return em
    	.createQuery("FROM Pedido d", Pedido.class)
    	.getResultList();
	}
    
    public Pedido ver(Long id) {
    	return em.find(Pedido.class, id);
    }

	public void remover(Pedido pedido) {
		pedido = em.getReference(Pedido.class, pedido.getId());	
    	
    	em.remove(pedido);
	}
	
	public List listarDadosPedidos(){
		Query query = em.createNativeQuery(
		"SELECT d.id AS EVENTO_ID, d.nome AS EVENTO, " +
		"c.id AS CLIENTE_ID, c.nome AS CLIENTE," +
		"FROM pedido d, cliente c" +
		"WHERE d.pedido_id = c.id", "pedidos");
		
		return query.getResultList();
	}
    

}
