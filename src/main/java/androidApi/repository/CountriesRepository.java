package androidApi.repository;

import androidApi.model.Cities;
import androidApi.model.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountriesRepository  extends JpaRepository<Countries, Integer> {

    @Query(value="SELECT * FROM Countries WHERE countries.country_name=:name",nativeQuery=true)
    public Countries getCountryId(@Param("name") String name);

    @Query("select a from Countries b, Cities a where a.country = b")
    public List<Cities> getViewData();

    @Query("SELECT c FROM Countries AS c WHERE c.country_name = :name")
    public Countries findByName(@Param("name") String name);
}
