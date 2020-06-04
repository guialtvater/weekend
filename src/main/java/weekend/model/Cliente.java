package weekend.model;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import weekend.model.Produto;


/**
 * Entity implementation class for Entity: Cliente
 *
 */
@Entity
@Table(name="cliente")
@NamedQueries({
	@NamedQuery(name = "Cliente.consultarTodos", query = "SELECT a FROM cliente a"),
	@NamedQuery(name = "Cliente.consultarPorId", query = "SELECT a FROM cliente a WHERE a.id= :id"),
	@NamedQuery(name = "Cliente.consultarPorNome", query = "SELECT a FROM cliente a WHERE a.nome = :nome"),
	@NamedQuery(name = "Cliente.consultarPeloRA", query = "SELECT a FROM cliente a WHERE a.ra = :ra")
})
@NamedNativeQuery(
    name="clientesResumido",
    query = "SELECT id, ra, nome, sobrenome " +
        "FROM cliente" +
        "ORDER BY nome, sobrenome",
    resultClass=Cliente.class)
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "CLIENTE_SEQ")
    @SequenceGenerator(name = "CLIENTE_SEQ", sequenceName = "cliente_seq")    
	private Long id;
	@Column(nullable=false)
	private @NotNull String nome;
	@Column(nullable=false)
	private @NotNull String sobrenome;
	@Column(nullable=false)
	private @NotNull Date dataNascimento;
	@Column(nullable=false)
	private @NotNull String email;
	@Column(nullable=false)
	@NotNull Double saldo;

	public Cliente() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public String getSobrenome() {
		return this.sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public String getNomeCompleto() {
		return this.nome + " " + this.sobrenome;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sobrenome == null) ? 0 : sobrenome.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}
	

	    public static String calcularIdade(LocalDate dataNascimento, LocalDate dataAtual) {
	        if ((dataNascimento != null) && (dataAtual != null)) {
	            int idade =  Period.between(dataNascimento, dataAtual).getYears();
	            if( idade >= 18) {
	            	return("Usuário pode consumir bebida alcóolica");
	            }
	            else {
	            	return("Usuário menor de idade");
	            }
	        } else {
	        	return("Data de nascimento inválida");
	        }
	    }
}