package com.dac.usersoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.dac.usersoft.model.User;
import com.dac.usersoft.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




/**
 * Author : rajgs
 * Date   : 31 Dec 2024
 * Time   : 11:58:09 am
*/

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/index")
	public String showIndexPage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegisterPage(Model m) {
		User user =new User();
		m.addAttribute("user", user);
		return "register"; //user object + view
	}
	

	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute User user, Model model) {	
		userService.registerUser(user); //Invokes service class method
		 model.addAttribute("message", "Registration successful! Please log in.");
	        return "login";
	}
	
	@PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        var user = userService.loginUser(email, password);
        if (user.isPresent()) {
            session.setAttribute("loggedUser", user.get()); //create a session
            return "redirect:/home"; // returns to GetMapping path
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

	@GetMapping("/home")
    public String showHomePage(HttpSession session, Model model) {
        var user = session.getAttribute("loggedUser");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "home"; //returns view + user object
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // end session
        return "redirect:/index";
    }

	
}
