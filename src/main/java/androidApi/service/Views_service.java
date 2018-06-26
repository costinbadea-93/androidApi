package androidApi.service;

import androidApi.model.Cities;
import androidApi.model.View_Cities;
import androidApi.repository.CountriesRepository;
import androidApi.repository.View_CitiesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Views_service {

    @Autowired
    View_CitiesRepository view_citiesRepository;

    @Autowired
    CountriesRepository countriesRepository;

    public List<View_Cities> getViewCitiesData() {
        ModelMapper modelMapper =  new ModelMapper();
        List<Cities> databaseData =  countriesRepository.getViewData();
        List<View_Cities> returnedList = new ArrayList<>();
        databaseData.forEach(e -> {
            returnedList.add(modelMapper.map(e, View_Cities.class));
        });
        return returnedList;
    }
}
