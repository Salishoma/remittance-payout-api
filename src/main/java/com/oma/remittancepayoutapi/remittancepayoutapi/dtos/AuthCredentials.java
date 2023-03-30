package com.oma.remittancepayoutapi.remittancepayoutapi.dtos;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
import lombok.*;
//import org.springframework.data.redis.core.RedisHash;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
//@RedisHash("auth_token")
//@Entity
public class AuthCredentials {
//    @Id
    private String access_token;
    private Long created_at;
    private Long expires_in;
}
