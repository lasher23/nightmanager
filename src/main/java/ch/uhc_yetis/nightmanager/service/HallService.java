package ch.uhc_yetis.nightmanager.service;

import ch.uhc_yetis.nightmanager.service.dto.HallDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ch.uhc_yetis.nightmanager.domain.Hall}.
 */
public interface HallService {

    /**
     * Save a hall.
     *
     * @param hallDTO the entity to save.
     * @return the persisted entity.
     */
    HallDTO save(HallDTO hallDTO);

    /**
     * Get all the halls.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HallDTO> findAll(Pageable pageable);


    /**
     * Get the "id" hall.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HallDTO> findOne(Long id);

    /**
     * Delete the "id" hall.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
