package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import umc.spring.service.UserService.UserCommandService;
import umc.spring.web.dto.user.UserRequestDTO;

@Controller
@RequiredArgsConstructor
public class UserViewController {

    private static final Logger log = LoggerFactory.getLogger(UserViewController.class);
    private final UserCommandService userCommandService;

    @PostMapping("/users/signup")
    public String joinUser(@ModelAttribute("userJoinDto")UserRequestDTO.JoinDto request,
                           BindingResult bindingResult,
                           Model model) {
        if(bindingResult.hasErrors()) {
            // 뷰에 데이터 바인딩 실패할 경우 signup 페이지 유지
            return "signup";
        }

        try{
            userCommandService.joinUser(request);
            return "redirect:/login";
        } catch (Exception e) {
            // 회원가입 과정에서 에러가 발생할 경우 에러메세지를 보내고, signup 페이지 유지
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("userJoinDto", new UserRequestDTO.JoinDto());
        return "signup";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
