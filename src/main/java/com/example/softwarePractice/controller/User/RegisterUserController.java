package com.example.softwarePractice.controller.User;

import com.example.softwarePractice.domain.Account;
import com.example.softwarePractice.service.AccountService;
import com.example.softwarePractice.service.AccountFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/user/register")
@SessionAttributes({"memReq", "userSession"})
/* 회원가입 */
public class RegisterUserController {

    private static final String USER_REGISTRATION_FORM = "user/registerForm";
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

    @ModelAttribute("memReq")
    public UserRegistRequest formBacking(HttpServletRequest request) {
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");

        String uri = request.getRequestURI();
        if (isLoggedIn(userSession) && !(uri.contains("/checkId"))) {
            Account account = userSession.getAccount();

            UserRegistRequest memReq = new UserRegistRequest(
                    account.getUserName(), account.getNickName(), account.getId(),
                    account.getEmail(), account.getPhone()
            );
            System.out.println("formBacking, if");
            return memReq;
        } else {
            System.out.println("formBacking, else");
            return new UserRegistRequest();
        }
    }

    private static boolean isLoggedIn(UserSession userSession) {
        return (userSession != null);
    }

    @GetMapping
    public String showForm() {
        System.out.println("showForm");
        return USER_REGISTRATION_FORM;
    }

    @PostMapping
    public String submit(HttpSession session,
                         @Valid @ModelAttribute("memReq") UserRegistRequest memReq,
                         BindingResult bindingResult) {
        validator.validate(memReq, bindingResult);


        if (bindingResult.hasErrors()) {
            return USER_REGISTRATION_FORM;
        }


        Account account = accountService.insertAccount(memReq); // 계정 생성
        UserSession userSession = new UserSession(account); // account를 세션에 저장
        session.setAttribute("userSession", userSession);
        // -> 따로 로그인하지 않아도 회원가입 완료하면 자동 로그인

        System.out.println("1 " + account.toString());
        System.out.println("2 " + userSession.getAccount().toString());

        return "index"; // 메인화면으로 redirection
    }

    @PostMapping("/checkId")
    @ResponseBody
    public boolean checkId(@RequestParam("id") String id) {
        return accountService.isIdExist(id);
    }
}