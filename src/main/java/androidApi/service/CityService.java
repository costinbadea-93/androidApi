package androidApi.service;

import androidApi.model.Cities;
import androidApi.model.Countries;
import androidApi.model.Flights;
import androidApi.model.Wishes;
import androidApi.repository.CityRepository;
import androidApi.repository.CountriesRepository;
import androidApi.repository.FlightsRepository;
import androidApi.repository.WishesRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    WishesRepository wishesRepository;

    @Autowired
    CountriesRepository countriesRepository;

    @Autowired
    FlightsRepository flightsRepository;



    public List<Cities> getCities() {
        return cityRepository.findAll();
    }

    public List<Wishes> getWishesByCountry(String countryName, int price) {
        int countryId = countriesRepository.getCountryId(countryName);
        List<Wishes> allWishes = wishesRepository.findAll();
        List<Cities> allCities = cityRepository.findAll();
        List<Flights> allFligths =  flightsRepository.findAll();

        if(countryId > 0 && price == 0 ) {
            return allWishes.stream().filter(e -> e.getCity().getCity_id() == countryId).collect(Collectors.toList());
        } else if (countryId > 0  && price > 0){
            Flights mostRelevantFlight = getMostSignificantFligthBasedOnPrice(allFligths, price);
            return allWishes
                    .stream()
                    .filter(e -> e.getCity().getCity_id() == mostRelevantFlight.getArrival_city_id().getCity_id()
                                || e.getCity().getCity_id() == mostRelevantFlight.getDeparture_city_id().getCity_id())
                    .collect(Collectors.toList());
        }
        return null;
    }

    private Flights getMostSignificantFligthBasedOnPrice (List<Flights> flights, int price){

        Map<String, Double> priceRoundList =  new TreeMap<>();

        flights.forEach(e -> {
            if (e.getPrice_economic() > price) {
                double difference = e.getPrice_economic() - price;
                priceRoundList.put(e.getFlight_id(), difference);
            } else {
                double difference = price -  e.getPrice_economic();
                priceRoundList.put(e.getFlight_id(), difference);
            }
        });
        Map.Entry<String, Double> min = Collections.min(priceRoundList.entrySet(), new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> entry1, Map.Entry<String, Double> entry2) {
                return entry1.getValue().compareTo(entry2.getValue());
            }
        });
        List<Flights> returnedFlight = flights.stream().filter(e ->
            e.getFlight_id().equalsIgnoreCase(min.getKey())
        ).collect(Collectors.toList());
        return returnedFlight.get(0);
    }
}
