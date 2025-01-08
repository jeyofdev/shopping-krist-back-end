package com.jeyofdev.shopping_krist.domain.profile;

import com.jeyofdev.shopping_krist.core.models.DomainSuccessResponse;
import com.jeyofdev.shopping_krist.domain.profile.dto.ProfileDTO;
import com.jeyofdev.shopping_krist.domain.profile.dto.SaveProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final ProfileMapper profileMapper;

    @GetMapping
    public ResponseEntity<DomainSuccessResponse<List<ProfileDTO>>> findAllProfile() {
        List<Profile> profileList = profileService.findAll();
        List<ProfileDTO> profileListDTOList = profileList.stream().map(profileMapper::mapFromEntity).toList();

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, profileListDTOList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<DomainSuccessResponse<ProfileDTO>> findProfileById(@PathVariable("profileId") UUID profileId) {
        Profile profile = profileService.findById(profileId);
        ProfileDTO profileDTO = profileMapper.mapFromEntity(profile);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, profileDTO),
                HttpStatus.OK
        );
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<DomainSuccessResponse<ProfileDTO>> saveProfile(
            @RequestBody SaveProfileDTO saveProfileDTO,
            @PathVariable UUID userId
    ) {
        Profile profile = profileMapper.mapToEntity(saveProfileDTO);
        Profile newProfile = profileService.save(profile, userId);
        ProfileDTO newProfileDTO = profileMapper.mapFromEntity(newProfile);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.CREATED, newProfileDTO),
                HttpStatus.OK
        );
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<DomainSuccessResponse<ProfileDTO>> updateProfileById(
            @PathVariable("profileId") UUID profileId,
            @RequestBody SaveProfileDTO saveProfileDTO
    ) {
        Profile profile = profileMapper.mapToEntity(saveProfileDTO);
        Profile updateProfile = profileService.updateById(profileId, profile);
        ProfileDTO updateProfileDTO = profileMapper.mapFromEntity(updateProfile);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, updateProfileDTO),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<DomainSuccessResponse<Object>> deleteProfileById(@PathVariable("profileId") UUID profileId) {
        String message = profileService.deleteById(profileId);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, message),
                HttpStatus.OK
        );
    }
}
