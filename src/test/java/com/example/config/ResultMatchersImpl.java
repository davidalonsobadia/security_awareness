package com.example.config;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.ResultMatcher;

public class ResultMatchersImpl {

	/*
	 * RESULT MATCHERS
	 */
	public static ResultMatcher isOk(){
		return status().isOk();
	}
	public static ResultMatcher isCreated(){
		return status().isCreated();
	}
	public static ResultMatcher isForbidden(){
		return status().isForbidden();
	}
	public static ResultMatcher isNoContent(){
		return status().isNoContent();
	}
	public static ResultMatcher isConflict(){
		return status().isConflict();
	}
	public static ResultMatcher isNotFound(){
		return status().isNotFound();
	}
	public static ResultMatcher isUnauthorized(){
		return status().isUnauthorized();
	}
}
