package com.example.config;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.repositories.UserRepositoryTest;
import com.example.test.security.OAuth2Test;

@RunWith(Suite.class)
@Suite.SuiteClasses({OAuth2Test.class, UserRepositoryTest.class})
public class MyOauthExampleApplicationTests {

}
