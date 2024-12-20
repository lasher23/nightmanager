package ch.uhc_yetis.nightmanager.infrastructure;

import ch.uhc_yetis.nightmanager.domain.model.ApplicationUser;
import ch.uhc_yetis.nightmanager.domain.repository.ApplicationUserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final ApplicationUserRepository applicationUserRepository;

    public JWTAuthorizationFilter(ApplicationUserRepository applicationUserRepository) {
        super();
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(SecurityConstants.HEADER_STRING);
        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        Authentication authentication = this.getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = Jwts.parserBuilder()
                    .setSigningKey(SecurityConstants.SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                ApplicationUser applicationUser = applicationUserRepository.findByUsername(user);
                if (applicationUser != null) {
                    return new UsernamePasswordAuthenticationToken(user, applicationUser.getPassword(), applicationUser.getRoles().stream().map(role -> (GrantedAuthority) role::getName).collect(Collectors.toList()));
                }
                return null;
            }
            return null;
        }
        return null;
    }
}
