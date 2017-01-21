package com.example;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.model.Authority;
import com.example.model.Role;
import com.example.model.User;
import com.example.repository.AuthorityRepository;
import com.example.repository.UserRepository;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		loadSecurityContext();
		loadUsersData();
		
	}
	
	private void loadUsersData(){
				
		Authority user = new Authority(Role.USER);
		Authority anonymous = new Authority(Role.ANONYMOUS);
		Authority admin = new Authority(Role.ADMIN);
		Authority superadmin = new Authority(Role.SUPERADMIN);
		
		authorityRepository.save(user);
		authorityRepository.save(anonymous);
		authorityRepository.save(admin);
		authorityRepository.save(superadmin);
		
		userRepository.save(new User(
				"David", // first name
				"Alonso", // last name
				passwordEncoder.encode("123456"), // password
				"alonso_50", // username
				superadmin) //Role
		);
		userRepository.save(new User(
				"Sergi", 
				"Alonso", 
				passwordEncoder.encode("123456"), 
				"sergi_50",
				user)
		);
		userRepository.save(new User(
				"Juan", 
				"Caubet", 
				passwordEncoder.encode("123456"), 
				"juan_50",
				anonymous)
		);
		userRepository.save(new User(
				"Victor",
				"Pomareda",
				passwordEncoder.encode("123456"),
				"victor_50",
				anonymous)
		);
	}
	
	void loadSecurityContext(){
		
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		
//		Role[] roles = Role.values();
//		for(Role role: roles){
//			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//		}
		
		authorities.add(new SimpleGrantedAuthority(Role.SUPERADMIN.getRoleName()));
		
		org.springframework.security.core.userdetails.User springUser = new org.springframework.security.core.userdetails.User(
				"test", "test", authorities);
		Authentication authentication = new UsernamePasswordAuthenticationToken(springUser, null,
			      springUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
