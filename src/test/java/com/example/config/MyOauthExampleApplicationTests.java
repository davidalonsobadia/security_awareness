package com.example.config;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.repositories.ActivityRepositoryTest;
import com.example.repositories.UserRepositoryTest;
import com.example.repository.NotificationStatusRepository;
import com.example.test.security.OAuth2Test;

@RunWith(Suite.class)
@Suite.SuiteClasses({OAuth2Test.class, 
	UserRepositoryTest.class, 
	ActivityRepositoryTest.class,
	NotificationStatusRepository.class})
public class MyOauthExampleApplicationTests {

}
