package com.api.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewTestController {
    @RequestMapping(value = "/hello")
    public String ViewTest() {
        return "hello";
    }



    @RequestMapping(value="/testBoardList")
    public void testBoardList () {

    }

    @GetMapping(value = "/testBoardContent")
    public void testBoardContent(@RequestParam String boardSeq) {
    	
    }

    @GetMapping(value = "/login/loginForm")
    public void loginForm() {

    }
    @GetMapping(value = "/members/addMemberForm")
    public void addMemberForm() {

    }


}
