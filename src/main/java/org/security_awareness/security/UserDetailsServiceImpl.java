package org.security_awareness.security;

import java.util.ArrayList;
import java.util.Collection;

import org.security_awareness.model.Role;
import org.security_awareness.model.User;
import org.security_awareness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User userFromDatabase = userRepository.findByEmail(email);
		Role role = userFromDatabase.getAuthority().getRole();
		
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        
		org.springframework.security.core.userdetails.User userDetails = 
				new org.springframework.security.core.userdetails.User(
						userFromDatabase.getEmail(),
						userFromDatabase.getPassword().getString(), 
						grantedAuthorities);
		
		return userDetails;
	}
}
