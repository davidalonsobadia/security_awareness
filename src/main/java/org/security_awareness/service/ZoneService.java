package org.security_awareness.service;

import org.security_awareness.model.Zone;

public interface ZoneService {

	Zone findByName(String name);

	Zone findById(long id);	
}
