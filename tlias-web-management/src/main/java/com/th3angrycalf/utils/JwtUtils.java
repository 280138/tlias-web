package com.th3angrycalf.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static final String signKey = "SVRIRUlNQQ==";
    private static final Long expire = 43200000L;

    /**
     * 生成JWT令牌
     * @return
     */
    public static String generateJwt(Map<String,Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims) //添加自定义数据 payloady有效载荷
                .signWith(SignatureAlgorithm.HS256, signKey) //algorithm签名算法和密钥
                .setExpiration(new Date(System.currentTimeMillis() + expire)) //设置令牌过期时间
                .compact(); //生成令牌
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey) //设置密钥
                .parseClaimsJws(jwt) //解析令牌
                .getBody(); //获取负载
        return claims;
    }
}
