package weekend.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import weekend.model.Cliente;
import weekend.model.Grupo;
import weekend.model.Usuario;

/**
 * Session Bean implementation class LoginService
 */
@Stateless
public class LoginService {

	@PersistenceContext
	private EntityManager em;   
	
    public LoginService() {
        // TODO Auto-generated constructor stub
    }
    
    public void persistir(Usuario usuario, Grupo grupo) {  
    	em.persist(usuario);
    	em.persist(grupo);
	}

}