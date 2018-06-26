package androidApi.repository;

import androidApi.model.Accomodations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccomodationRepository extends JpaRepository<Accomodations, Integer> {

    @Query("select distinct a from Roomtypes b, Accomodations a where a = b.accomodation")
    public List<Accomodations> getViewData();
}
