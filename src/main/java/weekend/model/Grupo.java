package weekend.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Grupo
 *
 */
@Entity
@Table(name="grupo")
public class Grupo implements Serializable {

	private static final long serialVersionUID = 3275140507097174612L;

	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "GRUPO_SEQ")
    @SequenceGenerator(name = "GRUPO_SEQ", sequenceName = "grupo_seq")    
	private Long id;
	@Column(nullable=false)
	private @NotNull String usuario;
	@Column(nullable=false)
	private @NotNull String papel;
	
	public Grupo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((papel == null) ? 0 : papel.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Grupo other = (Grupo) obj;
		if (papel == null) {
			if (other.papel != null)
				return false;
		} else if (!papel.equals(other.papel))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	

}