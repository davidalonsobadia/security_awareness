package org.security_awareness.repositories;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.security_awareness.config.AbstractMvcTest;
import org.security_awareness.model.Resource;
import org.springframework.test.web.servlet.ResultActions;

public class ResourceRepositoryTest extends AbstractMvcTest{

	private static final String RESOURCE_NAME = "resources";
	
	@Test
	public void Should_GetAllRseourcesSorted_When_ValidUser() throws Exception{
		String route = "search/findAllByOrderByCreationDateDesc";
		ResultActions result = readWithVariables(user(), route);
		
		List<Resource> resource = getEntitiesList(
				result.andReturn().getResponse().getContentAsString(),
				Resource.class);
		
		assertEquals(resource.size(), 4);
	}

	@Override
	protected String getResourceName() {
		return RESOURCE_NAME;
	}
	
	
	

}
