package androidApi.service;

import androidApi.model.Cities;
import androidApi.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;

    public List<Cities> getCities() {
        return cityRepository.findAll();
    }
}
