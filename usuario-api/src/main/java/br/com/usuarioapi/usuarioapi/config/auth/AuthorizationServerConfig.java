package br.com.usuarioapi.usuarioapi.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import java.util.Collections;

import static br.com.usuarioapi.usuarioapi.modules.usuario.enums.EPermissao.ADMIN;
import static br.com.usuarioapi.usuarioapi.modules.usuario.enums.EPermissao.USER;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;
    @Autowired
    private CustomTokenEndpointAuthenticationFilter customTokenEndpointAuthenticationFilter;

    private static final String APPLICATION_CLIENT = "usuario-api-client";
    private static final String APPLICATION_SECRET = "usuario-api-secret";
    private static final String ROLE_APPLICATION = "ROLE_APPLICATION";
    private static final Integer TOKEN_VALIDITY_SECONDS = 0;
    @Value("${app-config.oauth-clients.produto-api.client}")
    private String produtoApiClient;
    @Value("${app-config.oauth-clients.produto-api.secret}")
    private String produtoApiSecret;
    @Value("${app-config.oauth-clients.venda-api.client}")
    private String vendaApiClient;
    @Value("${app-config.oauth-clients.venda-api.secret}")
    private String vendaApiSecret;

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
            .tokenKeyAccess("permitAll()")
            .checkTokenAccess("permitAll()")
            .passwordEncoder(passwordEncoder)
            .tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()")
            .allowFormAuthenticationForClients()
            .tokenEndpointAuthenticationFilters(Collections.singletonList(customTokenEndpointAuthenticationFilter));
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
            .inMemory()
            .withClient(APPLICATION_CLIENT)
            .secret(bcryptPasswordEncoder.encode(APPLICATION_SECRET))
            .authorizedGrantTypes("password")
            .authorities(ADMIN.name(), USER.name())
            .scopes("read", "write", "trust")

            .and()
            .withClient(produtoApiClient)
            .secret(bcryptPasswordEncoder.encode(produtoApiSecret))
            .authorizedGrantTypes("client_credentials")
            .scopes("produto-api")
            .authorities(ROLE_APPLICATION)

            .and()
            .withClient(vendaApiClient)
            .secret(bcryptPasswordEncoder.encode(vendaApiSecret))
            .authorizedGrantTypes("client_credentials")
            .scopes("venda-api")
            .authorities(ROLE_APPLICATION)

            .resourceIds("oauth2-resource")
            .accessTokenValiditySeconds(TOKEN_VALIDITY_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            .authenticationManager(authenticationManager)
            .tokenStore(tokenStore)
            .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
            .tokenEnhancer(new CustomTokenEnhancer())
            .authenticationManager(authenticationManager);
    }

    @Bean
    public CustomTokenEndpointAuthenticationFilter customFilter() {
        return new CustomTokenEndpointAuthenticationFilter();
    }
}
