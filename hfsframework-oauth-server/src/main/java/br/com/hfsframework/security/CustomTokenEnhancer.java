package br.com.hfsframework.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import br.com.hfsframework.oauth.domain.User;
import br.com.hfsframework.oauth.service.IUserService;

public class CustomTokenEnhancer implements TokenEnhancer {

	@Autowired
	protected IUserService userService;
	
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>();
        
        additionalInfo.put("organization", "TRT-RJ");
        
		Optional<User> obj = userService.findByUsername(authentication.getName());
		
		if (obj.isPresent()) {
			additionalInfo.put("email", obj.get().getEmail());
			additionalInfo.put("url_photo", obj.get().getUrlPhoto());
		}
		
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
