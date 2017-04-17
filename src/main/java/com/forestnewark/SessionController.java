package com.forestnewark;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class SessionController {

    @RequestMapping("/")
    public String index(Model model,HttpSession session,HttpServletRequest request){


        Cookie[] cookies = request.getCookies();
        session.setAttribute("hasUserName",false);
        session.setAttribute("hasPassword",false);
        if (cookies.length > 0){
            for(int i = 0;i < cookies.length;i++){
                if(cookies[i].getName().equals("username")){
                    model.addAttribute("username",cookies[i].getValue());
                    session.setAttribute("username",cookies[i].getValue());
                    session.setAttribute("hasUserName",true);
                }else if(cookies[i].getName().equals("password")){
                    model.addAttribute("password",cookies[i].getValue());
                    session.setAttribute("password",cookies[i].getValue());
                    session.setAttribute("hasPassword",true);
                }
            }
        }

        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Model model,HttpSession session, HttpServletResponse response, @RequestParam("inputEmail") String username,@RequestParam("inputPassword") String password){

//        if(session.getAttribute("username").equals("admin") && session.getAttribute("password").equals("admin123")){
//            model.addAttribute("admin",true);
//        }else {
//            model.addAttribute("admin",false);
//        }

        model.addAttribute("isAdmin",false);
        if(!session.getAttribute("hasUserName").equals(true) && !session.getAttribute("hasPassword").equals(true)){
            Cookie cookieUsername = new Cookie("username", username);
            Cookie cookiePassword = new Cookie("password", password);
            cookieUsername.setMaxAge(60*60); //1 hour
            cookiePassword.setMaxAge(60*60);
            response.addCookie(cookieUsername);
            response.addCookie(cookiePassword);

        }

        return "welcome";


    }
}
