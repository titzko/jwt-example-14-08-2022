package com.example.backend.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final String SECRET = "secret";
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //the authentication manager gets called, which verfies the authentication attempt
    //if login succesfull successfulAuthentication() gets called by spring magic
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //if null -> https://stackoverflow.com/questions/39028179/spring-security-loadbyusernames-username-field-is-empty -> dont send request via json
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("Username is: {}", username);
        log.info("Password is: {}", password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    //this method is for sending the jwt when login is successfull
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        //can see in this method argument, there is already the response, so we can pass the token to the respone ;)
        //this method is called once login was successfull
        //using maven dependency auth0 java jwt -> helps us to generate the token and so on. check pom

        //user Object from Spring security here. Do not Mixup with AppUser from domain package
        User user = (User) authentication.getPrincipal();

        //from maven dependency
        Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes());
        String access_token = JWT.create()
                .withSubject(user.getUsername()) //subject defines a user uniquely, so could have also chosen the userid
                .withExpiresAt(new Date(System.currentTimeMillis() +10*60 * 1000)) //current time of system + 10min
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))//permissions or roles or w/e
                .sign(algorithm); //this is the access token

        String refresh_token = JWT.create()
                .withSubject(user.getUsername()) //subject defines a user uniquely, so could have also chosen the userid
                .withExpiresAt(new Date(System.currentTimeMillis() +30*60 * 1000)) //current time of system + 10min
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm); //this is the access token

        //response.setHeader("access_token", access_token);
        //response.setHeader("refresh_token", refresh_token);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        tokens.put("user_name", user.getUsername());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),tokens);
    }


}
