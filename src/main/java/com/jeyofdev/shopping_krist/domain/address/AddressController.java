package com.jeyofdev.shopping_krist.domain.address;

import com.jeyofdev.shopping_krist.domain.address.dto.AddressDTO;
import com.jeyofdev.shopping_krist.domain.address.dto.SaveAddressDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    private final AddressDomainMapper addressMapper;

    @GetMapping
    public ResponseEntity<List<AddressDTO>> findAllAddress() {
        List<Address> addressList = addressService.findAll();
        List<AddressDTO> addressDTOList = addressList.stream().map(addressMapper::mapFromEntity).toList();

        return new ResponseEntity<>(addressDTOList, HttpStatus.OK);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressDTO> findAddressById(@PathVariable("addressId") UUID addressId) {
        Address address = addressService.findById(addressId);
        AddressDTO addressDTO = addressMapper.mapFromEntity(address);

        return new ResponseEntity<>(addressDTO, HttpStatus.OK);
    }

    @PostMapping("/city/{cityId}/profile/{profileId}")
    public ResponseEntity<AddressDTO> saveAddress(
            @RequestBody SaveAddressDTO saveAddressDTO,
            @PathVariable("cityId") UUID cityId,
            @PathVariable("profileId") UUID profileId
    ) {
        Address address = addressMapper.mapToEntity(saveAddressDTO);
        Address newAddress = addressService.save(address, cityId, profileId);
        AddressDTO newAddressDTO = addressMapper.mapFromEntity(newAddress);

        return new ResponseEntity<>(newAddressDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressDTO> updateAddressById(
            @PathVariable("addressId") UUID addressId,
            @RequestBody SaveAddressDTO saveAddressDTO
    ) {
        Address address = addressMapper.mapToEntity(saveAddressDTO);
        Address updateAddress = addressService.updateById(addressId, address);
        AddressDTO updateAddressDTO = addressMapper.mapFromEntity(updateAddress);

        return new ResponseEntity<>(updateAddressDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable("addressId") UUID addressId) {
        addressService.deleteById(addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
