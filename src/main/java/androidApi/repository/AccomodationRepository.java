package androidApi.repository;

import androidApi.model.Accomodations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccomodationRepository extends JpaRepository<Accomodations, Integer> {
}
