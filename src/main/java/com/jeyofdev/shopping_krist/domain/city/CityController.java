package com.jeyofdev.shopping_krist.domain.city;

import com.jeyofdev.shopping_krist.core.constants.ApiRoutes;
import com.jeyofdev.shopping_krist.core.models.DomainSuccessResponse;
import com.jeyofdev.shopping_krist.domain.city.dto.CityDTO;
import com.jeyofdev.shopping_krist.domain.city.dto.SaveCityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiRoutes.BASE_API_V1 + ApiRoutes.CITY)
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    private final CityMapper cityMapper;

    @GetMapping
    public ResponseEntity<DomainSuccessResponse<List<CityDTO>>> findAllCity() {
        List<City> cityList = cityService.findAll();
        List<CityDTO> cityDTOList = cityList.stream().map(cityMapper::mapFromEntity).toList();

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, cityDTOList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<DomainSuccessResponse<CityDTO>> findCityById(@PathVariable("cityId") UUID cityId) {
        City city = cityService.findById(cityId);
        CityDTO cityDTO = cityMapper.mapFromEntity(city);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, cityDTO),
                HttpStatus.OK
        );
    }

    @PostMapping()
    public ResponseEntity<DomainSuccessResponse<CityDTO>> saveCity(@RequestBody SaveCityDTO saveCityDTO) {
        City city = cityMapper.mapToEntity(saveCityDTO);
        City newCity = cityService.save(city);
        CityDTO newCityDTO = cityMapper.mapFromEntity(newCity);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.CREATED, newCityDTO),
                HttpStatus.OK
        );
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<DomainSuccessResponse<CityDTO>> updateCityById(
            @PathVariable("cityId") UUID cityId,
            @RequestBody SaveCityDTO saveCityDTO
    ) {
        City city = cityMapper.mapToEntity(saveCityDTO);
        City updateCity = cityService.updateById(cityId, city);
        CityDTO updateCityDTO = cityMapper.mapFromEntity(updateCity);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, updateCityDTO),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<DomainSuccessResponse<Object>> deleteCityById(@PathVariable("cityId") UUID cityId) {
        String message = cityService.deleteById(cityId);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, message),
                HttpStatus.OK
        );
    }
}
