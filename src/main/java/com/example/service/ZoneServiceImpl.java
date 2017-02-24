package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Zone;
import com.example.repository.ZoneRepository;

@Service
@Transactional
public class ZoneServiceImpl implements ZoneService{

	@Autowired
	private ZoneRepository zoneRepository;
	
	@Override
	public Zone findByName(String name){
		return zoneRepository.findByName(name);
	}
}
