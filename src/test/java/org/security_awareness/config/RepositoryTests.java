package org.security_awareness.config;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.security_awareness.repositories.ActivityRepositoryTest;
import org.security_awareness.repositories.NotificationStatusRepositoryTest;
import org.security_awareness.repositories.ResourceRepositoryTest;
import org.security_awareness.repositories.UserRepositoryTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({UserRepositoryTest.class, 
	ActivityRepositoryTest.class,
	NotificationStatusRepositoryTest.class,
	ResourceRepositoryTest.class})
public class RepositoryTests {

}
