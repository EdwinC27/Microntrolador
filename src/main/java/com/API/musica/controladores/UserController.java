package com.API.musica.controladores;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.API.musica.componentes.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consulta.class);

    @Value("${keySecret}")
    public String key;

    @PostMapping("user")
    public User login(@RequestBody User consultaUser) {
        String token = getJWTToken(consultaUser.getUser());
        consultaUser.setToken(token);

        return consultaUser;
    }

    private String getJWTToken(String username) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("edwinAPI")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        key.getBytes()).compact();

        return token;
    }
}