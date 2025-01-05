package com.jeyofdev.shopping_krist.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class AllDataResponse {
    @JsonProperty("users")
    private List<UserDataResponse> userDataResponseList;

    @JsonProperty("cities")
    private List<CityDataResponse> cityDataResponseList;

    @JsonProperty("profiles")
    private List<ProfileDataResponse> profileDataResponseList;

    @JsonProperty("profileSettings")
    private ProfileSettingsDataResponse profileSettingsDataResponse;

    @JsonProperty("products")
    private List<ProductDataResponse> productDataResponseList;

    @JsonProperty("notifications")
    private List<NotificationDataResponse> notificationDataResponseList;

    @JsonProperty("cartItems")
    private List<CartItemDataResponse> cartItemDataResponseList;

    @JsonProperty("addresses")
    private List<AddressDataResponse> addressDataResponseList;

    @JsonProperty("categories")
    private List<CategoryDataResponse> categoriesDataResponseList;

    @JsonProperty("comments")
    private List<CommentDataResponse> commentDataResponseList;
}
