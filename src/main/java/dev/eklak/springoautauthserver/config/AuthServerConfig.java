package dev.eklak.springoautauthserver.config;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;

// EnableAuthorizationServer instructs Spring Boot to enable configuration specific
// to the OAuth 2 authorization server
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    // Injects the AuthorizationManager from the context
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(
        AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

    // Setting up ClientDetailsService instance
    // Method 1
    /*@Override
    public void configure(ClientDetailsServiceConfigurer clients) throws
        Exception {
        // Creates an instance using the ClientDetailsService implementation
        var service = new InMemoryClientDetailsService();

        // Creates an instance of ClientDetails and set the required information
        var cd = new BaseClientDetails();
        cd.setClientId("client");
        cd.setClientSecret("secret");
        cd.setScope(List.of("read"));
        cd.setAuthorizedGrantTypes(List.of("password"));

        // Adds the client details to InMemoryClientDetailsService
        service.setClientDetailsStore(Map.of("client", cd));

        // Configure client details service for use by our authorization server
        clients.withClientDetails(service);
    }*/

    // Method 2
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws
        Exception {
        clients.inMemory()
            .withClient("client")
            .secret("secret")
            .authorizedGrantTypes("authorization_code")
            .scopes("read")
            .redirectUris("http://localhost:9090/home");
    }
}
