package com.example.softwarePractice.service;

import com.example.softwarePractice.controller.User.UserRegistRequest;
import com.example.softwarePractice.domain.Account;

import java.util.List;

public class AccountService {
    // 실제 메소드 구현은 추후에..

    // 사용자 PK로 계정 검색
    public Account getAccount(int userId) {
        return null;
    }

    // id, password로 계정 검색
    public Account getAccount(String id, String password) {
        return null;
    }

    // 새로운 계정 추가 후 다시 Account 반환 → 바로 로그인
    public Account insertAccount(UserRegistRequest memReq) {
        return null;
    }

    // 회원 정보 수정 후 다시 Account 반환
    public Account updateAccount(Account account) {
        return null;
    }

    // 회원 정보 비밀번호 변경
    public void updatePassword(int userId, String password) {

    }

    // 회원 삭제
    public void deleteAccount(Account account) {

    }
}
