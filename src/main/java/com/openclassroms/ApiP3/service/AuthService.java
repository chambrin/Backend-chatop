package com.openclassroms.ApiP3.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassroms.ApiP3.dto.TokenResponseDTO;
import com.openclassroms.ApiP3.exception.AuthenticationFailedException;

@Service
public class AuthService {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthService(CustomUserDetailsService customUserDetailsService,
            PasswordEncoder passwordEncoder,
            JWTService jwtService) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public TokenResponseDTO authenticateUser(String username, String password) {
        try {
            UserDetails user = customUserDetailsService.loadUserByUsername(username);

            boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());

            if (!isPasswordMatch) {
                throw new BadCredentialsException("Invalid username or password");
            }
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    user.getUsername(), user.getPassword(), user.getAuthorities());
            String token = jwtService.generateToken(authentication);
            return new TokenResponseDTO(token);
        } catch (BadCredentialsException e) {
            throw new AuthenticationFailedException("Invalid username or password");
        } catch (Exception e) {
            throw new AuthenticationFailedException("Authentication failed due to an unknown error");
        }
    }
}
