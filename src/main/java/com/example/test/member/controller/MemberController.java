package com.example.test.member.controller;

import com.example.test.config.generic.Result;
import com.example.test.member.Member;
import com.example.test.member.controller.dto.*;
import com.example.test.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;
    @PostMapping("/googleLogin")
    public ResponseEntity<ResponseDto> googleLogin(@RequestBody AccessTokenDto accessTokenDto , HttpServletResponse response){
        String accessToken = accessTokenDto.getAccessToken();
        return ResponseEntity.ok().body(memberService.googleLogin(accessToken));
    }

    @PostMapping("/authTaker")
    public ResponseEntity<AuthTakerDto> authTaker(@RequestBody AuthTakerRequest authTakerRequest){
        return ResponseEntity.ok().body(memberService.authTaker(authTakerRequest));
    }

    @GetMapping("/getProfile")
    public Result hello(@RequestParam String id){
        return new Result(memberService.getFoodsByGiver(id));
    }

    @GetMapping("/saveTest")
    public String saveTest(){
        memberService.testMember();
        return "OK";
    }

    @GetMapping("/all")
    public List<Member> allUser(){
        List<Member> allUser = memberService.getAll();
        return allUser;
    }
}
