package androidApi.repository;

import androidApi.model.Cities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<Cities, Integer> {
}
