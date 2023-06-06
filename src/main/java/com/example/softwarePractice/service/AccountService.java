package com.example.softwarePractice.service;

import com.example.softwarePractice.controller.User.UserRegistRequest;
import com.example.softwarePractice.dao.AccountDao;
import com.example.softwarePractice.domain.Account;
import com.example.softwarePractice.domain.GroupItem;
import com.example.softwarePractice.domain.PersonalItem;
import com.example.softwarePractice.domain.SomsomItem;
import com.example.softwarePractice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    // 실제 메소드 구현은 추후에..

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountDao accountDao;

    // 사용자 ID로 계정 검색
    public Account getAccount(String id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) return account.get();
        return null;
    }

    // id, password로 계정 검색
    public Account getAccount(String id, String password) {
        Optional<Account> account = accountRepository.findByIdAndPassword(id, password);
        if (account.isPresent()) return account.get();
        return null;
    }

    public boolean isIdExist(String id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) return true;
        return false;
    }

    // 새로운 계정 추가 후 다시 Account 반환 → 바로 로그인
    public Account insertAccount(UserRegistRequest memReq) {
        Account account = new Account();
        account.setName(memReq.getUserName());
        account.setNickName(memReq.getNickName());
        account.setId(memReq.getId());
        account.setPassword(memReq.getPassword());
        account.setEmail(memReq.getEmail());
        account.setAddress(memReq.getAddress());
        account.setZipcode(memReq.getZipcode());
        account.setBankName(memReq.getBankName());
        account.setBankAccount(memReq.getBankAccount());
        account.setPhone(memReq.getPhoneNumber());

        accountDao.insertAccount(account);

        return account;
    }

    // 회원 정보 수정 후 다시 Account 반환
    public Account updateAccount(Account account) {
        return accountDao.updateAccount(account);
    }

    // 회원 정보 비밀번호 변경
    public void updatePassword(String id, String password) {
        accountDao.updatePassword(id, password);
    }

    // 회원 삭제
    public void deleteAccount(Account account) {
        accountDao.deleteAccount(account);
    }

    // 사용자 PK로 구매 내역, 판매 내역, 공동구매 내역, 위시리스트 개수 반환
    public int[] getMyPageList(String id) {
        return new int[] {1, 0, 0, 2}; // 추후 수정!!
    }

    // 사용자 PK로 판매 내역 리스트 검색
    public List<PersonalItem> getSellItemList(String id) {
        return null;
    }

    // 사용자 PK로 공동구매 판매 내역 리스트 검색
    public List<GroupItem> getSellGroupList(String id) {
        // 추후 수정 (지금은 확인용)

        List<GroupItem> itemList = new ArrayList<GroupItem>();

        GroupItem item1 = new GroupItem();
        item1.setTitle("@@ 공동구매");
        item1.setDescription("공동구매 합니다");
        item1.setPrice(5000);
        item1.setSalesNow(0);
        item1.setSalesTarget(300000);
        item1.setStartDate(new Date());
        item1.setSellerId(id);

        GroupItem item2 = new GroupItem();
        item2.setTitle("** 공동구매");
        item2.setDescription("공동구매 합니다");
        item2.setPrice(15000);
        item2.setSalesNow(0);
        item2.setSalesTarget(300000);
        item2.setStartDate(new Date());
        item2.setSellerId(id);

        itemList.add(item1);
        itemList.add(item2);

        return itemList;
    }

    // 사용자 PK로 구매 내역 리스트 검색
//    public List<Order> getOrderList(String id) {
//        return null;
//    }

    // 구매 취소
    public void cancelOrder(String id, int orderId) {

    }

    // 사용자 PK로 개인 판매 위시리스트 검색
    public List<PersonalItem> getPersonalWishlist(String id) {
        return getSellItemList(id); // 당연히 추후 수정
    }

    // 사용자 PK로 공동구매 위시리스트 검색
    public List<GroupItem> getGroupWishlist(String id) {
        return getSellGroupList(id); // 당연히 추후 수정
    }

    // 해당 사용자에 해당 아이템 위시리스트 추가
    public void addWishlist(String id, int itemId) {

    }

    // 해당 사용자에 해당 아이템 위시리스트 삭제
    public void cancelWishlist(String id, int itemId) {

    }
}
