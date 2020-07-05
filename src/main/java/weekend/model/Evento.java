package weekend.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class Evento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	   
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "EVENTO_SEQ")
    @SequenceGenerator(name = "EVENTO_SEQ", sequenceName = "evento_seq")    
	private Long id;
	@Column(nullable=false)
	private @NotNull String nome;
	@Column(nullable=false)
	private @NotNull String local;
	@Column(nullable=false)
	private @NotNull String endereco;
	@Column(nullable=false)
	private @NotNull Double valorEntrada;
	@OneToMany(mappedBy = "evento")
	private List<Produto> cardapio;
	@OneToMany(mappedBy = "evento")
	private List<Cliente> clientes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Double getValorEntrada() {
		return valorEntrada;
	}
	public void setValorEntrada(Double valorEntrada) {
		this.valorEntrada = valorEntrada;
	}
	public List<Produto> getCardapio() {
		return cardapio;
	}
	public void setCardapio(List<Produto> cardapio) {
		this.cardapio = cardapio;
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public static String pagamentoEntrada(Cliente cliente, Evento evento) {
		Double pagamento = cliente.getSaldo() - evento.getValorEntrada();
		if(pagamento >= 0) {
			cliente.setSaldo(cliente.saldo - evento.valorEntrada);
			return("Pedido realizado com sucesso!");
		} else {
			return("Saldo insuficiente!");
		}
	}
	
	
}
