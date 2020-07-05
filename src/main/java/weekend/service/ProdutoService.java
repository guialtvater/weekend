package weekend.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import weekend.model.Produto;

/**
 * Session Bean implementation class ProdutoService
 */
@Stateless
public class ProdutoService {
	@PersistenceContext
	private EntityManager em;    

    /**
     * Default constructor. 
     */
    public ProdutoService() {
        // TODO Auto-generated constructor stub
    }
    
    public void persistir(Produto produto) {
    	em.persist(produto);
	}
    
    public void atualizar(Produto produto) {
    	em.merge(produto);
	}
    
    
    public List<Produto> listar() {
    	return em
    	.createQuery("FROM Produto d", Produto.class)
    	.getResultList();
	}
    
    public Produto ver(Long id) {
    	return em.find(Produto.class, id);
    }

	public void remover(Produto produto) {
		produto = em.getReference(Produto.class, produto.getId());	
    	
    	em.remove(produto);
	}
	
	public List listarDadosProdutos(){
		Query query = em.createNativeQuery(
		"SELECT d.id AS EVENTO_ID, d.nome AS EVENTO, " +
		"c.id AS CLIENTE_ID, c.nome AS CLIENTE," +
		"FROM produto d, cliente c" +
		"WHERE d.produto_id = c.id", "produtos");
		
		return query.getResultList();
	}
    

}
