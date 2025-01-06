package com.jeyofdev.shopping_krist.domain.profile;

import com.jeyofdev.shopping_krist.auth_user.AuthUser;
import com.jeyofdev.shopping_krist.auth_user.AuthUserRepository;
import com.jeyofdev.shopping_krist.core.abstracts.AbstractDomainServiceBase;
import com.jeyofdev.shopping_krist.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class ProfileService extends AbstractDomainServiceBase<Profile, ProfileRepository> {
    private final ProfileRepository profileRepository;
    private final AuthUserRepository authUserRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, AuthUserRepository authUserRepository) {
        super(profileRepository, "profile");
        this.profileRepository = profileRepository;
        this.authUserRepository = authUserRepository;
    }

    @Transactional
    public Profile save(Profile profile, UUID userId) {
        AuthUser user = authUserRepository.findById(userId)
                .orElseThrow(
                        () -> new NotFoundException(MessageFormat.format("User with id {0} not found", userId))
                );

        profile.setUser(user);
        profile.setDeliveryAddressList(new ArrayList<>());
        profile.setNotificationList(new ArrayList<>());
        profile.setOrderList(new ArrayList<>());
        profile.setProductList(new ArrayList<>());
        profile.setProfileSettings(null);

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
                .user(existingProfile.getUser())
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
