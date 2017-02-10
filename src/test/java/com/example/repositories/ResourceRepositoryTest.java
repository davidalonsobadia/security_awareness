package com.example.repositories;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.Entity;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import com.example.config.AbstractMvcTest;

public class ResourceRepositoryTest extends AbstractMvcTest{

	private static final String RESOURCE_NAME = "resources";
	
	@Test
	public void Should_GetAllRseourcesSorted_When_ValidUser() throws Exception{
		String route = "search/findAllByOrderByCreationDateDesc";
		ResultActions result = readWithVariables(user(), route);
		
		List<? extends Entity> resource = getEntitiesList(result.andReturn().getResponse().getContentAsString());
		
		assertEquals(resource.size(), 4);
	}

	@Override
	protected String getResourceName() {
		return RESOURCE_NAME;
	}
	
	
	

}
