package com.example.demo.config;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username)
	{
		if("azhagu".equals(username)) {
			return new User("azhagu","$2a$10$YXbiQ1QAcRDZZi9UHfhPZOA0uB7DntJ3kCbhNHw0SoK9W8DrvM3bS",
					new ArrayList<>());
		}
		else
		{
			throw new UsernameNotFoundException("User Name not Found");
		}
		
	}
}
