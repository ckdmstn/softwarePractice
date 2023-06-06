package com.example.softwarePractice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue(value="PERSONAL")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonalItem extends Item {
    @Column(name="seller_id")
    private String sellerId;

    @Transient
    private String nickName; // 화면에 닉네임 출력하기 위한 필드

    @Column(name = "img_name")
    private String imgName;
    @Column(name = "img_path")
    private String imgPath;
}
