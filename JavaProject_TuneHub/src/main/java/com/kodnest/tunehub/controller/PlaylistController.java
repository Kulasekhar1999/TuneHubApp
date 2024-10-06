package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.service.PlaylistService;
import com.kodnest.tunehub.serviceimpl.SongServiceImpl;

@Controller
public class PlaylistController {

	@Autowired
	SongServiceImpl songServiceImpl;

	@Autowired
	PlaylistService playlistService;
	
	@GetMapping("/createplaylists")
	public String createPlaylists(Model model) {
		List<Song> songList=songServiceImpl.fetchAllSongs();
		model.addAttribute("songs", songList);

		return "createplaylists";
	}
	@PostMapping("/addplaylist")
	public String addplaylist(@ModelAttribute Playlist playlist) {
		//updating the playlist table
		playlistService.addplaylist(playlist);
		//updating the song table
		List<Song> songs = playlist.getSongs();
		for (Song s : songs) {
			s.getPlaylists().add(playlist);
			songServiceImpl.updateSong(s);
		}
		return "adminhome";
	}
	
	
	@GetMapping("/displayplaylist")
	public String viewplaylists(Model model) {
		List<Playlist> allplaylist=playlistService.fetchAllPlaylists();
		model.addAttribute("allplaylist",allplaylist);
		return "displayplaylist";
	}
}
