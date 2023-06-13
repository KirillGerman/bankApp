package ru.meshgroup.bankApplication.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;


class JwtProviderTest {

    @Test
    void ShouldParseJWTWhenSigned() {

        Date date = Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        var a =  Jwts.builder()
                .setClaims(new HashMap<>(){{
                    put("clientId","1");
                    put("ROLE","USER");
                }})
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, "jwtSecret")
                .compact();

        Claims claims = Jwts.parser().setSigningKey("jwtSecret").parseClaimsJws(a).getBody();

        assertEquals("1" , claims.get("clientId").toString());
        assertEquals("USER" , claims.get("ROLE").toString());

    }

}