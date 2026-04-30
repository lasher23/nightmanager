package ch.uhc_yetis.nightmanager.domain.repository;

import ch.uhc_yetis.nightmanager.domain.model.RegistrationGroup;
import ch.uhc_yetis.nightmanager.domain.model.RegistrationRequest;
import ch.uhc_yetis.nightmanager.domain.model.RegistrationRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {
    List<RegistrationRequest> findByRegistrationGroup(RegistrationGroup group);
    List<RegistrationRequest> findByRegistrationGroupAndStatus(RegistrationGroup group, RegistrationRequestStatus status);
    List<RegistrationRequest> findByRegistrationGroup_Tournament_Id(Long tournamentId);
}
