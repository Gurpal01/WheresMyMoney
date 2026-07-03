package com.Gurpal.WheresMyMoney.Service;

import com.Gurpal.WheresMyMoney.Dto.UserRequestDto;
import com.Gurpal.WheresMyMoney.Dto.UserReturnDto;
import com.Gurpal.WheresMyMoney.Entity.User;
import com.Gurpal.WheresMyMoney.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String setUser(UserRequestDto userRequestDto)
    {
        Optional<User> userOPt  = userRepository.findByUserName(userRequestDto.getUserName());
        if(userOPt.isPresent())
        {
            return "User Already Exist";
        }
        User user = new User();
        user.setUserName(userRequestDto.getUserName());
        user.setUserEmail(userRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userRepository.save(user);
        return "Sign Up Successfully";
    }

    public String deleteUser(String userName)
    {
        userRepository.deleteById(userRepository.findByUserName(userName).get().getUserId());
        return "User Deleted Successfully";
    }

    public UserReturnDto getUser(String userName)
    {
        User user = userRepository.findByUserName(userName).get();
        UserReturnDto userReturnDto = new UserReturnDto();
        userReturnDto.setUserName(user.getUsername());
        userReturnDto.setUserEmail(user.getUserEmail());
        return userReturnDto;
    }
}
