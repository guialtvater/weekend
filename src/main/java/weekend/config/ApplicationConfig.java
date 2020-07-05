package weekend.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.sql.DataSource;

import org.glassfish.soteria.identitystores.annotation.Credentials;
import org.glassfish.soteria.identitystores.annotation.EmbeddedIdentityStoreDefinition;

/*@EmbeddedIdentityStoreDefinition({
    @Credentials(
        callerName = "admin@weeekend.com",
        password = "admin123",
        groups = { "USUARIO", "ADMIN" }
    ),
    @Credentials(
        callerName = "usuario@weekend.com",
        password = "usuario123",
        groups = { "USUARIO" }
    )
})*/

@DatabaseIdentityStoreDefinition (
	dataSourceLookup = "java:global/DataSourceName",
	callerQuery = "SELECT senha FROM usuario WHERE nome = ?",
	groupsQuery = "SELECT papel FROM grupo WHERE usuario = ?"
)


@CustomFormAuthenticationMechanismDefinition(
	    loginToContinue = @LoginToContinue(
	        loginPage = "/login.xhtml",
	        errorPage = ""
	    )
	)
@FacesConfig @ApplicationScoped @Startup @Singleton
public class ApplicationConfig {
	
	// Descomentar código abaixo para que o Java gere as configurações iniciais de contas
	
	/*
	@Resource(lookup="java:global/DataSourceName")	
    private DataSource dataSource;
	
	@Inject
	private Pbkdf2PasswordHash passwordHash;
	
	@PostConstruct
	public void init() {
		Map<String, String> parametros = new HashMap<>();
		parametros.put("Pbkdf2PasswordHash.Iterations", "4096");
		parametros.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA256");
		parametros.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
        passwordHash.initialize(parametros);
        
        adicionarUsuario("admin@weekend.com", passwordHash.generate("admin123".toCharArray()));
        adicionarGrupo("admin@weekend.com", "ADMIN");
        adicionarGrupo("admin@weekend.com", "USUARIO");
        
        adicionarUsuario("usuario@weekend.com", passwordHash.generate("usuario123".toCharArray()));
        adicionarGrupo("usuario@weekend.com", "USUARIO");
        
        
        
	}
	
	
	private void adicionarUsuario(String usuario, String senha) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
            		"insert into usuario (id, nome, senha) values (usuario_seq.nextval, ?, ?)")) {
                
            	statement.setString(1, usuario);
            	statement.setString(2, senha);
            	statement.executeUpdate();
            }
        } catch (SQLException sqlE) {
           throw new IllegalStateException(sqlE);
        }
    }
	
	private void adicionarGrupo(String usuario, String papel) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
            		"insert into grupo (id, usuario, papel) values (grupo_seq.nextval, ?, ?)")) {
            	
            	statement.setString(1, usuario);
            	statement.setString(2, papel);
                statement.executeUpdate();
            }
        } catch (SQLException sqlE) {
           throw new IllegalStateException(sqlE);
        }
    }*/
	

}
