package com.jeyofdev.shopping_krist.domain.city;

import com.jeyofdev.shopping_krist.domain.city.dto.CityDTO;
import com.jeyofdev.shopping_krist.domain.city.dto.SaveCityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/city")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    private final CityDomainMapper cityMapper;

    @GetMapping
    public ResponseEntity<List<CityDTO>> findAllCity() {
        List<City> cityList = cityService.findAll();
        List<CityDTO> cityDTOList = cityList.stream().map(cityMapper::mapFromEntity).toList();

        return new ResponseEntity<>(cityDTOList, HttpStatus.OK);
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<CityDTO> findCityById(@PathVariable("cityId") UUID cityId) {
        City city = cityService.findById(cityId);
        CityDTO cityDTO = cityMapper.mapFromEntity(city);

        return new ResponseEntity<>(cityDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CityDTO> saveCity(@RequestBody SaveCityDTO saveCityDTO) {
        City city = cityMapper.mapToEntity(saveCityDTO);
        City newCity = cityService.save(city);
        CityDTO newCityDTO = cityMapper.mapFromEntity(newCity);

        return new ResponseEntity<>(newCityDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<CityDTO> updateCityById(
            @PathVariable("cityId") UUID cityId,
            @RequestBody SaveCityDTO saveCityDTO
    ) {
        City city = cityMapper.mapToEntity(saveCityDTO);
        City updateCity = cityService.updateById(cityId, city);
        CityDTO updateCityDTO = cityMapper.mapFromEntity(updateCity);

        return new ResponseEntity<>(updateCityDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<String> deleteCityById(@PathVariable("cityId") UUID cityId) {
        String deletedCity = cityService.deleteById(cityId);
        return new ResponseEntity<>(deletedCity, HttpStatus.NO_CONTENT);
    }
}
