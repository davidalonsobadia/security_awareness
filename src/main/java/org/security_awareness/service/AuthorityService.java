package org.security_awareness.service;

import org.security_awareness.model.Authority;
import org.security_awareness.model.Role;

public interface AuthorityService {
	
	Authority findByRole(Role role);

}
