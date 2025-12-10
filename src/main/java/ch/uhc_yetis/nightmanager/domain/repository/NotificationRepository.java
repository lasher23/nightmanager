package ch.uhc_yetis.nightmanager.domain.repository;

import ch.uhc_yetis.nightmanager.domain.model.NotificationLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationLog, Long> {
	List<NotificationLog> findByTagIdOrderBySentTimeDesc(String tag);
	List<NotificationLog> findByTagIdInOrderBySentTimeDesc(List<String> tags);
}
