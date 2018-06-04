package androidApi.service;

import androidApi.model.Flights;
import androidApi.repository.FlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightsService {
    @Autowired
    FlightsRepository flightsRepository;

    public List<Flights> getFlights() {
        return flightsRepository.findAll();
    }
}
