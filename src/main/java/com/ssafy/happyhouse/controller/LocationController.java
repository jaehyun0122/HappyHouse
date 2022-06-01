package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.Board;
import com.ssafy.happyhouse.model.dto.SidoGugunDongCode;
import com.ssafy.happyhouse.model.service.LocationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/map")
@Api("Location 컨트롤러  API V1")
public class LocationController {

	private final LocationService locationService;

	@Autowired
	public LocationController(LocationService locationService) {
		this.locationService = locationService;
	}

	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunDongCode>> sido() throws Exception {
//		logger.info("sido - 호출");
		return new ResponseEntity<List<SidoGugunDongCode>>(locationService.getSido(), HttpStatus.OK);
	}

	@ApiOperation(value = "구군 정보", notes = "전국의 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunDongCode>> gugun(
			@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
//		logger.info("gugun - 호출");
		return new ResponseEntity<List<SidoGugunDongCode>>(locationService.getGugunInSido(sido), HttpStatus.OK);
	}

	@ApiOperation(value = "동 정보", notes = "전국의 동을 반환한다.", response = List.class)
	@GetMapping("/dong")
	public ResponseEntity<List<SidoGugunDongCode>> dong(
			@RequestParam("gugun") @ApiParam(value = "구군코드.", required = true) String gugun) throws Exception {
		return new ResponseEntity<List<SidoGugunDongCode>>(locationService.getDongInGugun(gugun), HttpStatus.OK);
	}
}
