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
}
