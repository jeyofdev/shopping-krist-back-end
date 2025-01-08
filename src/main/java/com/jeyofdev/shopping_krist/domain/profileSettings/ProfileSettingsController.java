package com.jeyofdev.shopping_krist.domain.profileSettings;

import com.jeyofdev.shopping_krist.core.constants.ApiRoutes;
import com.jeyofdev.shopping_krist.core.models.DomainSuccessResponse;
import com.jeyofdev.shopping_krist.domain.profileSettings.dto.ProfileSettingsDTO;
import com.jeyofdev.shopping_krist.domain.profileSettings.dto.SaveProfileSettingsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiRoutes.BASE_API_V1 + ApiRoutes.PROFILE_SETTINGS)
@RequiredArgsConstructor
public class ProfileSettingsController {
    private final ProfileSettingsService profileSettingsService;
    private final ProfileSettingsMapper profileSettingsMapper;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DomainSuccessResponse<List<ProfileSettingsDTO>>> findAllProfileSettings() {
        List<ProfileSettings> profileSettingsList = profileSettingsService.findAll();
        List<ProfileSettingsDTO> profileSettingsDTOList = profileSettingsList.stream().map(profileSettingsMapper::mapFromEntity).toList();

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, profileSettingsDTOList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{profileSettingsId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<DomainSuccessResponse<ProfileSettingsDTO>> findProfileSettingsById(@PathVariable("profileSettingsId") UUID profileSettingsId) {
        ProfileSettings profileSettings = profileSettingsService.findById(profileSettingsId);
        ProfileSettingsDTO profileSettingsDTO = profileSettingsMapper.mapFromEntity(profileSettings);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, profileSettingsDTO),
                HttpStatus.OK
        );
    }

    @PostMapping(ApiRoutes.PROFILE + "/{profileId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<DomainSuccessResponse<ProfileSettingsDTO>> saveProfileSettings(
            @RequestBody SaveProfileSettingsDTO saveProfileSettingsDTO,
            @PathVariable UUID profileId
    ) {
        ProfileSettings profileSettings = profileSettingsMapper.mapToEntity(saveProfileSettingsDTO);
        ProfileSettings newProfileSettings = profileSettingsService.save(profileSettings, profileId);
        ProfileSettingsDTO newProfileSettingsDTO = profileSettingsMapper.mapFromEntity(newProfileSettings);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.CREATED, newProfileSettingsDTO),
                HttpStatus.OK
        );
    }

    @PutMapping("/{profileSettingsId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<DomainSuccessResponse<ProfileSettingsDTO>> updateProfileSettingsById(
            @PathVariable("profileSettingsId") UUID profileSettingsId,
            @RequestBody SaveProfileSettingsDTO saveProfileSettingsDTO
    ) {
        ProfileSettings profileSettings = profileSettingsMapper.mapToEntity(saveProfileSettingsDTO);
        ProfileSettings updateProfileSettings = profileSettingsService.updateById(profileSettingsId, profileSettings);
        ProfileSettingsDTO updateProfileSettingsDTO = profileSettingsMapper.mapFromEntity(updateProfileSettings);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, updateProfileSettingsDTO),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{profileSettingsId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<DomainSuccessResponse<Object>> deleteProfileSettingsById(@PathVariable("profileSettingsId") UUID profileSettingsId) {
        String message = profileSettingsService.deleteById(profileSettingsId);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, message),
                HttpStatus.OK
        );
    }
}
