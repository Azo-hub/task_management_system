package com.task_management_system.controller;

import com.task_management_system.domain.User;
import com.task_management_system.dto.LoginRequest;
import com.task_management_system.dto.UserDto;
import com.task_management_system.exceptions.EmailExistException;
import com.task_management_system.exceptions.UserNotFoundException;
import com.task_management_system.exceptions.UsernameExistException;
import com.task_management_system.security.UserPrincipal;
import com.task_management_system.security.jwt.JWTTokenProvider;
import com.task_management_system.services.UserService;
import com.task_management_system.utilities.SecurityConstant;
import com.task_management_system.utilities.SecurityUtility;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenProvider jWTTokenProvider;

    @Operation(
            description = "Create a new user endpoint",
            summary = "create a new user by taking userDto, the user role is either USER or ADMIN.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }

    )
    @PostMapping("/signUp")
    public ResponseEntity<User> newUserPost(@RequestBody @Valid UserDto userDto)
            throws UsernameExistException, UserNotFoundException, EmailExistException {

        User newUser = userService.createUser(
                userDto.getEmail(), userDto.getUsername(), userDto.getPassword(), userDto.getRole());

        return new ResponseEntity<>(newUser, HttpStatus.OK);

    }

    @Operation(
            description = "Login endpoint",
            summary = "returning an authenticated user with the bearer token as a header",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }

    )
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody @Valid LoginRequest loginRequest) {
        authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        User loginUser = userService.findByUsername(loginRequest.getUsername());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);

        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        loginUser.setLastLoginDate(LocalDateTime.now());
        userService.save(loginUser);

        return new ResponseEntity<>(loginUser, jwtHeader, HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {

        HttpHeaders headers = new HttpHeaders();
        headers.add(SecurityConstant.JWT_TOKEN_HEADER, jWTTokenProvider.generateJwtToken(userPrincipal));
        return headers;
    }


}
