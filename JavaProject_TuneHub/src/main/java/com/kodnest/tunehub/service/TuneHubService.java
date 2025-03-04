package com.kodnest.tunehub.service;

import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.User;

@Service
public interface TuneHubService {

	public String addUser( User user);

	public boolean emailExists(String email);

	public boolean validateUser(String email, String password);

	public String getRole(String email);

	public User getUser(String email);

	public void updateUser(User user);
}
