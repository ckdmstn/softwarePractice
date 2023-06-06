package com.example.softwarePractice.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@SequenceGenerator(name="SEQ_ITEM", sequenceName="ITEM_ID_SEQ", allocationSize=1)
@Data
public abstract class Item {
    @Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ITEM")
    @Column(name = "item_id")
    private long id;

    private String title;
    private int price;
    private String description;
    private int wishCount;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;
}
