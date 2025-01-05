package com.jeyofdev.shopping_krist.format;

import com.jeyofdev.shopping_krist.domain.profile.Profile;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NameFormat {
    private String firstname;
    private String lastname;
    private String fullname;

    public void setFullname() {
        this.fullname = this.fullname = String.format("%s %s", this.firstname, this.lastname);
    }

    public String getFullname() {
        if (this.fullname == null) {
            setFullname();
        }
        return this.fullname;
    }

    @Builder
    public NameFormat(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        setFullname();
    }

    public static NameFormat get(Profile profile) {
        return NameFormat.builder()
                .firstname(profile.getFirstname())
                .lastname(profile.getLastname())
                .build();
    }
}
