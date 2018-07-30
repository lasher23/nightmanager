package ch.uhc_yetis.nightmanager.domain.repository;

import ch.uhc_yetis.nightmanager.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
