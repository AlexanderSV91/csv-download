package com.faceit.example.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MsiResponse {

    long id;
    String state;
    String name;
    String institutionType;
    String phoneNumber;
    String website;
}
