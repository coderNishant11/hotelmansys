package com.hotelmansys.service;

import com.hotelmansys.entity.AppUser;
import com.hotelmansys.payload.LoginDto;
import com.hotelmansys.payload.UserDto;
import com.hotelmansys.repository.AppUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {

    private AppUserRepository appUserRepository;

    private ModelMapper modelMapper;

    private JwtService jwtService;

    public AppUserService(AppUserRepository appUserRepository, ModelMapper modelMapper, JwtService jwtService) {
        this.appUserRepository = appUserRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }



    public UserDto mapToDto(AppUser appUser){
        return modelMapper.map(appUser,UserDto.class);
    }

    public AppUser mapToEntity(UserDto userDto){
        return modelMapper.map(userDto, AppUser.class);
    }

    public UserDto createUser(UserDto userDto) {

        AppUser appUser = mapToEntity(userDto);
        AppUser save = appUserRepository.save(appUser);

        return mapToDto(save);
    }

    public String loginUser(LoginDto loginDto) {
        Optional<AppUser> byEmailOrUsername = appUserRepository.findByEmailOrUsername(loginDto.getUsername());

        if(byEmailOrUsername.isPresent()){
            AppUser appUser = byEmailOrUsername.get();

            boolean checkpw = BCrypt.checkpw(loginDto.getPassword(), appUser.getPassword());
            if(checkpw){
                String token = jwtService.generateToken(loginDto.getUsername());

                return token;
            }



        }

        return null;
    }
}
