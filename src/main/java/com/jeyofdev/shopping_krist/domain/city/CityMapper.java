package com.jeyofdev.shopping_krist.domain.city;

import com.jeyofdev.shopping_krist.domain.city.dto.CityDTO;
import com.jeyofdev.shopping_krist.domain.city.dto.SaveCityDTO;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {
    public CityDTO mapFromEntity(City city) {
        return new CityDTO(
                city.getId(),
                city.getName(),
                city.getZipCode()
        );
    }

    public City mapToEntity(SaveCityDTO saveCityDTO) {
        return City.builder()
                .name(saveCityDTO.name())
                .zipCode(saveCityDTO.zipCode())
                .build();
    }
}
