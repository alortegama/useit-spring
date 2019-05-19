package com.alvaro.useitspring.models.service;

import com.alvaro.useitspring.models.dao.IUsuariosDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	IUsuariosDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDao.findById(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
	}
}
