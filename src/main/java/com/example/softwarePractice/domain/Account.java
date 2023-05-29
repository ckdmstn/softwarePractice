package com.example.softwarePractice.domain;

import com.sun.istack.NotNull;
import lombok.*;
//import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name="ACCOUNT")
public class Account {
    @Id
    @SequenceGenerator(name = "USER_SEQ_GENERATOR",
            sequenceName = "user_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "USER_SEQ_GENERATOR")
    private int userId;
    @NotNull
    private String userName;
    private String nickName;
    @NotNull
    private String id;
    @NotNull
    private String password;
    private String email;
    private String address;
    private int zipcode;
    private String bankName;
    private String bankAccount;
    private String phone;
    //    수정 필요
    // private List<Integer> wishItem;

}
