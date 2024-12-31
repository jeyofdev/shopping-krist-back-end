package com.jeyofdev.shopping_krist.domain.profileSettings;

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
public class ProfileSettingsService {
    private final ProfileSettingsRepository profileSettingsRepository;

    public List<ProfileSettings> findAll() {
        return profileSettingsRepository.findAll();
    }

    public ProfileSettings findById(@PathVariable("profileDetailsId") UUID profileDetailsId) throws NotFoundException {
        return profileSettingsRepository.findById(profileDetailsId).orElseThrow(
                () -> new NotFoundException(MessageFormat.format(" Entity ProfileSettings with id {0} cannot be found", profileDetailsId)));
    }

    public ProfileSettings save(ProfileSettings profile) {
        return profileSettingsRepository.save(profile);
    }

    public ProfileSettings updateById(UUID profileDetailsId, ProfileSettings updatedProfileSettings) {
        ProfileSettings existingProfileSettings = findById(profileDetailsId);
        ProfileSettings existingProfileSettingsUpdated = ProfileSettings.builder()
                .id(profileDetailsId)
                .appearance(updatedProfileSettings.getAppearance() != null ? updatedProfileSettings.getAppearance() : existingProfileSettings.getAppearance())
                .isPushNotification(updatedProfileSettings.isPushNotification())
                .isEmailNotification(updatedProfileSettings.isEmailNotification())
                .build();

        return profileSettingsRepository.save(existingProfileSettingsUpdated);
    }

    @Transactional
    public void deleteById(UUID profileDetailsId) {
        findById(profileDetailsId);
        profileSettingsRepository.deleteById(profileDetailsId);
    }
}
