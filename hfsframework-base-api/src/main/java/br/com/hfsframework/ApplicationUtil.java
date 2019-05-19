/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.hfsframework.security.model.UserAuthenticatedVO;

@Component
public final class ApplicationUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public ApplicationUtil() {
		super();
	}

	public HttpSession getSessao() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(); // true == permite criar		
	}	
	
	public void setUsuarioAutenticado(UserAuthenticatedVO usu){
		getSessao().setAttribute("usuarioAutenticado", usu);
	}
	
	public UserAuthenticatedVO getUsuarioAutenticado(){
		UserAuthenticatedVO usu = (UserAuthenticatedVO) getSessao().getAttribute("usuarioAutenticado");
		return usu;
	}
	
	public void removeUsuarioAutenticado(){
		getSessao().removeAttribute("usuarioAutenticado");
	}
	
}
