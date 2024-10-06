package com.kodnest.tunehub.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.repository.TuneHubRepository;
import com.kodnest.tunehub.service.TuneHubService;

@Service
public class TuneHubServiceImpl implements TuneHubService{

	@Autowired
	TuneHubRepository tunehubRepository;

	public String addUser( User user) {
		tunehubRepository.save(user);
		return " User added Succesfully";
	}

	//To check the duplicate entries
	public boolean emailExists(String email) {
		if(	tunehubRepository.findByEmail(email)!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean validateUser(String email, String password) {
		User user = tunehubRepository.findByEmail(email);
		if(user!=null) {
		String dbpwd = user.getPassword();
		if(dbpwd.equals(password)) {
			return true;
		}
		else {
			return false;
		}
	}
		else {
			return false;
		}
	}

	public String getRole(String email) {
		User user = tunehubRepository.findByEmail(email);
		return user.getRole();
	}
	@Override
	public User getUser(String email) {
		return tunehubRepository.findByEmail(email);
		
	}

	@Override
	public void updateUser(User user) {
		tunehubRepository.save(user);
	}
}
