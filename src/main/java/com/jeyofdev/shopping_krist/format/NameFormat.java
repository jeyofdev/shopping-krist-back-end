package com.jeyofdev.shopping_krist.format;

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
}
