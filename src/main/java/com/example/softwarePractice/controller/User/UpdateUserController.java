package com.example.softwarePractice.controller.User;

import com.example.softwarePractice.domain.Account;
import com.example.softwarePractice.service.AccountFormValidator;
import com.example.softwarePractice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/user/myPage/update")
@SessionAttributes("userSession")
public class UpdateUserController {
    private static final String USER_MYPAGE_FORM = "user/myPageUpdateForm";

    @Autowired
    private AccountService accountService;
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    private AccountFormValidator validator;
    public void setValidator(AccountFormValidator validator) {
        this.validator = validator;
    }

    @ModelAttribute("accountReq")
    public UserRegistRequest formBacking(HttpServletRequest request) {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
            Account account = userSession.getAccount();

            UserRegistRequest accountReq = new UserRegistRequest(
                    account.getName(), account.getNickName(), account.getId(),
                    account.getEmail(), account.getAddress(), account.getZipcode(),
                    account.getBankName(), account.getBankAccount(), account.getPhone()
            );

            return accountReq;
        }
        return new UserRegistRequest();
    }

    /*@ModelAttribute("myPageList")
    public int[] getMyPageList(HttpServletRequest request) { // 구매내역, 판매내역, 공동구매내역, 위시리스트 개수 반환
        if (request.getMethod().equalsIgnoreCase("GET")) {
            UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
            Account account = userSession.getAccount();

            int[] myPageList = accountService.getMyPageList(account.getId());

            return myPageList;
        }
        return null;
    }

    @GetMapping
    public String show() {
        return USER_MYPAGE;
    }*/

    @GetMapping
    public String showForm() {
        return USER_MYPAGE_FORM;
    }

    // 닉네임, 전화번호, 이메일, 주소, 은행명, 계좌번호만 변경 가능
    @PostMapping
    public String update(HttpServletRequest request,
                         @Valid @ModelAttribute("accountReq") UserRegistRequest accountReq,
                         BindingResult bindingResult) {
        validator.validate(accountReq, bindingResult);

        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");

        Account account = userSession.getAccount();
        account.setNickName(accountReq.getNickName());
        account.setPhone(accountReq.getPhoneNumber());
        account.setEmail(accountReq.getEmail());
        account.setAddress(accountReq.getAddress());
        account.setZipcode(accountReq.getZipcode());
        account.setBankName(accountReq.getBankName());
        account.setBankAccount(accountReq.getBankAccount());

        Account newAccount = accountService.updateAccount(account);
        userSession.setAccount(newAccount);

        return "redirect:/" + "user/myPage";
    }

    @PostMapping("/password")
    public String updatePassword( HttpServletRequest request,
                                  @RequestParam("currentPassword") String currentPassword,
                                  @RequestParam("newPassword") String newPassword,
                                  @RequestParam("newPasswordCheck") String newPasswordCheck) {

        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        Account account = userSession.getAccount();

        if (account.getPassword().equals(currentPassword)) { // 현재 비밀번호 틀릴 경우
            return "redirect:/" + "user/myPage"; // 추후 수정
        }

        if (newPassword.equals(newPasswordCheck)) { // 새 비밀번호와 비밀번호 확인이 다를 경우
            return "redirect:/" + "user/myPage"; // 추후 수정
        }

        accountService.updatePassword(account.getId(), newPassword);

        return "redirect:/" + "user/myPage";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request, HttpSession session) throws Exception {
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        Account account = userSession.getAccount();
        accountService.deleteAccount(account);

        return "redirect:/" + "user/logout";
    }

    private static boolean isLoggedIn(UserSession userSession) {
        return (userSession != null);
    }
}
