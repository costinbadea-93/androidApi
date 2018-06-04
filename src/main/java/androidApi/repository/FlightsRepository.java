package androidApi.repository;

import androidApi.model.Flights;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightsRepository extends JpaRepository<Flights,Integer> {
}
