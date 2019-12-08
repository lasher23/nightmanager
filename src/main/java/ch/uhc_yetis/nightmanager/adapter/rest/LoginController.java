package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.UserDetailsServiceImpl;
import ch.uhc_yetis.nightmanager.domain.model.ApplicationUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.stream.Collectors;

import static ch.uhc_yetis.nightmanager.infrastructure.SecurityConstants.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private UserDetailsServiceImpl userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginController(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody ApplicationUser user) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(user.getUsername());
        if (this.bCryptPasswordEncoder.matches(user.getPassword(), userDetails.getPassword())) {
            String jwt = this.createJWT(userDetails);
            UserDto userDto = new UserDto();
            userDto.setUsername(userDetails.getUsername());
            userDto.setRoles(userDetails.getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toList()));
            return ResponseEntity.ok().header(HEADER_STRING, jwt).body(userDto);
        }
        return ResponseEntity.status(401).build();
    }

    private String createJWT(UserDetails user) {
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        return TOKEN_PREFIX + token;

    }
}
