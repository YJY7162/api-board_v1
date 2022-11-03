//package com.api.board.controller;
//
//import com.api.board.domain.User;
//import com.api.board.service.MemberService;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//
//@Slf4j
//@RequiredArgsConstructor
//@Controller
//@RequestMapping ("/users")
//public class LoginController {
//
//    @Autowired
//    private MemberService memberService;
//
//    // 회원 가입
//    @PostMapping
//    public String creatMember (@Valid @ModelAttribute User user) {
//        log.info("user_id : {}", user.getUser_id());
//
//        return
//
//    }
//
//
//    // 로그인
//    @PostMapping("/user")
//    public ResponseEntity<User> login (@Valid @RequestBody User user) {
//        log.info("member_id : {}", user.getUser_id());
//        log.info("member_password : {}", user.getUser_password());
//
//
//
//        return new ResponseEntity<>(memberService.loginCheck)
//    }
//
//
//
//    @PostMapping("/login")
//    public String loginV4(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
//                          @RequestParam(defaultValue = "/") String redirectURL,
//                          HttpServletRequest request) {
//
//        if (bindingResult.hasErrors()) {
//            return "login/loginForm";
//        }
//
//        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
//
//        if (loginMember == null) {
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
//            return "login/loginForm";
//        }
//
//        //로그인 성공 처리
//        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
//        HttpSession session = request.getSession();
//        //세션에 로그인 회원 정보 보관
//        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
//
//        return "redirect:" + redirectURL;
//
//    }
//
//
//
//    // 아이디 중복 체크
//    @GetMapping("/{user_id}")
//    public ResponseEntity<User> getMemberDetail (@PathVariable String member_id) {
//        log.info("member_id : {}", member.getMember_id());
//
//        return new ResponseEntity<>(memberService.get(member_id), HttpStatus.OK);
//    }
//
//
//}
