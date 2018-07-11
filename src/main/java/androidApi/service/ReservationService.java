package androidApi.service;

import androidApi.dto.FullReservationDTO;
import androidApi.dto.GetReservationsDTO;
import androidApi.dto.ItineraryDTO;
import androidApi.model.*;
import androidApi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService{

    @Autowired
    ReservationAccomodationRepository reservationAccomodationRepository;

    @Autowired
    ReservationFligthRepository reservationFligthRepository;

    @Autowired
    AccomodationRepository accomodationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FlightsRepository flightsRepository;

    public Reservations_accomodations addReservation(Reservations_accomodations reservations_accomodations, int accId, int userId){
        Accomodations accomodations =  accomodationRepository.findOne(accId);
        User user = userRepository.findOne(userId);

        reservations_accomodations.setAccomodation(accomodations);
        reservations_accomodations.setUser(user);

        return reservationAccomodationRepository.save(reservations_accomodations);
    }

    public Reservations_flights addResFlight(Reservations_flights reservations_flights, int flightId, int userId){
        Flights flights =  flightsRepository.findOne(flightId);
        User user = userRepository.findOne(userId);

        reservations_flights.setFlight(flights);
        reservations_flights.setUser(user);

        return reservationFligthRepository.save(reservations_flights);
    }

    public GetReservationsDTO getReservationAcc(int userId){
        GetReservationsDTO getReservationsDTOS = new GetReservationsDTO();
        List<Reservations_accomodations> reservationsAcc = reservationAccomodationRepository.findAll();
        List<Reservations_flights> reservations_flights =  reservationFligthRepository.findAll();
        List<ItineraryDTO> itineraryDTOSList = new ArrayList<>();
        for (Reservations_accomodations resA: reservationsAcc){
            for (Reservations_flights resF: reservations_flights) {
                if (resA.getBegin_time().getTime() == resF.getFlight_date().getTime() ||
                        resA.getBegin_time().getTime() == resF.getFlight_date().getTime()){
                    ItineraryDTO itineraryDTO = new ItineraryDTO();
                    itineraryDTO.setReservations_accomodations(resA);
                    itineraryDTO.setReservations_flights(resF);
                    itineraryDTOSList.add(itineraryDTO);
                }
            }
        }
        getReservationsDTOS.setReservationsAccomodations(reservationsAcc);
        getReservationsDTOS.setReservations_flights(reservations_flights);
        getReservationsDTOS.setItineraryDTOS(itineraryDTOSList);

       return getReservationsDTOS;
    }
}
