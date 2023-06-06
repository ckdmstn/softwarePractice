package com.example.softwarePractice.controller.PersonalItem;

import com.example.softwarePractice.controller.User.UserSession;
import com.example.softwarePractice.domain.Account;
import com.example.softwarePractice.domain.PersonalItem;
import com.example.softwarePractice.service.AccountService;
import com.example.softwarePractice.service.PersonalItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@SessionAttributes("userSession")
/* 개인거래 게시글 등록, 수정, 삭제를 관리하는 컨트롤러 */
public class PersonalItemController {
    private static final String PERSONAL_REGISTRATION_FORM = "personal/itemRegisterForm";
    private static final String PERSONAL_DETAIL_VIEW = "personal/detail";
    private static final String LOGIN_FORM = "user/loginForm";

    @Autowired
    private PersonalItemService personalItemService;
    public void setPersonalItemService(PersonalItemService personalItemService) {
        this.personalItemService = personalItemService;
    }

    @GetMapping("/personal/register")
    public ModelAndView showRegisterForm(HttpServletRequest request) {
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        ModelAndView mav = new ModelAndView();

        if (userSession != null) { // 로그인 되어 있으면
            mav.setViewName(PERSONAL_REGISTRATION_FORM);
            mav.addObject("statusString", new String[] {"거래가능", "거래중", "거래완료"});
            mav.addObject("personalItem", new PersonalItemRequest());
            return mav;
        } else {
            mav.setViewName(LOGIN_FORM);
            return mav;
        }
    }

    @PostMapping("/personal/register")
    public String register(HttpServletRequest request,
                                 @ModelAttribute("personalItem") PersonalItemRequest itemRegistReq,
                                 BindingResult result) {
        // 입력 값 검증 추후 수정

        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        Account account = userSession.getAccount();

        if (result.hasErrors()) {
            return PERSONAL_REGISTRATION_FORM;
        }

        PersonalItem personalItem = personalItemService.registerNewItem(itemRegistReq, account.getId());

        return "redirect:/personal/detail/" + personalItem.getId();
        // "redirect:/item/" + personalItem.getId();
    }

    @GetMapping("/user/myPage/sell/personal/update")
    public ModelAndView showUpdateForm(HttpServletRequest request,
                                       @RequestParam("itemId") long itemId) {
        ModelAndView mav = new ModelAndView();

        PersonalItem personalItem = personalItemService.searchItem(itemId);

        System.out.println(personalItem.getStatus());

        PersonalItemRequest personalItemRequest = new PersonalItemRequest();

        personalItemRequest.setItemId(personalItem.getId());
        personalItemRequest.setSellerId(personalItem.getSellerId());
        personalItemRequest.setTitle(personalItem.getTitle());
        personalItemRequest.setPrice(personalItem.getPrice());
        personalItemRequest.setDescription(personalItem.getDescription());
        if (personalItem.getStatus().toString().equals("INSTOCK")) {
            personalItemRequest.setStatus("거래가능");
        } else if (personalItem.getStatus().toString().equals("ING")) {
            personalItemRequest.setStatus("거래중");
        } else {
            personalItemRequest.setStatus("거래완료");
        }

        System.out.println(personalItemRequest.getStatus());

        mav.setViewName(PERSONAL_REGISTRATION_FORM);
        mav.addObject("statusString", new String[] {"거래가능", "거래중", "거래완료"});
        mav.addObject("personalItem", personalItemRequest);

        return mav;
    }

    @PostMapping("/user/myPage/sell/personal/update")
    public String update(HttpServletRequest request,
                               @ModelAttribute("personalItem") PersonalItemRequest itemRegistReq,
                               BindingResult result) {
        // 입력 값 검증 추후 수정

        if (result.hasErrors()) {
            return PERSONAL_REGISTRATION_FORM;
        }

        PersonalItem personalItem = personalItemService.updateItem(itemRegistReq);

        return "redirect:/personal/detail/" + personalItem.getId();
        // "redirect:/item/" + personalItem.getId();
    }

    @RequestMapping("/user/myPage/sell/personal/delete")
    public String delete(HttpServletRequest request,
                         @RequestParam("itemId") long itemId) {

        personalItemService.deleteItem(itemId);

        return "personal/list";
    }

    // 개인 거래 게시글 리스트
    @GetMapping("/personal/list")
    public String showList(HttpServletRequest request, Model model) {

        List<PersonalItem> personalItemList = personalItemService.personalItemList();

        model.addAttribute("personalItemList", personalItemList);

        return "personal/list";
    }

    // 개인 거래 게시글 상세 뷰
    @RequestMapping("/personal/detail/{itemId}")
    public String showPersonalDetail(HttpServletRequest request,
                                     @PathVariable("itemId") long itemId, Model model) {
        PersonalItem personalItem = personalItemService.searchItem(itemId);

        model.addAttribute("personalItem", personalItem);

        return PERSONAL_DETAIL_VIEW;
    }



}
