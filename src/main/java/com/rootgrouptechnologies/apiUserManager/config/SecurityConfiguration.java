package com.rootgrouptechnologies.apiUserManager.config;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequestEntityConverter;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequestEntityConverter;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import java.util.Objects;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .anyRequest().authenticated();

        http
                .oauth2Login()
                .defaultSuccessUrl("http://localhost:8082/api/v1/users/details", true)
                .tokenEndpoint().accessTokenResponseClient(accessTokenResponseClient())
                .and()
                .userInfoEndpoint().userService(userService());

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
                return OAuth2UserAgentUtils.withUserAgent(Objects.requireNonNull(super.convert(oauth2Request)));
            }
        });

        return client;
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> userService() {
        DefaultOAuth2UserService service = new DefaultOAuth2UserService();

        service.setRequestEntityConverter(new OAuth2UserRequestEntityConverter() {
            @Override
            public RequestEntity<?> convert(OAuth2UserRequest userRequest) {
                return OAuth2UserAgentUtils.withUserAgent(Objects.requireNonNull(super.convert(userRequest)));
            }
        });

        return service;
    }
}
