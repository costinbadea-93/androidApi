package androidApi.service;

import androidApi.model.Cities;
import androidApi.model.Countries;
import androidApi.model.Wishes;
import androidApi.repository.CityRepository;
import androidApi.repository.CountriesRepository;
import androidApi.repository.WishesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    WishesRepository wishesRepository;

    @Autowired
    CountriesRepository countriesRepository;



    public List<Cities> getCities() {
        return cityRepository.findAll();
    }

    public List<Wishes> getWishesByCountry(String countryName, String destination, int nrAdulti, int nrCopii) {


        List<Wishes> allWishes = wishesRepository.findAll();

        int countryId = countriesRepository.getCountryId(countryName);

        List<Cities> allCities = cityRepository.findAll();

        if(countryId > 0  && (nrAdulti == 0) && (nrCopii == 0)) {
            return allWishes.stream().filter(e -> e.getCity().getCity_id() == countryId).collect(Collectors.toList());
        } else if(countryId > 0  && (nrAdulti > 0) && (nrCopii > 0))
        return null;
    }
}
