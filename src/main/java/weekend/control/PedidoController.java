package weekend.control;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import weekend.model.Cliente;
import weekend.model.Pedido;
import weekend.service.PedidoService;

import java.io.Serializable;
import java.util.List;

@Named("pedidoCT")
@ConversationScoped
public class PedidoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5527096974353822628L;
	
	private Pedido pedido = new Pedido();
	private Cliente cliente = new Cliente();
	private List<Pedido> pedidos;
	
	@Inject
	private PedidoService pedidoService;
	@Inject 
	Conversation conversation;

	public PedidoController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		pedidos = pedidoService.listar();
	}
	
	public String novo() {		
		pedidoService.persistir(pedido);
		
		return "/pedido/index.xhtml?faces-redirect=true";
	}
	
	public String atualizar() {		
		pedidoService.atualizar(pedido);
		
		conversation.end();
		
		return "/pedido/index.xhtml?faces-redirect=true";
	}
	
	public String addCliente() {		
		if(conversation.isTransient()) {
			conversation.begin();
		}
		pedido = pedidoService.ver(pedido.getId());			
		
		return "/pedido/addclientes.xhtml";
	}
	
	public String novoCliente() {		
		pedido = pedidoService.ver(pedido.getId());		
		pedido.getCliente();
		
		pedidoService.atualizar(pedido);
		
		pedido = pedidoService.ver(pedido.getId());		
		
		return "/pedido/ver.xhtml?faces-redirect=true";
	}
	
	public String ver(Long id) {
		if(!conversation.isTransient()) {
			conversation.end();
		}
		conversation.begin();
		pedido = pedidoService.ver(id);				
		
		return "/pedido/ver.xhtml?faces-redirect=true";
	}
	
	public String editar(Long id) {		
		conversation.begin();
		pedido = pedidoService.ver(id);		
		return "/pedido/editar.xhtml";
	}
	
	public String remover(Long id) {	
		pedido.setId(id);
		pedidoService.remover(pedido);
		
		return "/pedido/index.xhtml?faces-redirect=true";
	}
	
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
