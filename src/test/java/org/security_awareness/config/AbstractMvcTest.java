package org.security_awareness.config;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.RequestPostProcessor;


public abstract class AbstractMvcTest extends MockMvcTest{
	
	abstract protected String getResourceName();
	
    protected <T> List<T> getEntitiesList(String content, Class<T> cls) throws Exception{
    	JSONObject json = new JSONObject(content);    	    	
    	JSONArray arrays = json.getJSONObject("_embedded").getJSONArray(getResourceName());
    	
    	List<T> listElements = new ArrayList<>();
    	
    	for(int i = 0 ; i < arrays.length() ; i++){
    		JSONObject obj = arrays.getJSONObject(i);
    		T element = mapper.readValue(obj.toString(), cls);
    		listElements.add(element);
    	}
    	return listElements;
    }
    
    
	protected void verify(ResultActions action, ResultMatcher matcher) throws Exception {
		action.andExpect(matcher);	
	}
	
	/*
	 * RESULT ACTIONS
	 */
	protected ResultActions create(User user, String body, String... path) throws Exception{
		RequestPostProcessor bearerToken = oauthHelper.bearerToken(client(), user);
		String route = "";
		if(path != null && path.length > 0)
			route = path[0];
		return mvc.perform(
    			post("/" + getResourceName() + route)
    				.with(bearerToken)
    				.header("Content-Type", "application/json")
    				.content(body)
    			);
	}
	protected ResultActions readWithVariables(org.springframework.security.core.userdetails.User user,
			String... variables) throws Exception{
    	RequestPostProcessor bearerToken = oauthHelper.bearerToken(client(), user);
    	String route = variables.length > 0 ? "/" + variables[0] : "";
    	return mvc.perform(
    			get("/" + getResourceName() + route)
    				.with(bearerToken)
    			);
	}
	
	protected ResultActions readWithParams(org.springframework.security.core.userdetails.User user, 
			@SuppressWarnings("unchecked") Map<String, Object>... params) throws Exception{
    	RequestPostProcessor bearerToken = oauthHelper.bearerToken(client(), user);

    	String route = "";    	
    	if(params.length > 0){
    		route = "?";
    		Iterator<Entry<String, Object>> it = params[0].entrySet().iterator();
    		while(it.hasNext()){
    			Map.Entry<String, Object> entryMap = it.next();
    			route += entryMap.getKey() + "=" + entryMap.getValue().toString();
    			if(it.hasNext())
    				route += "&";
    		}    		
    	}	
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
}
