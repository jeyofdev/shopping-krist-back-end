package com.jeyofdev.shopping_krist.domain.address;

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
public class AddressService {
    private final AddressRepository addressRepository;

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address findById(@PathVariable("addressId") UUID addressId) throws NotFoundException {
        return addressRepository.findById(addressId).orElseThrow(
                () -> new NotFoundException(MessageFormat.format(" Entity Address with id {0} cannot be found", addressId)));
    }

    public Address save(Address address) {
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
