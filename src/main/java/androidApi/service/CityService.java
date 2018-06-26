package androidApi.service;

import androidApi.dto.FullReservationDTO;
import androidApi.model.*;
import androidApi.repository.*;
import com.sun.xml.internal.bind.v2.TODO;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
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

        BeginDate = "22/08/2018";
        EndDate = "26/08/2018";

        Countries countries = countriesRepository.getCountryId(ArrivalCountry);
        int currentCountryId = countries.getCountry_id();

        List<Cities> cities = cityRepository.findAll().stream()
                .filter(e -> e.getCountry().getCountry_id() == currentCountryId).collect(Collectors.toList());

        List<Accomodations> accomodations = accomodationRepository.findAll().stream()
                .filter(e -> checkCitiesElementInList(cities, e)).collect(Collectors.toList());

        List<Reservations_accomodations> reservations_accomodations = reservationAccomodationRepository.findAll().stream()
                .filter(e -> checkAccomodationsElementInList(accomodations, e)).collect(Collectors.toList());

        //TODO : ADD BUDGET LOGIC
        LocalDate fromBeginDate = toLocaleDate(BeginDate);
        LocalDate fromEndDate = toLocaleDate(EndDate);
        List<Accomodations> relevantAccomodations = accomodationRepository.getViewData();
        List<Accomodations> returnedToViewRelevantAccomodations =  new ArrayList<>();

        for( Accomodations acc : relevantAccomodations){
            List<Roomtypes> filteredByRoomType = acc.getRoomType().stream()
                    .filter(e -> e.getType() == roomType).collect(Collectors.toList());
            List<Reservations_accomodations> rezAcc =  acc.getRezAccs();

            int initalSumCount = 0;
            for (Reservations_accomodations rez: rezAcc) {
                if(rez.getBegin_time().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfMonth() > toLocaleDate(BeginDate).getDayOfMonth() &&
                        rez.getBegin_time().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfMonth() < toLocaleDate(EndDate).getDayOfMonth()){
                    initalSumCount += rez.getNo_of_rooms();
                }
            }

            if(initalSumCount > rezAcc.get(0).getNo_of_rooms()) {
                returnedToViewRelevantAccomodations.add(acc);
            }
        }

       long numberDays =  fromBeginDate.getDayOfMonth() > fromEndDate.getDayOfMonth() ? fromBeginDate.getDayOfMonth() - fromEndDate.getDayOfMonth() : fromEndDate.getDayOfMonth() - fromBeginDate.getDayOfMonth();
       List<Roomtypes> mostRelevantRooms =  new ArrayList<>();
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

    private LocalDate toLocaleDate(String date){
        String pattern = "dd/MM/yyyy";
        try {
            DateFormat df = new SimpleDateFormat(pattern);
            Date today = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Using Java 8 Date and Time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
}
