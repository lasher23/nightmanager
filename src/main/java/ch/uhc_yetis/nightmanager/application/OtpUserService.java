package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.OtpUser;
import ch.uhc_yetis.nightmanager.domain.repository.OtpUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class OtpUserService {

    private final OtpUserRepository otpUserRepository;

    public OtpUserService(OtpUserRepository otpUserRepository) {
        this.otpUserRepository = otpUserRepository;
    }

    public List<OtpUserDto> findAll() {
        return otpUserRepository.findAll().stream()
                .map(OtpUserDto::from)
                .toList();
    }

    public OtpUserDto findById(Long id) {
        return otpUserRepository.findById(id)
                .map(OtpUserDto::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "OTP user not found: " + id));
    }

    public OtpUserDto create(CreateOtpUserRequest req) {
        if (req.getName() == null || req.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is required");
        }
        if (req.getValidUntil() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "validUntil is required");
        }
        if (otpUserRepository.existsByName(req.getName().trim())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "An OTP user with that name already exists");
        }

        OtpUser user = new OtpUser();
        user.setName(req.getName().trim());
        user.setToken(UUID.randomUUID().toString());
        user.setValidUntil(req.getValidUntil());
        if (req.getRoles() != null) {
            user.setRoles(req.getRoles());
        }

        return OtpUserDto.from(otpUserRepository.save(user));
    }

    public void delete(Long id) {
        if (!otpUserRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OTP user not found: " + id);
        }
        otpUserRepository.deleteById(id);
    }
}
