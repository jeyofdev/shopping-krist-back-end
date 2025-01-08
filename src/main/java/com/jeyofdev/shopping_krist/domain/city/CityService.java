package com.jeyofdev.shopping_krist.domain.city;

import com.jeyofdev.shopping_krist.core.abstracts.AbstractDomainServiceBase;
import com.jeyofdev.shopping_krist.domain.address.Address;
import com.jeyofdev.shopping_krist.domain.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CityService extends AbstractDomainServiceBase<City, CityRepository> {
    private final CityRepository cityRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public CityService(CityRepository cityRepository, AddressRepository addressRepository) {
        super(cityRepository, "city");
        this.cityRepository = cityRepository;
        this.addressRepository = addressRepository;
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

    @Override
    @Transactional
    public String deleteById(UUID cityId) {
        City city = findById(cityId);
        List<Address> addresses = addressRepository.findByCity(city);

        for (Address address : addresses) {
            address.setCity(null);
            addressRepository.save(address);
        }
        cityRepository.deleteById(cityId);

        return "The city with id %s has been successfully deleted.".formatted(cityId);
    }
}
