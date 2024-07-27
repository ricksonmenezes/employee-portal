package com.rgarage.employeeportal.employee.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

        @GetMapping("/signin")
        public String login() {
            return "loginpage";
        }
        @GetMapping("/home")
        public String homePage(HttpServletResponse response) {
            return "homepage";
        }


}
