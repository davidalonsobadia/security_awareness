package com.example.config;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.repositories.ActivityRepositoryTest;
import com.example.repositories.NotificationStatusRepositoryTest;
import com.example.repositories.ResourceRepositoryTest;
import com.example.repositories.UserRepositoryTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({UserRepositoryTest.class, 
	ActivityRepositoryTest.class,
	NotificationStatusRepositoryTest.class,
	ResourceRepositoryTest.class})
public class RepositoryTests {

}
