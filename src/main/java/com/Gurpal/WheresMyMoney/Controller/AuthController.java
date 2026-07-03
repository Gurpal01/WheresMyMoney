package com.Gurpal.WheresMyMoney.Controller;

import com.Gurpal.WheresMyMoney.Dto.AuthRequestDto;
import com.Gurpal.WheresMyMoney.Jwt.JwtUtility;
import org.apache.catalina.webresources.JarWarResource;
import org.hibernate.annotations.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    AuthenticationManager authenticationManager;
    JwtUtility jwtUtility; 

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,JwtUtility jwtUtility)
    {
        this.authenticationManager = authenticationManager;
        this.jwtUtility = jwtUtility;
    }

    @PostMapping("/login")
    public ResponseEntity<?> logInUser(@RequestBody AuthRequestDto authRequestDto)
    {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDto.getUserName(), authRequestDto.getPassword())
            );

            String token = jwtUtility.createToken(authRequestDto.getUserName());
            return ResponseEntity.ok(token);
        }
        catch (Exception e) {
            e.printStackTrace();   // <-- Important
            return ResponseEntity.badRequest().body(e.getClass().getName() + " : " + e.getMessage());
        }
    }


}
