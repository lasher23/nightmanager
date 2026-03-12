package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.ApplicationUser;
import ch.uhc_yetis.nightmanager.domain.repository.ApplicationUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserManagementService {

    private final ApplicationUserRepository userRepository;

    public UserManagementService(ApplicationUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserDto::from)
                .toList();
    }

    public UserDto findById(Long id) {
        return userRepository.findById(id)
                .map(UserDto::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + id));
    }

    public UserDto update(Long id, UpdateUserRequest request) {
        ApplicationUser user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + id));

        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }
        if (request.getEnabled() != null) {
            user.setEnabled(request.getEnabled());
        }
        if (request.getRoles() != null) {
            user.setRoles(request.getRoles());
        }

        return UserDto.from(userRepository.save(user));
    }
}
