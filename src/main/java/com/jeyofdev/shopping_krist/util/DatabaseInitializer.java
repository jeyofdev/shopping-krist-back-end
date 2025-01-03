package com.jeyofdev.shopping_krist.util;

import com.jeyofdev.shopping_krist.auth.AuthService;
import com.jeyofdev.shopping_krist.auth.model.AuthResponse;
import com.jeyofdev.shopping_krist.auth.model.LoginRequest;
import com.jeyofdev.shopping_krist.auth.model.RegisterRequest;
import com.jeyofdev.shopping_krist.core.enums.Color;
import com.jeyofdev.shopping_krist.core.enums.DarkMode;
import com.jeyofdev.shopping_krist.core.enums.Size;
import com.jeyofdev.shopping_krist.data.*;
import com.jeyofdev.shopping_krist.domain.cart.Cart;
import com.jeyofdev.shopping_krist.domain.cart.CartServiceBase;
import com.jeyofdev.shopping_krist.domain.cartItem.CartItemDomainMapper;
import com.jeyofdev.shopping_krist.domain.cartItem.CartItemServiceBase;
import com.jeyofdev.shopping_krist.domain.city.City;
import com.jeyofdev.shopping_krist.domain.city.CityService;
import com.jeyofdev.shopping_krist.domain.notification.Notification;
import com.jeyofdev.shopping_krist.domain.notification.NotificationService;
import com.jeyofdev.shopping_krist.domain.product.Product;
import com.jeyofdev.shopping_krist.domain.product.ProductDomainMapper;
import com.jeyofdev.shopping_krist.domain.product.ProductServiceBase;
import com.jeyofdev.shopping_krist.domain.profile.Profile;
import com.jeyofdev.shopping_krist.domain.profile.ProfileService;
import com.jeyofdev.shopping_krist.domain.profileSettings.ProfileSettings;
import com.jeyofdev.shopping_krist.domain.profileSettings.ProfileSettingsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.*;
import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DatabaseInitializer.class);

    private final AllDataService allDataService;
    private final AuthService authService;
    private final CityService cityService;
    private final ProfileService profilService;
    private final ProfileSettingsService profilSettingsService;
    private final ProductServiceBase productService;
    private final CartServiceBase cartService;
    private final NotificationService notificationService;

    private final ProductDomainMapper productMapper;
    private final CartItemServiceBase cartItemService;
    private final CartItemDomainMapper cartItemMapper;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Database initialization started...");
        this.createDatas();
    }

    public static void initializeDatabase(String jdbcUrl, String user, String password, String dbName) {
        String createDbQuery = MessageFormat.format("CREATE DATABASE {0}", dbName);
        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
             Statement statement = connection.createStatement()) {

            // If the database does not exist, create it
            statement.executeUpdate(createDbQuery);
            System.out.println("Database created successfully!");
        } catch (SQLException e) {
            if (e.getSQLState().equals("42P04")) { // Code error for "Database already exists"
                System.out.println("The database already exists.");
            } else {
                System.err.println(MessageFormat.format("Error creating database : {0}", e.getMessage()));
                throw new RuntimeException(e);
            }
        }
    }

    private void createDatas() throws IOException {
        AllDataResponse allDataList = allDataService.getAllDatas();

        createUsers(allDataList.getUserDataResponseList());
        createCities(allDataList.getCityDataResponseList());
        createProfile(allDataList.getUserDataResponseList(), allDataList.getProfileDataResponseList());
        createProfilSettings(allDataList.getProfileSettingsDataResponse());
        createNotifications(allDataList.getNotificationDataResponseList());
        createProducts(allDataList.getProductDataResponseList());
        createCarts();

      /* this.createCartItems();*/
    }

    private void createUsers(List<UserDataResponse> userDataResponseList) throws IOException {
        for (UserDataResponse user : userDataResponseList) {
            authService.register(RegisterRequest.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .role(user.getRole().equalsIgnoreCase("ADMIN") ? "ADMIN" : "USER")
                    .build());
        }
    }

    private void createCities(List<CityDataResponse> cityDataResponseList) {
        for (CityDataResponse city : cityDataResponseList) {
            cityService.save(City.builder()
                    .name(city.getName())
                    .zipCode(city.getZipCode())
                    .build()
            );
        }
    }

    private void createProfile(List<UserDataResponse> userDataResponseList, List<ProfileDataResponse> profileDataResponseList) {
        for (UserDataResponse user : userDataResponseList) {
            AuthResponse authResponse = authService.login(LoginRequest.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .build());

            int userDataIndex = userDataResponseList.indexOf(user);
            ProfileDataResponse profileData = profileDataResponseList.get(userDataIndex);
            profilService.save(Profile.builder()
                    .firstname(profileData.getFirstname())
                    .lastname(profileData.getLastname())
                    .phone(profileData.getPhone())
                    .address(profileData.getAddress())
                    .build(), UUID.fromString(authResponse.getUserId())
            );
        }
    }

    private void createProfilSettings(ProfileSettingsDataResponse profileSettingsDataResponse) {
        List<Profile> profileList = profilService.findAll();

        for (Profile profile : profileList) {
            UUID profileId = profile.getId();

            profilSettingsService.save(ProfileSettings.builder()
                    .appearance(DarkMode.valueOf(profileSettingsDataResponse.getAppearance().toUpperCase()))
                    .isPushNotification(profileSettingsDataResponse.isPushNotification())
                    .isEmailNotification(profileSettingsDataResponse.isEmailNotification())
                    .build(), profileId
            );
        }
    }

    private void createNotifications(List<NotificationDataResponse> notificationDataResponseList) {
        List<Profile> profileList = profilService.findAll();
        UUID firstProfileId = profileList.getFirst().getId();

        for (NotificationDataResponse notification : notificationDataResponseList) {
            notificationService.save(Notification.builder()
                    .title(notification.getTitle())
                    .description(notification.getDescription())
                    .isRead(notification.isRead())
                    .build(), firstProfileId);
        }
    }

    private void createProducts(List<ProductDataResponse> productDataResponseList) {
        for (ProductDataResponse product : productDataResponseList) {
            productService.save(Product.builder()
                    .brand(product.getBrand())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .oldPrice(product.getOldPrice())
                    .stock(product.getStock())
                    .color(Color.valueOf(product.getColor()))
                    .size(Size.valueOf(product.getSize()))
                    .build());
        }
    }

    private void createCarts() {
        List<Profile> profileList = profilService.findAll();

        for (Profile profile : profileList) {
            UUID profileId = profile.getId();
            cartService.save(Cart.builder().build(), profileId);
        }

       /* Cart cartA = cartMapper.mapToEntity(new SaveCartDTO());
        Cart cartB = cartMapper.mapToEntity(new SaveCartDTO());

        cartService.save(cartA);
        cartService.save(cartB);*/
    }

    /*private void createCartItems() {
        CartItem cartItemA = cartItemMapper.mapToEntity(new SaveCartItemDTO(5));
        CartItem cartItemB = cartItemMapper.mapToEntity(new SaveCartItemDTO(2));

        UUID firstProductId = productService.findAll().getFirst().getId();
        UUID secondProductId = productService.findAll().get(1).getId();
        UUID firstCartId = cartService.findAll().getFirst().getId();

        cartItemService.save(cartItemA, firstProductId, firstCartId);
        cartItemService.save(cartItemB, secondProductId, firstCartId);
    }*/
}
