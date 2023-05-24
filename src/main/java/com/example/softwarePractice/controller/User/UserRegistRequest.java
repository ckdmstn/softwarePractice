package com.example.softwarePractice.controller.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRegistRequest {
    private String userName;
    private String nickName;
    private String id;
    private String password;
    private String passwordCheck;
    private String email;
    private String address;
    private String bankName;
    private String bankAccount;
    private String phoneNumber;

    public UserRegistRequest(String userName, String nickName, String id,
                             String email, String address, String bankName,
                             String bankAccount, String phoneNumber) {
        this.userName = userName;
        this.nickName = nickName;
        this.id = id;
        this.email = email;
        this.address = address;
        this.bankName = bankName;
        this.bankAccount = bankAccount;
        this.phoneNumber = phoneNumber;
    }

    public boolean isSamePasswordConfirmPassword() {
        if (password == null || passwordCheck == null)
            return false;
        return password.equals(passwordCheck);
    }

    public boolean hasPassword() {
        return password != null && password.trim().length() > 0;
    }
}
