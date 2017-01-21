package com.example.repositories;

import java.util.List;

import javax.persistence.Entity;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.model.User;
import com.fasterxml.jackson.core.type.TypeReference;

public abstract class AbstractRepositoryTest extends MockMvcTest{
	
	abstract protected String getResourceName();
	
    protected List<? extends Entity> getEntitiesList(String content) throws Exception{
    	JSONObject json = new JSONObject(content);    	    	
    	JSONArray arrays = json.getJSONObject("_embedded").getJSONArray(getResourceName());
    	return mapper.readValue(arrays.toString(), new TypeReference<List<User>>(){});
    }

}
