package weekend.control;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import weekend.model.Cliente;
import weekend.service.ClienteService;
import java.io.Serializable;

@Named("clienteCT")
@ConversationScoped
public class ClienteController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5224515085221039239L;
	private Cliente cliente = new Cliente();
	private String consulta;
	private List<Cliente> clientes;
	
	@Inject	
	private ClienteService clienteService;
	@Inject
	private Conversation conversation;

	public ClienteController() {
		// TODO Auto-generated constructor stub
		
	}
	
	@PostConstruct
	public void init() {

		clientes = clienteService.listar();
	}
	
	public String novo() {		
		clientes = clienteService.listar();
		
		return "/cliente/index.xhtml?faces-redirect=true";
	}
	
	public void consultarPorCliente() {
		clientes = clienteService.listarPorNome(this.consulta);
	}
	
	public String ver(Long id) {	
		conversation.begin();
		cliente = clienteService.ver(id);				
		
		return "/cliente/ver.xhtml?faces-redirect=true";
	}
	
	public String editar(Long id) {		
		conversation.begin();
		cliente = clienteService.ver(id);
		
		return "/cliente/editar.xhtml";
	}
	
	public String remover(Long id) {	
		cliente.setId(id);
		clienteService.remover(cliente);
		
		return "/cliente/index.xhtml?faces-redirect=true";
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	
}
