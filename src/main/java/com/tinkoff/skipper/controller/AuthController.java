package com.tinkoff.skipper.controller;


import com.tinkoff.skipper.service.AuthService;
import com.tinkoff.skipper.dto.JwtRequest;
import com.tinkoff.skipper.dto.JwtResponse;
import com.tinkoff.skipper.dto.RefreshJwtRequest;
import com.tinkoff.skipper.utils.SkipperResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
@CrossOrigin(origins="http://localhost:8080")
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest)
            throws AuthException {
        final JwtResponse token = authService.login(authRequest);
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                token
        );
    }

    @PostMapping("token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                token
        );
    }

//    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request)
            throws AuthException {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return SkipperResponseBuilder.buildResponse(
                HttpStatus.OK,
                token
        );
    }


}
