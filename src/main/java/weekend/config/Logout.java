package weekend.config;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named @RequestScoped
public class Logout {
    @Inject
    private HttpServletRequest request;
    
    public String sair() throws ServletException {
        request.logout();
        request.getSession().invalidate();
        
        return "/index.xhtml?faces-redirect=true";
    }
}