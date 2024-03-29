package com.faceit.example.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity()
@Table(name = "msi")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Msi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String state;
    String name;
    String institutionType;
    String phoneNumber;
    String website;
}
