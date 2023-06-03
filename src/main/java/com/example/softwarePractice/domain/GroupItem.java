package com.example.softwarePractice.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "ITEM")
@DiscriminatorValue(value="GROUP")
public class GroupItem extends Item implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="item_id")
    private long itemId;

    //@ManyToOne
    //@JoinColumn(name="seller", referencedColumnName="id")
    //private Account user; //1 : N(0..*) , many side
    @Column(name="seller_id")
    private int sellerId; // userId

    private int category_id;

    @Column(name="sales_target")
    private int salesTarget; //공구 목표액
    @Column(name="sales_now")
    private int salesNow; //현재 판매액
    @Column(name="start_date")
    private Date startDate; //공구 시작일
    @Column(name="end_date")
    private Date endDate; //공구 마감일
    private String status; //INSTOCK, SOLDOUT

    private String dtype; //GROUP - this is discriminator column
}
