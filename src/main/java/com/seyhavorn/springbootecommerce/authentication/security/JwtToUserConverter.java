package com.seyhavorn.springbootecommerce.authentication.security;

import com.seyhavorn.springbootecommerce.authentication.entity.User;
import com.seyhavorn.springbootecommerce.authentication.repository.UserRepository;
import com.seyhavorn.springbootecommerce.authentication.service.impl.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@AllArgsConstructor
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    private final UserRepository userRepository;

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
        User user = userRepository.findById(Long.parseLong(jwt.getSubject())).orElseThrow(() -> new BadCredentialsException("User not found"));
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        return new UsernamePasswordAuthenticationToken(userDetails, jwt, userDetails.getAuthorities());
    }
}
