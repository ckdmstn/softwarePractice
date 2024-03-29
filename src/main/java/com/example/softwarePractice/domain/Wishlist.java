package com.example.softwarePractice.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="WISHLIST")
public class Wishlist {
    @Id
    @GeneratedValue
    @Column(name = "wishlist_id")
    private long wishlistId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "item_id")
    private long itemId;
}