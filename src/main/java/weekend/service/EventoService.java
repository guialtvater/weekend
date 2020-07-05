package weekend.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import weekend.model.Evento;

/**
 * Session Bean implementation class EventoService
 */
@Stateless
public class EventoService {
	@PersistenceContext
	private EntityManager em;    

    /**
     * Default constructor. 
     */
    public EventoService() {
        // TODO Auto-generated constructor stub
    }
    
    public void persistir(Evento evento) {
    	em.persist(evento);
	}
    
    public void atualizar(Evento evento) {
    	em.merge(evento);
	}
    
    
    public List<Evento> listar() {
    	return em
    	.createQuery("FROM Evento d", Evento.class)
    	.getResultList();
	}
    
    public Evento ver(Long id) {
    	return em.find(Evento.class, id);
    }

	public void remover(Evento evento) {
		evento = em.getReference(Evento.class, evento.getId());	
    	
    	em.remove(evento);
	}
	
	public List listarDadosEventos(){
		Query query = em.createNativeQuery(
		"SELECT d.id AS EVENTO_ID, d.nome AS EVENTO, " +
		"c.id AS CLIENTE_ID, c.nome AS CLIENTE," +
		"FROM evento d, cliente c" +
		"WHERE d.evento_id = c.id", "eventos");
		
		return query.getResultList();
	}
    

}
