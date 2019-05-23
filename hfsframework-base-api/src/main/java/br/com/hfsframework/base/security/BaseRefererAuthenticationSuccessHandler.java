package br.com.hfsframework.base.security;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class BaseRefererAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    public BaseRefererAuthenticationSuccessHandler() {
        super();
        setUseReferer(true);
    }

}
