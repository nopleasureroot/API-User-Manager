package com.rootgrouptechnologies.apiUserManager.security;

import com.rootgrouptechnologies.apiUserManager.security.service.OAuth2UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequestEntityConverter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import java.util.Objects;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    private final OAuth2UserServiceImpl oAuth2UserService;

    public SecurityConfiguration(OAuth2UserServiceImpl oAuth2UserService) {
        this.oAuth2UserService = oAuth2UserService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated();

        http
                .oauth2Login()
                .defaultSuccessUrl("http://localhost:8082/api/v1/users/details", true)
                .tokenEndpoint().accessTokenResponseClient(accessTokenResponseClient())
                .and()
                .userInfoEndpoint().userService(oAuth2UserService);

        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .logout()
                .invalidateHttpSession(true).clearAuthentication(true)
                .logoutSuccessUrl("http://localhost:8082/api/v1/login")
                .deleteCookies("U_SESSION").permitAll();
    }

    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
        DefaultAuthorizationCodeTokenResponseClient client = new DefaultAuthorizationCodeTokenResponseClient();

        client.setRequestEntityConverter(new OAuth2AuthorizationCodeGrantRequestEntityConverter() {
            @Override
            public RequestEntity<?> convert(OAuth2AuthorizationCodeGrantRequest oauth2Request) {
                return SecurityHelper.withUserAgent(Objects.requireNonNull(super.convert(oauth2Request)));
            }
        });

        return client;
    }

    static class SecurityHelper {
        private static final String DISCORD_BOT_USER_AGENT = "Root";

        static RequestEntity<?> withUserAgent(RequestEntity<?> request) {
            HttpHeaders headers = new HttpHeaders();
            headers.putAll(request.getHeaders());
            headers.add(HttpHeaders.USER_AGENT, DISCORD_BOT_USER_AGENT);

            return new RequestEntity<>(request.getBody(), headers, request.getMethod(), request.getUrl());
        }
    }

}
