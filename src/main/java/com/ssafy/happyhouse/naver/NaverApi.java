package com.ssafy.happyhouse.naver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class NaverApi {

	private final JSONParser jsonParser;

	@Autowired
	public NaverApi(JSONParser jsonParser) {
		this.jsonParser = jsonParser;
	}
	
	@GetMapping("/search/img/{aptName}")
	public ResponseEntity<List<Img>> searchImg(
			@PathVariable("aptName") String aptName) throws Exception {
		return new ResponseEntity<List<Img>>(jsonParser.searchImg(aptName),HttpStatus.OK);
	}
	
	@GetMapping("/search/news/{aptName}")
	public ResponseEntity<List<News>> searchNews(
			@PathVariable("aptName") String aptName) throws Exception {
		return new ResponseEntity<List<News>>(jsonParser.searchNews(aptName),HttpStatus.OK);
	}
}
