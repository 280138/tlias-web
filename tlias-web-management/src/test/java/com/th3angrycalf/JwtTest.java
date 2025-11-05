package com.th3angrycalf;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    /**
     * 生成JWT - Jwts.builder()
     */
    @Test
    public void testGenerateJwt(){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("username","admin");

        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "aXRoZWltYQ==") //指定签名算法和密钥
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) //设置过期时间
                .addClaims(dataMap) //添加自定义数据
                .compact();//生成令牌
        System.out.println(jwt);
    }


    /**
     * 解析JWT - Jwts.parser()
     */
    @Test
    public void testParseJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3NjE5NzA3ODIsImlkIjoxLCJ1c2VybmFtZSI6ImFkbWluIn0._4i7CTqpCnIXuLhm2RyWz29UfoSAJCTCXbEzpUQmqxw";
        Claims claims = Jwts.parser()
                .setSigningKey("aXRoZWltYQ==")
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims);
    }
}
