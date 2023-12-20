package com.arrkadique.bankingsystem.configuration;

import com.arrkadique.bankingsystem.entity.User;
import com.arrkadique.bankingsystem.repository.UserRepository;
import com.arrkadique.bankingsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Slf4j
public class AuthProvider implements AuthenticationProvider
{
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        String[] passCode = password.split(" ");
        User user = (User) userService.loadUserByUsername(username);

        if(user.getEmail().equals(username))
        {
            if(!passwordEncoder.matches(passCode[0], user.getPassword()))
            {
                throw new BadCredentialsException("Please make sure you are using a valid username or password");
            }
            if(!passCode[1].equals(user.getCode()))
            {
                throw new BadCredentialsException("Invalid code");
            }

            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

            return new UsernamePasswordAuthenticationToken(user, password, authorities);
        }
        throw new BadCredentialsException("Wrong data");
    }

    public boolean supports(Class<?> arg)
    {
        return true;
    }
}
