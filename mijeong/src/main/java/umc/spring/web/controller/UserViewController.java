//package umc.spring.web.controller;
//
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import umc.spring.service.UserService.UserCommandService;
//import umc.spring.web.dto.user.UserRequest;
//
//@Slf4j
//@RequiredArgsConstructor
//@Controller
//public class UserViewController {
//
//    private final UserCommandService userCommandService;
//
//    @GetMapping("/login")
//    public String loginPage() {
//        return "login";
//    }
//
//    // 회원가입
//    @GetMapping("/signup")
//    public String signupPage(Model model) {
//        model.addAttribute("userJoinDto", new UserRequest.JoinDto());
//        return "signup";
//    }
//
//    @PostMapping("/users/signup")
//    public String joinMember(@ModelAttribute("userJoinDto") UserRequest.JoinDto request, // 협업시에는 기존 RequestBody 어노테이션을 붙이기
//                             BindingResult bindingResult,
//                             Model model) {
//        if (bindingResult.hasErrors()) {
//            // 뷰에 데이터 바인딩이 실패할 경우 signup 페이지를 유지
//            return "signup";
//        }
//
//        try {
//            log.info("유저 회원가입 요청");
//            userCommandService.joinUser(request);
//            return "redirect:/login";
//        } catch (Exception e) {
//            // 회원가입 과정에서 에러가 발생할 경우 에러 메시지를 보내고, signup 페이디를 유지
//            log.error("유저 회원가입 요청 에러: {}", e.getMessage());
//            model.addAttribute("error", e.getMessage());
//            return "signup";
//        }
//    }
//
//    @GetMapping("/home")
//    public String home() {
//        return "home";
//    }
//
//    @GetMapping("/admin")
//    public String admin() {
//        return "admin";
//    }
//}