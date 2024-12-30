package com.jeyofdev.shopping_krist.domain.profile;

import com.jeyofdev.shopping_krist.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public Profile findById(@PathVariable("profileId") UUID profileId) throws NotFoundException {
        return profileRepository.findById(profileId).orElseThrow(
                () -> new NotFoundException(MessageFormat.format(" Entity Profile with id {0} cannot be found", profileId)));
    }

    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile updateById(UUID profileId, Profile updatedProfile) {
        Profile existingProfile = findById(profileId);
        Profile existingProfileUpdated = Profile.builder()
                .id(profileId)
                .firstname(updatedProfile.getFirstname() != null ? updatedProfile.getFirstname() : existingProfile.getFirstname())
                .lastname(updatedProfile.getLastname() != null ? updatedProfile.getLastname() : existingProfile.getLastname())
                .phone(updatedProfile.getPhone() != null ? updatedProfile.getPhone() : existingProfile.getPhone())
                .address(updatedProfile.getAddress() != null ? updatedProfile.getAddress() : existingProfile.getAddress())
                .build();

        return profileRepository.save(existingProfileUpdated);
    }

    @Transactional
    public String deleteById(UUID profileId) {
        findById(profileId);
        profileRepository.deleteById(profileId);

        return "Your account has been successfully deleted.";
    }
}