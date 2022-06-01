package com.ssafy.happyhouse.naver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JSONParser {
	
	private final Connection con;

	@Autowired
	public JSONParser(Connection con) {
		this.con = con;
	}
	
	private ObjectMapper mapper = new ObjectMapper();
	private JsonNode jsonNode;

	public List<Img> searchImg(String searchName) {
		String json = con.searchImg(searchName);
		List<Img> list = null;
		try {
			if(json != null) {
				jsonNode = mapper.readTree(json);
				list =  mapper.readValue(jsonNode.get("items").toString(), new TypeReference<List<Img>>() {});
			}
			
		}
		 catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<News> searchNews(String searchKeyword) {
		String json = con.searchNews(searchKeyword);
		List<News> list = null;
		try {
			if(json != null) {
				jsonNode = mapper.readTree(json);
				list =  mapper.readValue(jsonNode.get("items").toString(), new TypeReference<List<News>>() {});
			}
			
		}
		 catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
