package org.security_awareness.service.impl;

import org.security_awareness.model.Authority;
import org.security_awareness.model.Role;
import org.security_awareness.repository.AuthorityRepository;
import org.security_awareness.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
