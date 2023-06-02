package com.example.softwarePractice.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
////@RequiredArgsConstructor
//@Builder
//@Entity
//@Table(name="GROUPITEM")
public class GroupItem extends Item implements Serializable {
//    @Id
//    @GeneratedValue
    private String itemId;
    private String sellerId;
    private int salesTarget;
    private int salesNow;
    private Date startDate;
    private Date endDate;
    private int status;
}
