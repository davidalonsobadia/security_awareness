package org.security_awareness.controller;

import org.junit.Test;
import org.security_awareness.config.AbstractMvcTest;
import static org.security_awareness.config.ResultMatchersImpl.*;


public class ActivityControllerTest extends AbstractMvcTest{

	private static final String RESOURCE_NAME = "activities";
	
	private final String POST_BODY = "{\n\t\"name\": \"Test Fuck Event\","
			+ "\n\t\"description\": \"Tech Congress\","
			+ "\n\t\"place\": \"Barcelona Fira\","
			+ "\n\t\"zone\": \"http://localhost:8080/api/zones/1\","
			+ "\n\t\"manager\": \"http://localhost:8080/api/users/2\","
			+ "\n\t\"type\": 2, "
			+ "\n\t\"numRepeats\": 2,"
			+ "\n\t\"dateStart\": \"21/03/2017 09:30:00\","
			+ "\n\t\"dateEnd\": \"21/03/2017 11:00:00\"\n}";
	
	private final String WRONG_POST_BODY = "{\n\t\"name\": \"Test Fuck Event\","
			+ "\n\t\"description\": \"Tech Congress\","
			+ "\n\t\"place\": \"Barcelona Fira\","
			+ "\n\t\"zone\": \"FAKE ZONE\","
			+ "\n\t\"manager\": \"http://localhost:8080/api/users/2\","
			+ "\n\t\"type\": 2, "
			+ "\n\t\"numRepeats\": 2,"
			+ "\n\t\"dateStart\": \"21/03/2017 09:30:00\","
			+ "\n\t\"dateEnd\": \"21/03/2017 11:00:00\"\n}";
	
	
	
	@Override
	protected String getResourceName() {
		return RESOURCE_NAME;
	}

	
	@Test
	public void Should_SaveActivity_When_AdminAndOkBody() throws Exception{
		verify(create(admin(), POST_BODY), isCreated());
	}
	
	@Test
	public void Should_Forbidden_When_UserRole() throws Exception{
		verify(create(user(), POST_BODY), isForbidden());
	}
	
	@Test(expected = Exception.class)
	public void Should_Error_When_AdminAndWrongBody() throws Exception{
		create(admin(), WRONG_POST_BODY);
	}

}
