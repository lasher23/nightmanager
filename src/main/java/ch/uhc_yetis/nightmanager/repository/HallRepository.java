package ch.uhc_yetis.nightmanager.repository;

import ch.uhc_yetis.nightmanager.domain.Hall;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Hall entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {

}
