package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.ChartSidoGugunDongCode;
import com.ssafy.happyhouse.model.service.ChartLocationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/chart")
public class ChartLocationController {
	
	private final ChartLocationService chartLocationService;

	@Autowired
	public ChartLocationController(ChartLocationService chartLocationService) {
		this.chartLocationService = chartLocationService;
	}

	@GetMapping("/sido")
	public ResponseEntity<List<ChartSidoGugunDongCode>> sido() throws Exception {
		return new ResponseEntity<List<ChartSidoGugunDongCode>>(chartLocationService.getSido(), HttpStatus.OK);
	}

	
	@GetMapping("/gugun")
	public ResponseEntity<List<ChartSidoGugunDongCode>> gugun(
			@RequestParam("sido") String sido) throws Exception {
		
		return new ResponseEntity<List<ChartSidoGugunDongCode>>(chartLocationService.getGugunInSido(sido), HttpStatus.OK);
	}

	@GetMapping("/dong")
	public ResponseEntity<List<ChartSidoGugunDongCode>> dong(
			@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<ChartSidoGugunDongCode>>(chartLocationService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<String> update(
			@RequestBody ChartSidoGugunDongCode chartSidoGugunDongCode ) throws Exception {
		System.out.println(chartSidoGugunDongCode);
		if(chartLocationService.update(chartSidoGugunDongCode)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.OK);
	}
}
