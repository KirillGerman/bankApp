package ru.meshgroup.bankApplication.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

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
                .signWith(SignatureAlgorithm.HS512, "meshgroup")
                .compact();

        Claims claims = Jwts.parser().setSigningKey("meshgroup").parseClaimsJws(a).getBody();

        assertEquals("1" , claims.get("clientId").toString());
        assertEquals("USER" , claims.get("ROLE").toString());

    }


    @Test
    void test(){
        assert  1 < 0;
    }

}