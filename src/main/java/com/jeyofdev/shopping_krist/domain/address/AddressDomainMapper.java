package com.jeyofdev.shopping_krist.domain.address;

import com.jeyofdev.shopping_krist.core.interfaces.mapper.IDomainMapper;
import com.jeyofdev.shopping_krist.domain.address.dto.AddressDTO;
import com.jeyofdev.shopping_krist.domain.address.dto.SaveAddressDTO;
import com.jeyofdev.shopping_krist.format.AddressFormat;
import org.springframework.stereotype.Component;

@Component
public class AddressDomainMapper implements IDomainMapper<Address, AddressDTO, SaveAddressDTO> {
    @Override
    public AddressDTO mapFromEntity(Address address) {
        return new AddressDTO(
                address.getId(),
                address.getName(),
                address.getPhone(),
                getAddressResponse(address)
        );
    }

    @Override
    public Address mapToEntity(SaveAddressDTO saveAddressDTO) {
        return Address.builder()
                .name(saveAddressDTO.name())
                .phone(saveAddressDTO.phone())
                .streetNumber(saveAddressDTO.streetNumber())
                .street(saveAddressDTO.street())
                .street(saveAddressDTO.street())
                .zipCode(saveAddressDTO.zipCode())
                .build();
    }

    private AddressFormat getAddressResponse(Address address) {
        return AddressFormat.builder()
                .streetNumber(address.getStreetNumber())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .city(address.getCity() != null ? address.getCity().getName() : null)
                .build();
    }
}
