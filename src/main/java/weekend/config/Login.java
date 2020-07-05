package weekend.config;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import weekend.model.Grupo;
import weekend.model.Usuario;
import weekend.service.LoginService;

@Named @RequestScoped
public class Login {
    private Usuario usuario = new Usuario();
    @NotNull @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres.") 
    private String senha;

    @Inject
    private SecurityContext securityContext;
    @Inject
	private Pbkdf2PasswordHash passwordHash;
    @Inject
    private LoginService loginService;

    public void entrar() {
    	FacesContext facesContext = FacesContext.getCurrentInstance();
    	AuthenticationStatus resultado = autenticar();
        switch (resultado) {
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SEND_FAILURE:
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no login.", null));
                break;
            case SUCCESS:
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login realizado com sucesso", null));                
                break;
		default:
			break;
        }
    }
    private AuthenticationStatus autenticar() {
    	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    	
    	Credential credential = new UsernamePasswordCredential(
    			usuario.getNome(), new Password(usuario.getSenha()));
    	        AuthenticationStatus status = securityContext
    	          .authenticate(
	        		  (HttpServletRequest) externalContext.getRequest(),
	                  (HttpServletResponse) externalContext.getResponse(),
	                  AuthenticationParameters.withParams().credential(credential));
    	        
        return status;
    }
    
    public void cadastrar() {
    	
    	if (usuario.getSenha().compareTo(senha) == 0) {
			Map<String, String> parametros = new HashMap<>();
			parametros.put("Pbkdf2PasswordHash.Iterations", "4096");
			parametros.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA256");
			parametros.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
	        passwordHash.initialize(parametros);    
	        
	        usuario.setSenha(passwordHash.generate(usuario.getSenha().toCharArray()));
	        
	        Grupo grupo = new Grupo();
	                
	        grupo.setUsuario(usuario.getNome());
	        grupo.setPapel("USUARIO");
	        
	        boolean cadastrado = false;
	        
	        try {
	        	loginService.persistir(usuario, grupo);
	        	cadastrado = true;
			} catch (Exception e) {
				// TODO: handle exception
				FacesContext facesContext = FacesContext.getCurrentInstance();
	    		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no cadastro.", null));
				cadastrado = false;
			}
	        
	        if(cadastrado) {
	        	usuario.setSenha(senha);
	        	entrar();
	        }
    	} else {
    		FacesContext facesContext = FacesContext.getCurrentInstance();
    		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senhas não coincidem", null));
    	}
    	
    	
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}