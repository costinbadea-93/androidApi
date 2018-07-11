package androidApi.repository;

import androidApi.model.Reservations_flights;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationFligthRepository extends JpaRepository<Reservations_flights,Integer> {
}
