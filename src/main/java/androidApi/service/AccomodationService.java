package androidApi.service;

import androidApi.model.Accomodations;
import androidApi.repository.AccomodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccomodationService {

    @Autowired
    AccomodationRepository accomodationRepository;

    public List<Accomodations> getAccomodations() {
        return accomodationRepository.findAll();
    }
}
