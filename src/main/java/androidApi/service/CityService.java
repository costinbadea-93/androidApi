package androidApi.service;

import androidApi.dto.FullReservationDTO;
import androidApi.model.*;
import androidApi.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CityService {
    private final Logger logger = LoggerFactory.getLogger(CityService.class);
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



    public FullReservationDTO  getParsedData (String DepartureCountry, String ArrivalCountry, String BeginDate, String EndDate, double budget, int trackHistory, int isBusiness,int numberOfRooms, String roomType, int numberOfSeats,int userId, List<String>wishes) {
       return getReservationAccomoodationForDTO(ArrivalCountry,DepartureCountry, BeginDate, EndDate, budget, roomType, numberOfRooms, isBusiness, numberOfSeats,userId, wishes, trackHistory);

    }

    private List<Cities> getArrivalCitiesByWishes(List<String> wishes, List<Cities> arrivalCities){
        List<Cities> filteredCities =  new ArrayList<>();
        for(String wish : wishes){
            for(Cities city : arrivalCities){
                List<Wishes> cityWishes = city.getWishes();
                 for(Wishes w : cityWishes){
                    if( w.getWish_name().equals(wish)){
                        filteredCities.add(city);
                    }
                }
            }
        }

        return filteredCities;
    }

    private List<Accomodations> filterAccByHistory(List<Integer> ids, List<Accomodations> accomodations){
        List<Accomodations> returnedAccomodations =  new ArrayList<>();
            for(int id : ids){
                for( Accomodations acc: accomodations){
                    if(id != acc.getAccomodation_id()){
                        returnedAccomodations.add(acc);
                    }
                }
            }

            return returnedAccomodations;
    }

    private FullReservationDTO getReservationAccomoodationForDTO(String ArrivalCountry, String DepartureCountry,
                                                                 String BeginDate, String EndDate, double budget,
                                                                 String roomType, int numberOfRooms, int isBussiness,
                                                                 int numberOfSeats,int userId, List<String> wishes,
                                                                 int trackHistory) {

        FullReservationDTO returnedResult = new FullReservationDTO();
//        BeginDate = "22/08/2018";
//        EndDate = "26/08/2018";

        Countries departureCountries = countriesRepository.getCountryId(DepartureCountry);
        int departureCountryId =  departureCountries.getCountry_id();

        List<Cities> departureCities =  cityRepository.findAll().stream()
                .filter(e -> e.getCountry().getCountry_id() == departureCountryId).collect(Collectors.toList());

        Countries arrivalCountries = countriesRepository.getCountryId(ArrivalCountry);
        int currentCountryId = arrivalCountries.getCountry_id();

        List<Cities> arrivalCities = cityRepository.findAll().stream()
                .filter(e -> e.getCountry().getCountry_id() == currentCountryId).collect(Collectors.toList());

        //Filter arrivalCities by wishes
       List<Cities> filteredCitiesByWishes =  getArrivalCitiesByWishes(wishes,arrivalCities);
       if(filteredCitiesByWishes.size() > 0){
           returnedResult.setWasFileterdByWishes(true);
       }


       List<Cities> arrivalPassedCities = filteredCitiesByWishes.size() == 0 ? arrivalCities: filteredCitiesByWishes;

        Long dateBegin = parseToDateTime(BeginDate);
        Long dateTo = parseToDateTime(EndDate);

        LocalDate fromBeginDate = toLocaleDate(BeginDate);
        LocalDate fromEndDate = toLocaleDate(EndDate);
        long numberDays =  fromBeginDate.getDayOfMonth() > fromEndDate.getDayOfMonth() ? fromBeginDate.getDayOfMonth() - fromEndDate.getDayOfMonth() : fromEndDate.getDayOfMonth() - fromBeginDate.getDayOfMonth();

        List<Accomodations> relevantAccomodations = accomodationRepository.getViewData();
        System.out.println("Step 1");
        System.out.println(relevantAccomodations);
        List<Accomodations> relevantAccomodationsByCountry =  new ArrayList<>();

        for(Cities c: arrivalPassedCities){
            for(Accomodations a: relevantAccomodations){
                if(c.getCity_id()==a.getCity().getCity_id()){
                    relevantAccomodationsByCountry.add(a);
                }
            }
        }
        System.out.println("Step 2");
        System.out.println(relevantAccomodationsByCountry);
        List<Accomodations> returnedToViewRelevantAccomodations =  new ArrayList<>();
        Map<Double,Accomodations> costMap =  new HashMap<>();
        Roomtypes returndRoomtype= null;

        //Track history
        List<Integer> resAccIds = new ArrayList<>();
        List<Reservations_accomodations> reservations_accomodations =
                reservationAccomodationRepository.findAll().stream()
                .filter(e -> e.getUser().getId() == userId).collect(Collectors.toList());
        System.out.println("Step 3");
        System.out.println(reservations_accomodations);
        for(Reservations_accomodations r : reservations_accomodations){
            resAccIds.add(r.getAccomodation().getAccomodation_id());
        }

        List<Accomodations> accFilteredByHistory =  new ArrayList<>();
        if(trackHistory == 1){
             accFilteredByHistory =  filterAccByHistory(resAccIds, relevantAccomodationsByCountry);
        }

        List<Accomodations> passedListAcc = accFilteredByHistory.size() == 0 ? relevantAccomodationsByCountry : accFilteredByHistory;

        System.out.println("Step 4");
        System.out.println(passedListAcc);
        for( Accomodations acc : passedListAcc){
            List<Reservations_accomodations> rezAcc =  acc.getRezAccs();
            List<Reservations_accomodations> filteredRa =  buildMostImportantReservationsAccomodations(rezAcc, dateBegin,dateTo);

            int initalSumCount = 0;
            for (Reservations_accomodations rez: filteredRa) {
                initalSumCount += rez.getNo_of_rooms();
            }

           List<Roomtypes> specifiedRoomType =  acc.getRoomType().stream()
                   .filter(e -> e.getType().equals(roomType)).collect(Collectors.toList());
           returndRoomtype = specifiedRoomType.get(0);
            System.out.println("Step 5");
            System.out.println(specifiedRoomType);
            if(initalSumCount < specifiedRoomType.get(0).getNumber_of_rooms()) {

//                if(filteredRa.size() > 0) {
                    double cost = numberOfRooms * specifiedRoomType.get(0).getPrice() * numberDays;
                    if ( budget > cost) {
                        double remainedBudget = budget - cost;
                        costMap.put(cost,acc);
                        returnedToViewRelevantAccomodations.add(acc);
                    }
//                }
            }

        }
        System.out.println("Step 6");
        System.out.println(costMap);

        if(returnedToViewRelevantAccomodations.size() > 0) {
            Accomodations minCostAcc = returnMinimumValueFromMapAcc(costMap);
            double cost = getMinCost(costMap);
            double remainedCost = budget - cost;
            List<Flights> flights =  flightsRepository.findAll();

            System.out.println("Step 7");
            System.out.println(flights);

            List<Flights> filteredFligthsFrom =  getFilterFlightsList(flights, departureCities, arrivalPassedCities);
            Flights relevantFromFlight;
            System.out.println("Step 8");
            System.out.println(filteredFligthsFrom);
            double remainedCostAfterFlightFrom = remainedCost;
            if(getMostSignificantFlight(filteredFligthsFrom,isBussiness, numberOfSeats, dateTo, remainedCost).size() > 0){
                System.out.println("Step 9");
                System.out.println(getMostSignificantFlight(filteredFligthsFrom,isBussiness, numberOfSeats, dateTo, remainedCost).size());
                 relevantFromFlight = returnMinimumValueFromMapFlight(getMostSignificantFlight(filteredFligthsFrom,isBussiness, numberOfSeats, dateTo, remainedCost));
                 remainedCostAfterFlightFrom -= getMinCostFlights(getMostSignificantFlight(filteredFligthsFrom,isBussiness, numberOfSeats, dateTo, remainedCost));
            }else {
                relevantFromFlight = null;
            }

            Flights relevantToFlight;
            List<Flights> filteredFligthsTo =  getFilterFlightsList(flights, arrivalPassedCities, departureCities);
            if(getMostSignificantFlight(filteredFligthsTo,isBussiness, numberOfSeats, dateBegin, remainedCostAfterFlightFrom).size() > 0){
                System.out.println("Step 10");
                System.out.println(getMostSignificantFlight(filteredFligthsTo,isBussiness, numberOfSeats, dateBegin, remainedCostAfterFlightFrom).size());
                 relevantToFlight = returnMinimumValueFromMapFlight(getMostSignificantFlight(filteredFligthsTo,isBussiness, numberOfSeats, dateBegin, remainedCostAfterFlightFrom));
            }else {
                relevantToFlight = null;
            }
            returnedResult.setAccomodations(returnMinimumValueFromMapAcc(costMap));
            returnedResult.setFlightFrom(relevantFromFlight);
            returnedResult.setFlightTo(relevantToFlight);
            returnedResult.setRoomtypes(returndRoomtype);
            System.out.println("Step 11");
            System.out.println(returnMinimumValueFromMapAcc(costMap));
            System.out.println("Step 17");
            System.out.println(returnedResult);
        }

        System.out.println("Step 16");
        System.out.println(returnedResult);
         return returnedResult;
    }

    private Map<Double,Flights> getMostSignificantFlight(List<Flights> flightsList, int isBusiness, int numberOfSeats, long dateBegin, double remainedCost){
        List<Flights> availableFlights = new ArrayList<>();
        Map<Double, Flights> flightsMap = new HashMap<>();
        System.out.println("Step 11");
        System.out.println(flightsList);
        for(Flights f : flightsList) {
            //Filter by date and category of seats
            int numberOfTakenSeats = 0;
            List<Reservations_flights> flightsReservations = f.getRezFligh().stream()
                    .filter(e -> parseToDateTime(e.getFlight_date()) == dateBegin && e.getIsBusiness() == isBusiness)
                    .collect(Collectors.toList());
            for(Reservations_flights rf : flightsReservations) {
                numberOfTakenSeats += rf.getNumberOfSeats();
            }

            if(isBusiness == 1) {
                if(f.getSeats_business() - numberOfTakenSeats >= numberOfSeats){
                    double flightCost = numberOfSeats * f.getPrice_business();
                    if(flightCost < remainedCost) {
                        availableFlights.add(f);
                        flightsMap.put(flightCost, f);
                    }
                }
            }else {
                //if is economic
                if(f.getSeats_economic() - numberOfTakenSeats >= numberOfSeats){
                    double flightCost = numberOfSeats * f.getPrice_economic();
                    if(flightCost < remainedCost) {
                        availableFlights.add(f);
                        flightsMap.put(flightCost, f);
                    }
                }
            }

        }

        System.out.println("Step 12");
        System.out.println(flightsMap);

        System.out.println("Step 13");
        System.out.println(availableFlights);
        //
        return flightsMap;
    }

    public List<Flights> getFilterFlightsList(List<Flights> flights, List<Cities> arrivalCities,List<Cities> departureCities) {
        List<Flights> returnedFlightsList =  new ArrayList<>();
        for(Flights f : flights){
            for(Cities ca : arrivalCities) {
                for(Cities dc: departureCities){
                    if( f.getDeparture_city_id().getCity_id() == dc.getCity_id() &&
                         f.getArrival_city_id().getCity_id() == ca.getCity_id()) {
                        returnedFlightsList.add(f);
                    }

                }
            }
        }
        return returnedFlightsList;
    }
    public List<Reservations_accomodations> buildMostImportantReservationsAccomodations(List<Reservations_accomodations> accomodations, long dateBegin, long dateTo){
        List<Reservations_accomodations> filteredRa = new ArrayList<>();
                    for (Reservations_accomodations rez: accomodations) {
                    if((parseToDateTime(rez.getBegin_time()) >= dateBegin && parseToDateTime(rez.getEnd_time()) >= dateBegin && parseToDateTime(rez.getBegin_time()) <= dateTo &&
                            parseToDateTime(rez.getEnd_time()) >= dateTo) ||
                            (parseToDateTime(rez.getBegin_time()) <= dateBegin && parseToDateTime(rez.getEnd_time()) >= dateBegin && parseToDateTime(rez.getEnd_time()) >= dateBegin &&
                                    parseToDateTime(rez.getEnd_time()) <= dateTo) ||
                            (parseToDateTime(rez.getBegin_time()) <= dateBegin && parseToDateTime(rez.getEnd_time()) >= dateTo) ||(parseToDateTime(rez.getBegin_time()) >= dateBegin &&
                            parseToDateTime(rez.getEnd_time()) <= dateTo) ){
                        filteredRa.add(rez);
                    }
            }
            return filteredRa;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }

    public long parseToDateTime(String date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return df.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 1;
    }
    private Accomodations returnMinimumValueFromMapAcc(Map<Double,Accomodations> map) {
        Map.Entry<Double, Accomodations> minAccomodationCost = Collections.min(map.entrySet(), new Comparator<Map.Entry<Double, Accomodations>>() {
            public int compare(Map.Entry<Double, Accomodations> entry1, Map.Entry<Double, Accomodations> entry2) {
                return entry1.getKey().compareTo(entry2.getKey());
            }
        });

        System.out.println("FINDED ERROR");
        System.out.println(minAccomodationCost.getValue());
        System.out.println(map);
        return minAccomodationCost.getValue();
    }

    private Flights returnMinimumValueFromMapFlight(Map<Double,Flights> map) {
        Map.Entry<Double, Flights> minAccomodationCost = Collections.min(map.entrySet(), new Comparator<Map.Entry<Double, Flights>>() {
            public int compare(Map.Entry<Double, Flights> entry1, Map.Entry<Double, Flights> entry2) {
                return entry1.getKey().compareTo(entry2.getKey());
            }
        });
        System.out.println("Step 15");
        System.out.println(minAccomodationCost.getValue());
        return minAccomodationCost.getValue();
    }

    private double  getMinCost(Map<Double,Accomodations> map) {
        Map.Entry<Double, Accomodations> minAccomodationCost = Collections.min(map.entrySet(), new Comparator<Map.Entry<Double, Accomodations>>() {
            public int compare(Map.Entry<Double, Accomodations> entry1, Map.Entry<Double, Accomodations> entry2) {
                return entry1.getKey().compareTo(entry2.getKey());
            }
        });
        return minAccomodationCost.getKey();
    }

    private double getMinCostFlights(Map<Double,Flights> map) {
        Map.Entry<Double, Flights> minFlightsCost = Collections.min(map.entrySet(), new Comparator<Map.Entry<Double, Flights>>() {
            public int compare(Map.Entry<Double, Flights> entry1, Map.Entry<Double, Flights> entry2) {
                return entry1.getKey().compareTo(entry2.getKey());
            }
        });
        return minFlightsCost.getKey();
    }

}
