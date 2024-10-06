package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.service.SongService;
import com.kodnest.tunehub.serviceimpl.TuneHubServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	TuneHubServiceImpl tunehubServiceImp;

	@Autowired
	SongService songService;
	
	@PostMapping(value="/register")
	public String addUser(@ModelAttribute User user){

		//email taken from registration form 
		String email = user.getEmail();

		//checking if email as entered in registration is present in DB or not
		boolean status=	tunehubServiceImp.emailExists(email);

		if(status==false) {
			tunehubServiceImp.addUser(user);
			return "home";
		}
		else {
			return "alreadyuser";
		}

	}
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email,
			@RequestParam("password") String password ,HttpSession session,Model model)
	{

		if(tunehubServiceImp.validateUser(email,password)==true) {
			String role = tunehubServiceImp.getRole(email);
			session.setAttribute("email",email);
			if(role.equalsIgnoreCase("admin")) {
				return "adminhome";
			}
			else {
				User user = tunehubServiceImp.getUser(email);
				boolean premium = user.isPremium();
				//logic
				List<Song> fetchAllSongs = songService.fetchAllSongs();
				model.addAttribute("songs",fetchAllSongs);
				
				model.addAttribute("isPremium",premium);
				return "customerhome";
			}

		}
		else {
			return "signup";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return  "index";
	}
	
	@GetMapping("/exploresongs")
	public String exploresongs(String email) {
		return  email;
	}
	
}


