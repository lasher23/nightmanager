package ch.uhc_yetis.nightmanager.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Transactional
@Component
public interface ITeamRepository extends JpaRepository<TeamEntity, Long> {
}
