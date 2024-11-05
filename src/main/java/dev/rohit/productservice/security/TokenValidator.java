package dev.rohit.productservice.security;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TokenValidator {
    private RestTemplateBuilder restTemplateBuilder;

    public TokenValidator(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public Optional<JwtData> validateToken(String token){
        // Use rest template to make a call to user service
        return Optional.empty();
        /* Using restTemplateBuilder I need to call "/validate" api of the 'User Services' and
           in validate api it will return JTW data ie( user, role, email etc.)
           For Detail how the flow is working check 10 FEB class note
         */
    }
}
