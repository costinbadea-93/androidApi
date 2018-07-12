package androidApi.service;

import androidApi.model.*;
import androidApi.repository.AccomodationRepository;
import androidApi.repository.CountriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccomodationService {

    @Autowired
    AccomodationRepository accomodationRepository;

    @Autowired
    CountriesRepository countriesRepository;

    public List<Accomodations> getAccomodations(String country, String d1, String d2, int numberOfRooms, String roomType) {
        Countries countries = countriesRepository.findByName(country);
        List<Cities> cities = countries.getCities();
        List<Accomodations> accomodations = accomodationRepository.findAll();
        List<Accomodations> filteredAccListByCity = new ArrayList<>();
        List<Accomodations> returnedAcc = new ArrayList<>();

        d1 = "22/08/2018";
        d2 = "26/08/2018";

        Long dateBegin = parseToDateTime(d1);
        Long dateTo = parseToDateTime(d2);

        for (Accomodations acc : accomodations) {
            for (Cities ci : cities) {
                if (acc.getCity().getCity_id() == ci.getCity_id()) {
                    filteredAccListByCity.add(acc);
                }
            }
        }

        for (Accomodations acc : filteredAccListByCity) {
            List<Reservations_accomodations> rezAcc = acc.getRezAccs();
            List<Reservations_accomodations> filteredRa = buildMostImportantReservationsAccomodations(rezAcc, dateBegin, dateTo);
            int initalSumCount = 0;
            for (Reservations_accomodations rez : filteredRa) {
                initalSumCount += rez.getNo_of_rooms();
            }

            List<Roomtypes> specifiedRoomType = acc.getRoomType().stream()
                    .filter(e -> e.getType().equals(roomType)).collect(Collectors.toList());
            if (initalSumCount < specifiedRoomType.get(0).getNumber_of_rooms()) {
                if (filteredRa.size() > 0) {
                    returnedAcc.add(acc);
                }
            }
        }
        return returnedAcc;
    }

    private long parseToDateTime(String date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return df.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 1;
    }

    private List<Reservations_accomodations> buildMostImportantReservationsAccomodations(List<Reservations_accomodations> accomodations, long dateBegin, long dateTo) {
        List<Reservations_accomodations> filteredRa = new ArrayList<>();
        for (Reservations_accomodations rez : accomodations) {
            if ((parseToDateTime(rez.getBegin_time()) >= dateBegin && parseToDateTime(rez.getEnd_time()) >= dateBegin && parseToDateTime(rez.getBegin_time()) <= dateTo &&
                    parseToDateTime(rez.getEnd_time()) >= dateTo) ||
                    (parseToDateTime(rez.getBegin_time()) <= dateBegin && parseToDateTime(rez.getEnd_time()) >= dateBegin && parseToDateTime(rez.getEnd_time()) >= dateBegin &&
                            parseToDateTime(rez.getEnd_time()) <= dateTo) ||
                    (parseToDateTime(rez.getBegin_time()) <= dateBegin && parseToDateTime(rez.getEnd_time()) >= dateTo) || (parseToDateTime(rez.getBegin_time()) >= dateBegin &&
                    parseToDateTime(rez.getEnd_time()) <= dateTo)) {
                filteredRa.add(rez);
            }
        }
        return filteredRa;
    }
}
