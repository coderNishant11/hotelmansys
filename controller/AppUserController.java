package com.hotelmansys.controller;

import com.hotelmansys.entity.AppUser;
import com.hotelmansys.payload.LoginDto;
import com.hotelmansys.payload.TokenDto;
import com.hotelmansys.payload.UserDto;
import com.hotelmansys.repository.AppUserRepository;
import com.hotelmansys.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class AppUserController {
    private AppUserRepository appUserRepository;

    private AppUserService appUserService;

    public AppUserController(AppUserRepository appUserRepository, AppUserService appUserService) {
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> userSignup(
            @Valid @RequestBody UserDto userDto,
            BindingResult result
    ){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> byEmail = appUserRepository.findByEmail(userDto.getEmail());
        if(byEmail.isPresent()){
            return new ResponseEntity<>("Email id already exists", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        Optional<AppUser> byUsername = appUserRepository.findByUsername(userDto.getUsername());
        if(byUsername.isPresent()){
            return new ResponseEntity<>("Username already exists",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userDto.setRole("ROLE_USER");
        userDto.setPassword(BCrypt.hashpw(userDto.getPassword(),BCrypt.gensalt(5)));
        UserDto user = appUserService.createUser(userDto);

        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @PostMapping("/signup-property-owner")
    public ResponseEntity<?> propertOwnerSignup(
            @Valid @RequestBody UserDto userDto,
            BindingResult result
    ){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> byUsername = appUserRepository.findByUsername(userDto.getUsername());
        if(byUsername.isPresent()){
            return new ResponseEntity<>("Username already exists",HttpStatus.INTERNAL_SERVER_ERROR);

        }
        Optional<AppUser> byEmail = appUserRepository.findByEmail(userDto.getEmail());
        if(byEmail.isPresent()){
            return new ResponseEntity<>("Email already exits", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userDto.setRole("ROLE_OWNER");
        userDto.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(5)));
        UserDto user = appUserService.createUser(userDto);
        return new ResponseEntity<>(user,HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<? >login(@RequestBody LoginDto loginDto){

        String token = appUserService.loginUser(loginDto);

        if(token!=null){

            TokenDto jwtToken = new TokenDto(token, "JWT");

            return new ResponseEntity<>(jwtToken,HttpStatus.OK);


        }else{

            return new ResponseEntity<>("username or password is wrong", HttpStatus.OK);
        }

    }

}
