package com.example.robofinance.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Data
@Getter
@Setter
@EqualsAndHashCode
@Entity
@AllArgsConstructor
@Builder

@Table(name = "address")

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String contry;
    @Column
    private String region;
    @Column
    private String city;
    @Column
    private String street;
    @Column
    private String house;
    @Column
    private String flat;

    @Column
    private final Instant created = Instant.now();


    @Builder.Default
    @Column
    private Instant modified = Instant.now();

    public Address() {
    }

    @PreUpdate
    public void setLastUpdate() {
        modified = Instant.now();
    }
}
