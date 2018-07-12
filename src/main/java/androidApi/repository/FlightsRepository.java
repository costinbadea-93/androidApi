package androidApi.repository;

import androidApi.model.Countries;
import androidApi.model.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FlightsRepository extends JpaRepository<Flights,Integer> {

    @Query("SELECT f FROM Flights AS f WHERE f.flight_id = :name")
    public Flights findByName(@Param("name") String name);
}
