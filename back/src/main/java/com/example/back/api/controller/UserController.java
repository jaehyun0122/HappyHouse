//package com.example.back.api.controller;
//
//import com.example.back.api.service.user.UserService;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/v1/user")
//@RequiredArgsConstructor
//@Slf4j
//public class UserController {
//
//    private final UserService userService;
//    @Autowired
//    private JwtServiceImpl jwtService;
//
//    @Autowired
//    private MemberService memberService;
//
//    @ApiOperation(value = "회원 인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
//    @GetMapping("/{nickname}")
//    public ResponseEntity<Map<String, Object>> getInfo(
//            @PathVariable("nickname") @ApiParam(value = "인증할 회원의 아이디.", required = true) String nickname,
//            HttpServletRequest request) {
//        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status = HttpStatus.ACCEPTED;
//        if (jwtService.isUsable(request.getHeader("access-token"))) {
//            logger.info("사용 가능한 토큰!!!");
//            try {
////				로그인 사용자 정보.
//                Member member = memberService.info(nickname);
//                resultMap.put("info", member);
//                resultMap.put("message", SUCCESS);
//                status = HttpStatus.ACCEPTED;
//            } catch (Exception e) {
//                logger.error("정보조회 실패 : {}", e);
//                resultMap.put("message", e.getMessage());
//                status = HttpStatus.INTERNAL_SERVER_ERROR;
//            }
//        } else {
//            logger.error("사용 불가능 토큰!!!");
//            resultMap.put("message", FAIL);
//            status = HttpStatus.ACCEPTED;
//        }
//        return new ResponseEntity<Map<String, Object>>(resultMap, status);
//    }
//
//    @ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, Object>> login(
//            @RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) Member member) {
//        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status = null;
//        try {
//            Member loginUser = memberService.login(member);
//            if (loginUser != null) {
//                String token = jwtService.create("nickname", loginUser.getNickname(), "access-token");// key, data, subject
//                logger.debug("로그인 토큰정보 : {}", token);
//                resultMap.put("access-token", token);
//                resultMap.put("message", SUCCESS);
//                status = HttpStatus.ACCEPTED;
//            } else {
//                resultMap.put("message", FAIL);
//                status = HttpStatus.ACCEPTED;
//            }
//        } catch (Exception e) {
//            logger.error("로그인 실패 : {}", e);
//            resultMap.put("message", e.getMessage());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<Map<String, Object>>(resultMap, status);
//    }
//
//
//    @ApiOperation(value = "회원가입", notes = "새로운 회원 가입 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = Map.class)
//    @PostMapping("")
//    public ResponseEntity<String> regMember(
//            @RequestBody @ApiParam(value = "회원가입 시 필요한 정보(아이디, 비밀번호, 이름, 이메일).", required = true) Member member) throws Exception {
//        logger.info("회원가입 호출");
//        logger.info(member.getNickname());
//        logger.info(member.getName());
//        logger.info(member.getPassword());
//        logger.info(member.getEmail());
//        if (memberService.regMember(member)) {
//            logger.info("회원가입 성공");
//            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//        }
//        logger.info("회원가입 실패");
//        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//    }
//
//    @ApiOperation(value = "회원 수정", notes = "기존의 회원 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = Map.class)
//    @PutMapping("/{nickname}")
//    public ResponseEntity<String> modifyMember (
//            @PathVariable("nickname") String nickname, @RequestBody @ApiParam(value = "회원 정보 수정시 필요한 정보(아이디, 비밀번호, 이름, 이메일).", required = true) Member member) throws Exception {
//        logger.info("회원 수정");
//        if(memberService.modifyMember(member)) {
//            logger.info("회원 수정 성공");
//            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//        }
//        logger.info("회원 수정 실패");
//        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//    }
//
//    @ApiOperation(value = "회원 삭제", notes = "회원을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = Map.class)
//    @DeleteMapping("/{nickname}")
//    public ResponseEntity<String> deleteMember(
//            @ApiParam(value = "회원 정보 삭제시 필요한 정보(아이디).", required = true)
//            @PathVariable("nickname") String nickname) throws Exception {
//        logger.info("회원 삭제");
//        if(memberService.deleteMember(nickname)) {
//            logger.info("회원 삭제 성공");
//            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//        }
//        logger.info("회원 삭제 실패");
//        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//    }
//}
