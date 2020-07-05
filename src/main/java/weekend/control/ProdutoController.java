package weekend.control;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import weekend.model.Cliente;
import weekend.model.Produto;
import weekend.service.ProdutoService;

import java.io.Serializable;
import java.util.List;

@Named("produtoCT")
@ConversationScoped
public class ProdutoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5527096974353822628L;
	
	private Produto produto = new Produto();
	private Cliente cliente = new Cliente();
	private List<Produto> produtos;
	
	@Inject
	private ProdutoService produtoService;
	@Inject 
	Conversation conversation;

	public ProdutoController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		produtos = produtoService.listar();
	}
	
	public String novo() {		
		produtoService.persistir(produto);
		
		return "/produto/index.xhtml?faces-redirect=true";
	}
	
	public String atualizar() {		
		produtoService.atualizar(produto);
		
		conversation.end();
		
		return "/produto/index.xhtml?faces-redirect=true";
	}
	
	public String addCliente() {		
		if(conversation.isTransient()) {
			conversation.begin();
		}
		produto = produtoService.ver(produto.getId());			
		
		return "/produto/addclientes.xhtml";
	}
	
	public String ver(Long id) {
		if(!conversation.isTransient()) {
			conversation.end();
		}
		conversation.begin();
		produto = produtoService.ver(id);				
		
		return "/produto/ver.xhtml?faces-redirect=true";
	}
	
	public String editar(Long id) {		
		conversation.begin();
		produto = produtoService.ver(id);		
		return "/produto/editar.xhtml";
	}
	
	public String remover(Long id) {	
		produto.setId(id);
		produtoService.remover(produto);
		
		return "/produto/index.xhtml?faces-redirect=true";
	}
	
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
