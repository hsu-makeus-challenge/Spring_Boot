package umc.spring.web.controller;//package umc.spring.web.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import umc.spring.service.UserCommandService;
//import umc.spring.web.dto.UserRequestDTO;
//
//@Controller
//@RequiredArgsConstructor
//public class UserViewController {
//
//    private final UserCommandService userCommandService;
//
//    @PostMapping("/signup")
//    public String joinMember(@ModelAttribute("userJoinDto") UserRequestDTO.JoinDto request, // 협업시에는 기존 RequestBody 어노테이션을 붙여주시면 됩니다!
//                             BindingResult bindingResult,
//                             Model model) {
//        if (bindingResult.hasErrors()) {
//            // 뷰에 데이터 바인딩이 실패할 경우 signup 페이지를 유지합니다.
//            return "signup";
//        }
//
//        try {
//            userCommandService.joinUser(request);
//            return "redirect:/login";
//        } catch (Exception e) {
//            // 회원가입 과정에서 에러가 발생할 경우 에러 메시지를 보내고, signup 페이디를 유지합니다.
//            model.addAttribute("error", e.getMessage());
//            return "signup";
//        }
//    }
//
//    @GetMapping("/login")
//    public String loginPage() {
//        return "login"; // → src/main/resources/templates/login.html (Thymeleaf 템플릿)
//    }
//
//    @GetMapping("/signup")
//    public String signupForm(Model model) {
//        model.addAttribute("userJoinDto", new UserRequestDTO.JoinDto());
//        return "signup";
//    }
//
//    @GetMapping("/home")
//    public String homePage() {
//        return "home";
//    }
//
//    @GetMapping("/admin")
//    public String adminPage() {
//        return "admin";
//    }
//}