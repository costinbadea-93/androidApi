package androidApi.service;

import androidApi.model.Accomodations;
import androidApi.model.Reservations_accomodations;
import androidApi.model.User;
import androidApi.repository.AccomodationRepository;
import androidApi.repository.ReservationAccomodationRepository;
import androidApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Reservation_accService {

    @Autowired
    ReservationAccomodationRepository reservationAccomodationRepository;

    @Autowired
    AccomodationRepository accomodationRepository;

    @Autowired
    UserRepository userRepository;

    public Reservations_accomodations addReservation(Reservations_accomodations reservations_accomodations, int accId, int userId){
        Accomodations accomodations =  accomodationRepository.findOne(accId);
        User user = userRepository.findOne(userId);

        reservations_accomodations.setAccomodation(accomodations);
        reservations_accomodations.setUser(user);

        return reservationAccomodationRepository.save(reservations_accomodations);
    }
}
