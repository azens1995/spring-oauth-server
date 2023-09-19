package dev.eklak.springoautauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

// EnableAuthorizationServer instructs Spring Boot to enable configuration specific
// to the OAuth 2 authorization server
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${password}")
    private String password;

    @Value("${privatekey}")
    private String privateKey;

    @Value("${alias}")
    private String alias;

    // Injects the AuthorizationManager from the context
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(
        AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            .authenticationManager(authenticationManager)
            .tokenStore(tokenStore())
            .accessTokenConverter(jwtAccessTokenConverter());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws
        Exception {
        clients.inMemory()
            .withClient("client")
            .secret("secret")
            .authorizedGrantTypes("password", "refresh_token")
            .scopes("read")
            .and()
            // Add the client credentials used by resource server to retrieve public key
            .withClient("rclient")
            .secret("rsecret");
    }

    @Bean
    public TokenStore tokenStore() {
        // Creates a token store with an access token converter associated to it
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        var converter = new JwtAccessTokenConverter();
        // Creates a KeyStoreFactory object to retrieve the private key file from the classpath
        KeyStoreKeyFactory keyStoreKeyFactory =
            new KeyStoreKeyFactory(new ClassPathResource(privateKey), password.toCharArray());
        // Use the keystoreKeyFactory object to retrieve the key pair
        // and sets the key pair to the JWTAccessTokenConverter object
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(alias));
        return converter;
    }

    @Override
    public void configure(
        AuthorizationServerSecurityConfigurer security) {
        // Configures the auth server to expose the endpoints for the public key
        // for any requests authenticated with valid client credentials
        security.tokenKeyAccess
            ("isAuthenticated()");
    }
}
