package com.jeyofdev.shopping_krist.domain.profileSettings;

import com.jeyofdev.shopping_krist.domain.profileSettings.dto.ProfileSettingsDTO;
import com.jeyofdev.shopping_krist.domain.profileSettings.dto.SaveProfileSettingsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profile/settings")
@RequiredArgsConstructor
public class ProfileSettingsController {
    private final ProfileSettingsService profileSettingsService;
    private final ProfileSettingsMapper profileSettingsMapper;

    @GetMapping
    public ResponseEntity<List<ProfileSettingsDTO>> findAllProfileSettings() {
        List<ProfileSettings> profileSettingsList = profileSettingsService.findAll();
        List<ProfileSettingsDTO> profileSettingsDTOList = profileSettingsList.stream().map(profileSettingsMapper::mapFromEntity).toList();

        return new ResponseEntity<>(profileSettingsDTOList, HttpStatus.OK);
    }

    @GetMapping("/{profileSettingsId}")
    public ResponseEntity<ProfileSettingsDTO> findProfileSettingsById(@PathVariable("profileSettingsId") UUID profileSettingsId) {
        ProfileSettings profileSettings = profileSettingsService.findById(profileSettingsId);
        ProfileSettingsDTO profileSettingsDTO = profileSettingsMapper.mapFromEntity(profileSettings);

        return new ResponseEntity<>(profileSettingsDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ProfileSettingsDTO> saveProfileSettings(
            @RequestBody SaveProfileSettingsDTO saveProfileSettingsDTO
    ) {
        ProfileSettings profileSettings = profileSettingsMapper.mapToEntity(saveProfileSettingsDTO);
        ProfileSettings newProfileSettings = profileSettingsService.save(profileSettings);
        ProfileSettingsDTO newProfileSettingsDTO = profileSettingsMapper.mapFromEntity(newProfileSettings);

        return new ResponseEntity<>(newProfileSettingsDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{profileSettingsId}")
    public ResponseEntity<ProfileSettingsDTO> updateProfileSettingsById(
            @PathVariable("profileSettingsId") UUID profileSettingsId,
            @RequestBody SaveProfileSettingsDTO saveProfileSettingsDTO
    ) {
        ProfileSettings profileSettings = profileSettingsMapper.mapToEntity(saveProfileSettingsDTO);
        ProfileSettings updateProfileSettings = profileSettingsService.updateById(profileSettingsId, profileSettings);
        ProfileSettingsDTO updateProfileSettingsDTO = profileSettingsMapper.mapFromEntity(updateProfileSettings);

        return new ResponseEntity<>(updateProfileSettingsDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{profileSettingsId}")
    public ResponseEntity<Void> deleteProfileSettingsById(@PathVariable("profileSettingsId") UUID profileSettingsId) {
        profileSettingsService.deleteById(profileSettingsId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
