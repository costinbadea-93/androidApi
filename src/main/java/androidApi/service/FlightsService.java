package androidApi.service;

import androidApi.model.*;
import androidApi.repository.AccomodationRepository;
import androidApi.repository.CityRepository;
import androidApi.repository.CountriesRepository;
import androidApi.repository.FlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FlightsService {
    @Autowired
    FlightsRepository flightsRepository;

    @Autowired
    CountriesRepository countriesRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CityService cityService;

    public List<Flights> getFlights() {
        return flightsRepository.findAll();
    }

    public List<Flights> getFileteredFlights( String departureCountry,String arrivalCountry, String FlightDate, int isBusiness, int numberOfSeats){
        Countries departureCountries = countriesRepository.getCountryId(departureCountry);
        int departureCountryId =  departureCountries.getCountry_id();

        List<Cities> departureCities =  cityRepository.findAll().stream()
                .filter(e -> e.getCountry().getCountry_id() == departureCountryId).collect(Collectors.toList());

        Countries arrivalCountries = countriesRepository.getCountryId(arrivalCountry);
        int currentCountryId = arrivalCountries.getCountry_id();

        List<Cities> arrivalCities = cityRepository.findAll().stream()
                .filter(e -> e.getCountry().getCountry_id() == currentCountryId).collect(Collectors.toList());

        List<Flights> flights =  flightsRepository.findAll();
        List<Flights> filteredFligthsFrom = cityService.getFilterFlightsList(flights, arrivalCities, departureCities);

        List<Flights> relevantFromFlights = getMostSignificantFlights(filteredFligthsFrom,isBusiness, numberOfSeats, FlightDate);

        return  relevantFromFlights;
    }

    private List<Flights> getMostSignificantFlights(List<Flights> flightsList, int isBusiness, int numberOfSeats, String FlightDate){
        List<Flights> availableFlights = new ArrayList<>();
        for(Flights f : flightsList) {
            //Filter by date and category of seats
            int numberOfTakenSeats = 0;
            List<Reservations_flights> flightsReservations = f.getRezFligh().stream()
                    .filter(e -> cityService.parseToDateTime(e.getFlight_date()) == cityService.parseToDateTime(FlightDate) && e.getIsBusiness() == isBusiness)
                    .collect(Collectors.toList());

            if(flightsReservations.size() == 0){
                if(isBusiness == 1) {
                    if(f.getSeats_business()  >= numberOfSeats){
                        availableFlights.add(f);
                    }
                }else {
                    //if is economic
                    if(f.getSeats_economic() >= numberOfSeats){
                        availableFlights.add(f);
                    }
                }

            }else {
                for(Reservations_flights rf : flightsReservations) {
                    numberOfTakenSeats += rf.getNumberOfSeats();
                }

                if(isBusiness == 1) {
                    if(f.getSeats_business() - numberOfTakenSeats >= numberOfSeats){
                        availableFlights.add(f);
                    }
                }else {
                    //if is economic
                    if(f.getSeats_economic() - numberOfTakenSeats >= numberOfSeats){
                        availableFlights.add(f);
                    }
                }
            }
        }
        return availableFlights;
    }
}
