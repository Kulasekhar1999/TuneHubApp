package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.serviceimpl.SongServiceImpl;

@Controller
public class SongController {
	@Autowired
	SongServiceImpl songServiceImpl;
	
	@PostMapping("/addsong")
	public String addsong(@ModelAttribute Song song) {
		boolean songstatus=songServiceImpl.songExists(song.getName());
		if(songstatus==false) {
			songServiceImpl.addSong(song);
			System.out.println("Song Added Successfully");
		}
		else {
			System.out.println("Song Already Exists");
		}
		songServiceImpl.addSong(song);
		return "adminhome";
	}
	
	
@GetMapping("/viewsongs")
public String viewsongs(Model model) {
	List<Song> songList=songServiceImpl.fetchAllSongs();
	model.addAttribute("songs", songList);
	
	return "displaysongs";
}

@GetMapping("listen")
public String listenSongs(Model model) {
	boolean premium=true;
	if(premium) {
	List<Song> songs = songServiceImpl.fetchAllSongs();
	model.addAttribute("songs", songs);
	return "listensongs";
	}
	else {
		return "subscriptionform";
	}
}
}
