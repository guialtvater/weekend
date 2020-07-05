package weekend.control;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import weekend.model.Cliente;
import weekend.model.Evento;
import weekend.service.EventoService;

import java.io.Serializable;
import java.util.List;

@Named("eventoCT")
@ConversationScoped
public class EventoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5527096974353822628L;
	
	private Evento evento = new Evento();
	private Cliente cliente = new Cliente();
	private List<Evento> eventos;
	
	@Inject
	private EventoService eventoService;
	@Inject 
	Conversation conversation;

	public EventoController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		eventos = eventoService.listar();
	}
	
	public String novo() {		
		eventoService.persistir(evento);
		
		return "/evento/index.xhtml?faces-redirect=true";
	}
	
	public String atualizar() {		
		eventoService.atualizar(evento);
		
		conversation.end();
		
		return "/evento/index.xhtml?faces-redirect=true";
	}
	
	public String addCliente() {		
		if(conversation.isTransient()) {
			conversation.begin();
		}
		evento = eventoService.ver(evento.getId());			
		
		return "/evento/addclientes.xhtml";
	}
	
	public String novoCliente() {		
		evento = eventoService.ver(evento.getId());		
		evento.getClientes().add(cliente);
		
		eventoService.atualizar(evento);
		
		evento = eventoService.ver(evento.getId());		
		
		return "/evento/ver.xhtml?faces-redirect=true";
	}
	
	public String ver(Long id) {
		if(!conversation.isTransient()) {
			conversation.end();
		}
		conversation.begin();
		evento = eventoService.ver(id);				
		
		return "/evento/ver.xhtml?faces-redirect=true";
	}
	
	public String editar(Long id) {		
		conversation.begin();
		evento = eventoService.ver(id);		
		return "/evento/editar.xhtml";
	}
	
	public String remover(Long id) {	
		evento.setId(id);
		eventoService.remover(evento);
		
		return "/evento/index.xhtml?faces-redirect=true";
	}
	
	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
