package com.jeyofdev.shopping_krist.domain.profile;

import com.jeyofdev.shopping_krist.domain.profile.dto.ProfileDTO;
import com.jeyofdev.shopping_krist.domain.profile.dto.SaveProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileServiceBase profileService;
    private final ProfileDomainMapper profileMapper;

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileDTO> findProfileById(@PathVariable("profileId") UUID profileId) {
        Profile profile = profileService.findById(profileId);
        ProfileDTO profileDTO = profileMapper.mapFromEntity(profile);

        return new ResponseEntity<>(profileDTO, HttpStatus.OK);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<ProfileDTO> saveProfile(
            @RequestBody SaveProfileDTO saveProfileDTO,
            @PathVariable UUID userId
    ) {
        Profile profile = profileMapper.mapToEntity(saveProfileDTO);
        Profile newProfile = profileService.save(profile, userId);
        ProfileDTO newProfileDTO = profileMapper.mapFromEntity(newProfile);

        return new ResponseEntity<>(newProfileDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<ProfileDTO> updateProfileById(
            @PathVariable("profileId") UUID profileId,
            @RequestBody SaveProfileDTO saveProfileDTO
    ) {
        Profile profile = profileMapper.mapToEntity(saveProfileDTO);
        Profile updateProfile = profileService.updateById(profileId, profile);
        ProfileDTO updateProfileDTO = profileMapper.mapFromEntity(updateProfile);

        return new ResponseEntity<>(updateProfileDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<String> deleteProfileById(@PathVariable("profileId") UUID profileId) {
        String deletedProfile = profileService.deleteById(profileId);
        return new ResponseEntity<>(deletedProfile, HttpStatus.NO_CONTENT);
    }
}
