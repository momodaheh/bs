package com.bs.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class tokenUtil {
    private static final String SECRET_KEY = "wzy";
    private static long time =1000 * 60 * 60 *24;  //有效期

    public static String createToken(String userId){
        Date now =new Date();
        Date expiryDate =new Date(now.getTime()+time);

        byte[] apiKeySecretBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        SecretKey key = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }

    public static boolean checkToken(String token) {
        try {
            byte[] apiKeySecretBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
            SecretKey key = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());

            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
