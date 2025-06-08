//package umc.study.web.controller;
//
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import umc.study.apiPayload.ApiResponse;
//import umc.study.converter.UserConverter;
//import umc.study.domain.Users;
//import umc.study.service.UserService.UserCommandService;
//import umc.study.web.dto.UserRequestDTO;
//import umc.study.web.dto.UserResponseDTO;
//
//@Slf4j
//@Controller
//@RequiredArgsConstructor
//public class MemberViewController {
//
//    private final UserCommandService userCommandService;
//
////    @PostMapping("/members/signup")
////    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@ModelAttribute @Valid UserRequestDTO.JoinDto request){
////        Users user = userCommandService.joinMember(request);
////        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
////    }
//    @PostMapping("/members/signup")
//    public String joinMember(@ModelAttribute("memberJoinDto") UserRequestDTO.JoinDto request, // 협업시에는 기존 RequestBody 어노테이션을 붙여주시면 됩니다!
//                             BindingResult bindingResult,
//                             Model model) {
//        log.info("gender={}", request.getGender());
//        log.info("role={}", request.getRole());
//        log.info("name={}", request.getName());
//        log.info("age={}", request.getAge());
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
//        return "login";
//    }
//
//    @GetMapping("/signup")
//    public String signupPage(Model model) {
//        model.addAttribute("memberJoinDto", new UserRequestDTO.JoinDto());
//        return "signup";
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