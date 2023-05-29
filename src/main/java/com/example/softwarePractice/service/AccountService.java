package com.example.softwarePractice.service;

import com.example.softwarePractice.controller.User.UserRegistRequest;
import com.example.softwarePractice.dao.AccountDao;
import com.example.softwarePractice.domain.Account;
import com.example.softwarePractice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    // 실제 메소드 구현은 추후에..

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountDao accountDao;

    // 사용자 PK로 계정 검색
    public Account getAccount(int userId) {
        Optional<Account> account = accountRepository.findById(userId);
        if (account.isPresent()) return account.get();
        return null;
    }

    // id, password로 계정 검색
    public Account getAccount(String id, String password) {
        Optional<Account> account = accountRepository.findByIdAndPassword(id, password);
        if (account.isPresent()) return account.get();
        return null;
    }

    // 새로운 계정 추가 후 다시 Account 반환 → 바로 로그인
    public Account insertAccount(UserRegistRequest memReq) {
        Account account = new Account();
        account.setUserName(memReq.getUserName());
        account.setNickName(memReq.getNickName());
        account.setId(memReq.getId());
        account.setPassword(memReq.getPassword());
        account.setEmail(memReq.getEmail());
        account.setAddress(memReq.getAddress());
        account.setZipcode(memReq.getZipcode());
        account.setBankName(memReq.getBankName());
        account.setBankAccount(memReq.getBankAccount());
        account.setPhone(memReq.getPhoneNumber());

        Account newAccount = accountDao.insertAccount(account);

        return newAccount;
    }

    // 회원 정보 수정 후 다시 Account 반환
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    // 회원 정보 비밀번호 변경
    public void updatePassword(int userId, String password) {
        accountDao.updatePassword(userId, password);
    }

    // 회원 삭제
    public void deleteAccount(Account account) {
        accountDao.deleteAccount(account);
    }
}
