package androidApi.repository;

import androidApi.model.Reservations_accomodations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationAccomodationRepository extends JpaRepository<Reservations_accomodations,Integer> {
}
