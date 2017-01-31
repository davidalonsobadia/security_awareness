package com.example.repositories;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.persistence.Entity;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import com.fasterxml.jackson.core.type.TypeReference;


public abstract class AbstractRepositoryTest extends MockMvcTest{
	
	abstract protected String getResourceName();
	
    protected List<? extends Entity> getEntitiesList(String content) throws Exception{
    	JSONObject json = new JSONObject(content);    	    	
    	JSONArray arrays = json.getJSONObject("_embedded").getJSONArray(getResourceName());
    	return mapper.readValue(arrays.toString(), new TypeReference<List<?>>(){});
    }
    
    
	protected void verify(ResultActions action, ResultMatcher matcher) throws Exception {
		action.andExpect(matcher);	
	}
	
	/*
	 * RESULT ACTIONS
	 */
	protected ResultActions create(User user, String body) throws Exception{
		RequestPostProcessor bearerToken = oauthHelper.bearerToken(client(), user);
		return mvc.perform(
    			post("/" + getResourceName())
    				.with(bearerToken)
    				.header("Content-Type", "application/json")
    				.content(body)
    			);
	}
	protected ResultActions read(org.springframework.security.core.userdetails.User user, String... params) throws Exception{
    	RequestPostProcessor bearerToken = oauthHelper.bearerToken(client(), user);
    	String route = params.length > 0 ? "/" + params[0] : "";
    	return mvc.perform(
    			get("/" + getResourceName() + route)
    				.with(bearerToken)
    			);
	}
	protected ResultActions remove(User user, String... params) throws Exception{
		RequestPostProcessor bearerToken = oauthHelper.bearerToken(client(), user);
		String route = params.length > 0 ?  "/" + params[0] : "";
    	return mvc.perform(
    			delete("/" + getResourceName() + route)
    				.with(bearerToken)
    			);
	}
	
	
	/*
	 * RESULT MATCHERS
	 */
	protected ResultMatcher isOk(){
		return status().isOk();
	}
	protected ResultMatcher isCreated(){
		return status().isCreated();
	}
	protected ResultMatcher isForbidden(){
		return status().isForbidden();
	}
	protected ResultMatcher isNoContent(){
		return status().isNoContent();
	}
	protected ResultMatcher isConflict(){
		return status().isConflict();
	}
	protected ResultMatcher isNotFound(){
		return status().isNotFound();
	}
	protected ResultMatcher isUnauthorized(){
		return status().isUnauthorized();
	}
}
