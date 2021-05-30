package com.vanhoang.demo.security;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vanhoang.demo.exceptions.authentication.JWTException;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class JwtToken {
    public static final Long TOKEN_EXPIRE = 600000L;
    String IIS = "JWT_PAYMENT";
    public String generateJWT(String key,Map payload) throws JsonProcessingException, InvalidKeyException, NoSuchAlgorithmException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Map header = new HashMap();
        header.put("typ","JWT");
        header.put("alg","HS256");
        String headerBase64 = Base64.getUrlEncoder().encodeToString(
                objectWriter.writeValueAsString(header)
                        .replace(" ","")
                        .replace("\n","")
                        .replace("\r","").getBytes(StandardCharsets.UTF_8));
        payload.put("iss",IIS);
        String payloadBase64 = Base64.getUrlEncoder().encodeToString(
                objectWriter.writeValueAsString(payload)
                        .replace(" ","")
                        .replace("\n","")
                        .replace("\r","").getBytes(StandardCharsets.UTF_8));
        String dataSignature = headerBase64+"."+payloadBase64;
        String signature = hmacSHA256(dataSignature,key);
        return dataSignature+"."+signature;
    }
    public Boolean verifyJWT(String token,String key) throws JWTException, InvalidKeyException, NoSuchAlgorithmException {
        List<String> strings = Arrays.asList(token.split("\\."));
        if (strings.size()<3) throw new JWTException(JWTException.TOKEN_MALFORMED,JWTException.TOKEN_MALFORMED);
        TokenModel tokenModel = null;
        try {
            tokenModel = decode(token);
        } catch (JsonProcessingException e) {
            throw new JWTException(JWTException.TOKEN_ISNOTJSON,JWTException.TOKEN_ISNOTJSON);
        }
        if (tokenModel.payload.get("exp")!=null){
            Date expire = new Date(Long.valueOf(String.valueOf(tokenModel.payload.get("exp"))));
            Date date = new Date();
            if (expire.getTime()<date.getTime()) throw new JWTException(JWTException.TOKEN_EXPIRE,JWTException.TOKEN_EXPIRE);
        }

        String dataSignature = strings.get(0)+"."+strings.get(1);
        String signature = hmacSHA256(dataSignature,key);
        if (!signature.equals(strings.get(2))) throw new JWTException(JWTException.INVALID_SIGNATURE,JWTException.INVALID_SIGNATURE);
        return true;
    }
    public TokenModel decode(String token) throws JsonProcessingException {
        TokenModel tokenModel = new TokenModel();
        List<String> strings = Arrays.asList(token.split("\\."));
        ObjectMapper mapper = new ObjectMapper();
        tokenModel.setHeaders(mapper.readValue(new String(Base64.getDecoder().decode(strings.get(0))),Map.class));
        tokenModel.setPayload(mapper.readValue(new String(Base64.getDecoder().decode(strings.get(1))),Map.class));
        tokenModel.setSignature(strings.get(2));
        return tokenModel;
    }
    public String hmacSHA256(String data,String secret) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] hash = secret.getBytes(StandardCharsets.UTF_8);
        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(hash, "HmacSHA256");
        sha256Hmac.init(secretKey);
        byte[] signedBytes = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(signedBytes);
    }
}
