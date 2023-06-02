package com.example.softwarePractice.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
////@RequiredArgsConstructor
//@Builder
//@Entity
//@Table(name="PERSONALITEM")
public class PersonalItem extends Item {
    private String sellerId;
    private int status;
}
