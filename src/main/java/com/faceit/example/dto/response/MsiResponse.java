package com.faceit.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsiResponse {

    private long id;
    private String state;
    private String name;
    private String institutionType;
    private String phoneNumber;
    private String website;

    public MsiResponse(String state,
                       String name,
                       String institutionType,
                       String phoneNumber,
                       String website) {
        this.state = state;
        this.name = name;
        this.institutionType = institutionType;
        this.phoneNumber = phoneNumber;
        this.website = website;
    }
}
