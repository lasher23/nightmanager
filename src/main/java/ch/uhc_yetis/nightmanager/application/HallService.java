package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.Hall;
import ch.uhc_yetis.nightmanager.domain.repository.HallRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {

  private HallRepository hallRepository;

  public HallService(HallRepository hallRepository) {
    this.hallRepository = hallRepository;
  }

  public List<Hall> findAll() {
    return this.hallRepository.findAll();
  }

  public Hall getById(long id) {
    return this.hallRepository.findById(id)
        .orElseThrow(() -> new CustomException("Halle nicht gefunden", Status.NOT_FOUND));
  }

  public Optional<Hall> findById(long hallId) {
    return this.hallRepository.findById(hallId);
  }
}
