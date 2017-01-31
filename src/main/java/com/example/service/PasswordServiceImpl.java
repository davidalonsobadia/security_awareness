package com.example.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.Password;
import com.example.repository.PasswordRepository;

@Service
@Transactional
public class PasswordServiceImpl implements PasswordService {
	
	@Autowired
	PasswordRepository passwordRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void save(Password password) {
		password.setPassword(passwordEncoder.encode(password.getPassword()));
		passwordRepository.save(password);
	}

}
