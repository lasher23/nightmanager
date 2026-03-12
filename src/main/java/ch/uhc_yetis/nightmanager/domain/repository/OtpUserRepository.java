package ch.uhc_yetis.nightmanager.domain.repository;

import ch.uhc_yetis.nightmanager.domain.model.OtpUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpUserRepository extends JpaRepository<OtpUser, Long> {
    Optional<OtpUser> findByToken(String token);
    boolean existsByName(String name);
}
