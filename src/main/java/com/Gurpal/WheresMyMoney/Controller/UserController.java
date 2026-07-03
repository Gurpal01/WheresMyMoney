package com.Gurpal.WheresMyMoney.Controller;

import com.Gurpal.WheresMyMoney.Dto.UserRequestDto;
import com.Gurpal.WheresMyMoney.Dto.UserReturnDto;
import com.Gurpal.WheresMyMoney.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUpUser(@RequestBody UserRequestDto userRequestDto)
    {
        return ResponseEntity.ok(userService.setUser(userRequestDto));
    }

    @GetMapping("/user")
    public ResponseEntity<UserReturnDto> getUser(Authentication authentication)
    {
        String userName = authentication.getName();
        return ResponseEntity.ok(userService.getUser(userName));
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser(Authentication authentication)
    {
        String userName = authentication.getName();
        return ResponseEntity.ok(userService.deleteUser(userName));
    }

}
