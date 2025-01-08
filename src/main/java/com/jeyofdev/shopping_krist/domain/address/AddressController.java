package com.jeyofdev.shopping_krist.domain.address;

import com.jeyofdev.shopping_krist.core.models.DomainSuccessResponse;
import com.jeyofdev.shopping_krist.domain.address.dto.AddressDTO;
import com.jeyofdev.shopping_krist.domain.address.dto.SaveAddressDTO;
import com.jeyofdev.shopping_krist.domain.city.dto.CityDTO;
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
    private final AddressMapper addressMapper;

    @GetMapping
    public ResponseEntity<DomainSuccessResponse<List<AddressDTO>>> findAllAddress() {
        List<Address> addressList = addressService.findAll();
        List<AddressDTO> addressDTOList = addressList.stream().map(addressMapper::mapFromEntity).toList();

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, addressDTOList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<DomainSuccessResponse<AddressDTO>> findAddressById(@PathVariable("addressId") UUID addressId) {
        Address address = addressService.findById(addressId);
        AddressDTO addressDTO = addressMapper.mapFromEntity(address);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, addressDTO),
                HttpStatus.OK
        );
    }

    @PostMapping("/city/{cityId}/profile/{profileId}")
    public ResponseEntity<DomainSuccessResponse<AddressDTO>> saveAddress(
            @RequestBody SaveAddressDTO saveAddressDTO,
            @PathVariable("cityId") UUID cityId,
            @PathVariable("profileId") UUID profileId
    ) {
        Address address = addressMapper.mapToEntity(saveAddressDTO);
        Address newAddress = addressService.save(address, cityId, profileId);
        AddressDTO newAddressDTO = addressMapper.mapFromEntity(newAddress);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.CREATED, newAddressDTO),
                HttpStatus.OK
        );
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<DomainSuccessResponse<AddressDTO>> updateAddressById(
            @PathVariable("addressId") UUID addressId,
            @RequestBody SaveAddressDTO saveAddressDTO
    ) {
        Address address = addressMapper.mapToEntity(saveAddressDTO);
        Address updateAddress = addressService.updateById(addressId, address);
        AddressDTO updateAddressDTO = addressMapper.mapFromEntity(updateAddress);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, updateAddressDTO),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<DomainSuccessResponse<Object>> deleteAddressById(@PathVariable("addressId") UUID addressId) {
        String message = addressService.deleteById(addressId);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, message),
                HttpStatus.OK
        );
    }
}
