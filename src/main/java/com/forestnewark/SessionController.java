package com.forestnewark;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by forestnewark on 4/16/17.
 */
@Controller
public class SessionController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpSession session, HttpServletResponse response){
        Cookie cookie = new Cookie("crazyCookie","soCrazy!");
        cookie.setMaxAge(60*60); //1 hour
        response.addCookie(cookie);
        System.out.println(session.getId());
        return "welcome";


    }
}
