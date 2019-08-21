package ch.uhc_yetis.nightmanager.service.impl;

import ch.uhc_yetis.nightmanager.service.HallService;
import ch.uhc_yetis.nightmanager.domain.Hall;
import ch.uhc_yetis.nightmanager.repository.HallRepository;
import ch.uhc_yetis.nightmanager.service.dto.HallDTO;
import ch.uhc_yetis.nightmanager.service.mapper.HallMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Hall}.
 */
@Service
@Transactional
public class HallServiceImpl implements HallService {

    private final Logger log = LoggerFactory.getLogger(HallServiceImpl.class);

    private final HallRepository hallRepository;

    private final HallMapper hallMapper;

    public HallServiceImpl(HallRepository hallRepository, HallMapper hallMapper) {
        this.hallRepository = hallRepository;
        this.hallMapper = hallMapper;
    }

    /**
     * Save a hall.
     *
     * @param hallDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public HallDTO save(HallDTO hallDTO) {
        log.debug("Request to save Hall : {}", hallDTO);
        Hall hall = hallMapper.toEntity(hallDTO);
        hall = hallRepository.save(hall);
        return hallMapper.toDto(hall);
    }

    /**
     * Get all the halls.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HallDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Halls");
        return hallRepository.findAll(pageable)
            .map(hallMapper::toDto);
    }


    /**
     * Get one hall by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HallDTO> findOne(Long id) {
        log.debug("Request to get Hall : {}", id);
        return hallRepository.findById(id)
            .map(hallMapper::toDto);
    }

    /**
     * Delete the hall by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Hall : {}", id);
        hallRepository.deleteById(id);
    }
}
