package androidApi.repository;

import androidApi.model.Countries;
import androidApi.model.View_Cities;
import androidApi.model.Wishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountriesRepository  extends JpaRepository<Countries, Integer> {

    @Query(value="SELECT * FROM Countries WHERE Countries.country_name=:name",nativeQuery=true)
    public Countries getCountryId(@Param("name") String name);

    @Query(value="select a.city_name, b.country_code from countries b,cities a where a.country_country_id=b.country_id",nativeQuery=true)
    public List<View_Cities> getViewData();
}
