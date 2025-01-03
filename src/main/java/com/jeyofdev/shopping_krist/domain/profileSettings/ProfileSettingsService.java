package com.jeyofdev.shopping_krist.domain.profileSettings;

import com.jeyofdev.shopping_krist.core.abstracts.AbstractDomainServiceBase;
import com.jeyofdev.shopping_krist.domain.profile.Profile;
import com.jeyofdev.shopping_krist.domain.profile.ProfileRepository;
import com.jeyofdev.shopping_krist.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.UUID;

@Service
public class ProfileSettingsService extends AbstractDomainServiceBase<ProfileSettings, ProfileSettingsRepository> {
    private final ProfileSettingsRepository profileSettingsRepository;
    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileSettingsService(ProfileSettingsRepository profileSettingsRepository, ProfileRepository profileRepository) {
        super(profileSettingsRepository, "profileSettings");
        this.profileSettingsRepository = profileSettingsRepository;
        this.profileRepository = profileRepository;
    }

    @Transactional
    public ProfileSettings save(ProfileSettings profileSettings, UUID profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(
                        () -> new NotFoundException(MessageFormat.format("Profile with id {0} not found", profileId))
                );

        profileSettings.setProfile(profile);
        profile.setProfileSettings(profileSettings);
        return profileSettingsRepository.save(profileSettings);
    }

    public ProfileSettings updateById(UUID profileSettingsId, ProfileSettings updatedProfileSettings) {
        ProfileSettings existingProfileSettings = findById(profileSettingsId);
        ProfileSettings existingProfileSettingsUpdated = ProfileSettings.builder()
                .id(profileSettingsId)
                .appearance(updatedProfileSettings.getAppearance() != null ? updatedProfileSettings.getAppearance() : existingProfileSettings.getAppearance())
                .isPushNotification(updatedProfileSettings.isPushNotification())
                .isEmailNotification(updatedProfileSettings.isEmailNotification())
                .build();

        return profileSettingsRepository.save(existingProfileSettingsUpdated);
    }

    @Transactional
    public void deleteById(UUID profileSettingsId) {
        ProfileSettings profileSettings = findById(profileSettingsId);
        Profile profile = profileSettings.getProfile();

        if (profile != null) {
            profile.setProfileSettings(null);
            profileRepository.save(profile);
        }

        profileSettingsRepository.deleteById(profileSettingsId);
    }
}
