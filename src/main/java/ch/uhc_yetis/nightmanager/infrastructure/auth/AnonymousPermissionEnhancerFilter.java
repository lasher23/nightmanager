package ch.uhc_yetis.nightmanager.infrastructure.auth;

import ch.uhc_yetis.nightmanager.application.PermissionService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Runs after Spring's AnonymousAuthenticationFilter.
 * Replaces the default anonymous token with one containing the permissions
 * from the ANONYMOUS role in the database, so that @PreAuthorize checks
 * work uniformly for both authenticated and unauthenticated users.
 */
@Component
public class AnonymousPermissionEnhancerFilter extends OncePerRequestFilter {

    private final PermissionService permissionService;

    public AnonymousPermissionEnhancerFilter(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            Set<String> permissions = permissionService.resolvePermissionsForRole("ANONYMOUS");
            if (!permissions.isEmpty()) {
                var authorities = permissions.stream()
                        .map(SimpleGrantedAuthority::new)
                        .map(a -> (org.springframework.security.core.GrantedAuthority) a)
                        .toList();
                var enhanced = new AnonymousAuthenticationToken(
                        "anonymousKey", "anonymousUser", authorities);
                SecurityContextHolder.getContext().setAuthentication(enhanced);
            }
        }
        chain.doFilter(request, response);
    }
}
