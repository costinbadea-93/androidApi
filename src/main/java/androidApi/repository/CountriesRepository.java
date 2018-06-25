package androidApi.repository;

import androidApi.model.Countries;
import androidApi.model.Wishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountriesRepository  extends JpaRepository<Countries, Integer> {

    @Query(value="SELECT country_id FROM Countries WHERE Countries.country_name=:name",nativeQuery=true)
    public int getCountryId(@Param("name") String name);
}
