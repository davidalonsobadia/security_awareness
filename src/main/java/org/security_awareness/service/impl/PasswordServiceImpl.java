package org.security_awareness.service.impl;

import javax.transaction.Transactional;

import org.security_awareness.model.Password;
import org.security_awareness.repository.PasswordRepository;
import org.security_awareness.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
