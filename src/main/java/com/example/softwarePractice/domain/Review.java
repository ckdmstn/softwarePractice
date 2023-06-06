package com.example.softwarePractice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Review implements Serializable {
    @Id @Column(name = "review_id")
    @GeneratedValue
    private Long reviewId;
    @Column(name = "account_id")
    private String userId;
    @Column(name = "order_item_id")
    private Long orderItemId;
    private String description;
    private double score;
}
