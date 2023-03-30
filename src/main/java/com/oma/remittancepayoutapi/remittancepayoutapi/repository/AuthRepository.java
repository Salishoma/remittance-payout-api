//package com.oma.remittancepayoutapi.remittancepayoutapi.repository;
//
//import com.oma.remittancepayoutapi.remittancepayoutapi.dtos.AuthCredentials;
//import lombok.AllArgsConstructor;
////import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Repository
//@AllArgsConstructor
//public class AuthRepository {
//    private final RedisTemplate<String, AuthCredentials> template;
//
//    public void save(String token, AuthCredentials cred) {
//        template.opsForHash().put(token, token, cred);
//    }
//
//    public AuthCredentials findBbyToken(String token) {
//        return (AuthCredentials) template.opsForHash().get(token, token);
//    }
//
//
//    public void deleteCredentials(String email, String reference) {
//        template.opsForHash().delete(email, reference);
//    }
//}
