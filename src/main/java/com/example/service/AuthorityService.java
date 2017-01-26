package com.example.service;

import com.example.model.Authority;
import com.example.model.Role;

public interface AuthorityService {
	
	Authority findByRole(Role role);

}
