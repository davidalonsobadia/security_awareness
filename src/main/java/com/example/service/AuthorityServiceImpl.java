package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Authority;
import com.example.model.Role;
import com.example.repository.AuthorityRepository;

@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Override
	public Authority findByRole(Role role) {
		return authorityRepository.findByRole(role);
	}

}
