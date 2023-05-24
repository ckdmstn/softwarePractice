package com.example.softwarePractice.controller.User;

import com.example.softwarePractice.domain.Account;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@SuppressWarnings("serial")
public class UserSession {
    private Account account;

    public UserSession(Account account) {
        this.account = account;
    }
}
