package weekend.model;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class Pedido implements Serializable {
	
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PEDIDO_SEQ")
    @SequenceGenerator(name = "PEDIDO_SEQ", sequenceName = "pedido_seq")    
	private Long id;
	@OneToOne(mappedBy = "pedido")
	private Cliente cliente;
	@OneToMany(mappedBy = "pedido")
	private static List<Produto> produtos;
	@Column(nullable=false)
	@NotNull
	public Double valor;
	
	public Pedido() {
		super();
		cliente = new Cliente();
		produtos = new ArrayList<Produto>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((produtos == null) ? 0 : produtos.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (produtos == null) {
			if (other.produtos != null)
				return false;
		} else if (!produtos.equals(other.produtos))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
	
	public void adicionarProduto(Pedido pedido, Produto produto) {
		pedido.produtos.add(produto);
	}
	
	public void valorPedido(Pedido pedido, Produto produto) {
		pedido.setValor(pedido.valor + produto.valor);
	}
	
	public static String pagamentoCliente(Cliente cliente, Pedido pedido) {
		Double pagamento = cliente.getSaldo() - pedido.getValor();
		if(pagamento >= 0) {
			cliente.setSaldo(cliente.saldo - pedido.valor);
			return("Pedido realizado com sucesso!");
		} else {
			return("Saldo insuficiente!");
		}
	}
}
