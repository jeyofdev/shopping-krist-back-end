package com.jeyofdev.shopping_krist.domain.city;

import com.jeyofdev.shopping_krist.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public City findById(@PathVariable("cityId") UUID cityId) throws NotFoundException {
        return cityRepository.findById(cityId).orElseThrow(
                () -> new NotFoundException(MessageFormat.format(" Entity City with id {0} cannot be found", cityId)));
    }

    public City save(City city) {
        return cityRepository.save(city);
    }

    public City updateById(UUID cityId, City updatedCity) {
        City existingCity = findById(cityId);
        City existingCityUpdated = City.builder()
                .id(cityId)
                .name(updatedCity.getName() != null ? updatedCity.getName() : existingCity.getName())
                .zipCode(updatedCity.getZipCode() != null ? updatedCity.getZipCode() : existingCity.getZipCode())
                .build();

        return cityRepository.save(existingCityUpdated);
    }

    @Transactional
    public String deleteById(UUID cityId) {
        findById(cityId);
        cityRepository.deleteById(cityId);

        return "Your account has been successfully deleted.";
    }
}
