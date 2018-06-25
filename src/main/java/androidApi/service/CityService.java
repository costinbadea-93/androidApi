package androidApi.service;

import androidApi.dto.FullReservationDTO;
import androidApi.model.*;
import androidApi.repository.*;
import com.sun.xml.internal.bind.v2.TODO;
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

    @Autowired
    AccomodationRepository accomodationRepository;

    @Autowired
    ReservationAccomodationRepository reservationAccomodationRepository;

    @Autowired
    RoomtypesRepository roomtypesRepository;


    public List<Cities> getCities() {
        return cityRepository.findAll();
    }

    public List<Wishes> getWishesByCountry(String countryName, int price) {
//        int countryId = countriesRepository.getCountryId(countryName);
//        List<Wishes> allWishes = wishesRepository.findAll();
//        List<Cities> allCities = cityRepository.findAll();
//        List<Flights> allFligths =  flightsRepository.findAll();
//
//        if(countryId > 0 && price == 0 ) {
//            return allWishes.stream().filter(e -> e.getCity().getCity_id() == countryId).collect(Collectors.toList());
//        } else if (countryId > 0  && price > 0){
//            Flights mostRelevantFlight = getMostSignificantFligthBasedOnPrice(allFligths, price);
//            return allWishes
//                    .stream()
//                    .filter(e -> e.getCity().getCity_id() == mostRelevantFlight.getArrival_city_id().getCity_id()
//                                || e.getCity().getCity_id() == mostRelevantFlight.getDeparture_city_id().getCity_id())
//                    .collect(Collectors.toList());
//        }
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



    public List<FullReservationDTO> getParsedData (String DepartureCountry, String ArrivalCountry, String BeginDate, String EndDate, double budget, int trackHistory, int isBusiness,int numberOfRooms, String roomType, int numberOfSeats) {
        Reservations_accomodations reservations_accomodations = getReservationAccomoodationForDTO(ArrivalCountry, BeginDate, EndDate, budget, roomType);

        return null;
    }

    private Reservations_accomodations getReservationAccomoodationForDTO(String ArrivalCountry, String BeginDate, String EndDate, double budget, String roomType) {
        //GET ArrivalCountry
        Countries countries = countriesRepository.getCountryId(ArrivalCountry);
        int currentCountryId = countries.getCountry_id();

        List<Cities> cities = cityRepository.findAll().stream()
                .filter(e -> e.getCountry().getCountry_id() == currentCountryId).collect(Collectors.toList());

        List<Accomodations> accomodations = accomodationRepository.findAll().stream()
                .filter(e -> checkCitiesElementInList(cities, e)).collect(Collectors.toList());

        List<Reservations_accomodations> reservations_accomodations = reservationAccomodationRepository.findAll().stream()
                .filter(e -> checkAccomodationsElementInList(accomodations, e)).collect(Collectors.toList());

        List<Roomtypes> roomtypes = roomtypesRepository.findAll().stream()
                .filter(e -> checkRoomTypesElementInList(accomodations, e)).collect(Collectors.toList());

        //TODO : ADD BUDGET LOGIC


        return  null;

    }

    private boolean checkCitiesElementInList (List<Cities> cities, Accomodations accomodations) {
        for(Cities c : cities) {
            if ( c.getCity_id() == accomodations.getCity().getCity_id()){
                return true;
            }
        }
        return false;
    }

    private boolean checkAccomodationsElementInList (List<Accomodations> accomodations, Reservations_accomodations resAcc) {
        for(Accomodations a : accomodations) {
            if (a.getAccomodation_id() == resAcc.getAccomodation_reservation_id()){
                return true;
            }
        }
        return false;
    }

    private boolean checkRoomTypesElementInList (List<Accomodations> accomodations, Roomtypes roomtypes) {
        for(Accomodations a : accomodations) {
            if (a.getRoomType().getRoom_id() == roomtypes.getRoom_id()){
                return true;
            }
        }
        return false;
    }
}
