package br.com.frasson.controller;

import br.com.frasson.dto.LoginUserDto;
import br.com.frasson.dto.RegisterUserDto;
import br.com.frasson.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    UserController (UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginUserDto loginUserDto) {
        return this.userService.login(loginUserDto.getEmail(), loginUserDto.getPassword());
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterUserDto registerUserDto) {
        this.userService.register(registerUserDto.getName(), registerUserDto.getEmail(), registerUserDto.getPassword());
    }
}
