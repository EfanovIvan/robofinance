package com.example.robofinance.domain;

import com.example.robofinance.utils.GenderConverter;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String lastName;

    @Column
    private String firstName;

    @Column
    private String middleName;

    @ManyToOne
    @JoinColumn(name = "actual_address_id")
    private Address actualAddress;


    @ManyToOne
    @JoinColumn(name = "registred_address_id")
    private Address registeredAddress;

    @Convert(converter = GenderConverter.class)
    @Column(name="sex")
    private Gender gender;

}