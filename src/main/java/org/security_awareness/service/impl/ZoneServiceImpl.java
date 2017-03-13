package org.security_awareness.service.impl;

import org.security_awareness.model.Zone;
import org.security_awareness.repository.ZoneRepository;
import org.security_awareness.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ZoneServiceImpl implements ZoneService{

	@Autowired
	private ZoneRepository zoneRepository;
	
	@Override
	public Zone findByName(String name){
		return zoneRepository.findByName(name);
	}

	@Override
	public Zone findById(long id) {
		return zoneRepository.findOne(id);
	}
}
