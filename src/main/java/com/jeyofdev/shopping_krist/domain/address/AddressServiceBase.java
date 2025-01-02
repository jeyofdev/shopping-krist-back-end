package com.jeyofdev.shopping_krist.domain.address;

import com.jeyofdev.shopping_krist.core.abstracts.AbstractDomainServiceBase;
import com.jeyofdev.shopping_krist.domain.city.City;
import com.jeyofdev.shopping_krist.domain.city.CityRepository;
import com.jeyofdev.shopping_krist.domain.profile.Profile;
import com.jeyofdev.shopping_krist.domain.profile.ProfileRepository;
import com.jeyofdev.shopping_krist.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.UUID;

@Service
public class AddressServiceBase extends AbstractDomainServiceBase<Address, AddressRepository> {
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final ProfileRepository profileRepository;

    @Autowired
    public AddressServiceBase(AddressRepository addressRepository, CityRepository cityRepository, ProfileRepository profileRepository) {
        super(addressRepository, "address");
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
        this.profileRepository = profileRepository;
    }

    public Address save(Address address, UUID cityId, UUID profileId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(
                        () -> new NotFoundException(MessageFormat.format("City with id {0} not found", cityId))
                );

        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(
                        () -> new NotFoundException(MessageFormat.format("Profile with id {0} not found", profileId))
                );

        address.setCity(city);
        address.setProfile(profile);
        return addressRepository.save(address);
    }

    public Address updateById(UUID addressId, Address updatedAddress) {
        Address existingAddress = findById(addressId);
        Address existingAddressUpdated = Address.builder()
                .id(addressId)
                .name(updatedAddress.getName() != null ? updatedAddress.getName() : existingAddress.getName())
                .phone(updatedAddress.getPhone() != null ? updatedAddress.getPhone() : existingAddress.getPhone())
                .streetNumber(updatedAddress.getStreetNumber() != null ? updatedAddress.getStreetNumber() : existingAddress.getStreetNumber())
                .street(updatedAddress.getStreet() != null ? updatedAddress.getStreet() : existingAddress.getStreet())
                .zipCode(updatedAddress.getZipCode() != null ? updatedAddress.getZipCode() : existingAddress.getZipCode())
                .city(existingAddress.getCity())
                .profile(existingAddress.getProfile())
                .build();

        return addressRepository.save(existingAddressUpdated);
    }

    @Transactional
    public String deleteById(UUID addressId) {
        findById(addressId);
        addressRepository.deleteById(addressId);

        return "Your account has been successfully deleted.";
    }
}
