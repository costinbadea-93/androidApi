package androidApi.service;

import androidApi.model.Countries;
import androidApi.model.View_Cities;
import androidApi.repository.CountriesRepository;
import androidApi.repository.View_CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Views_service {

    @Autowired
    View_CitiesRepository view_citiesRepository;

    @Autowired
    CountriesRepository countriesRepository;

    public List<View_Cities> getViewCitiesData() {
        return countriesRepository.getViewData();
    }
}
