package com.example.back.api.controller;

import com.example.back.api.repository.house.HouseRepositoryImpl;
import com.example.back.api.service.house.HouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
@Api("아파트 정보 컨트롤러")
public class HouseController {

    @Autowired
    HouseRepositoryImpl houseRepository;

    @Autowired
    HouseService houseService;

    @ApiOperation(value = "전국 시도 이름" , notes = "파라미터 없이 get요청")
    @GetMapping("/house/sido")
    public ResponseEntity<List<String>> getSidoList(){
        List<String> sido = new ArrayList<>();
        try{
            sido = houseRepository.getSidoList();
            return new ResponseEntity<>(sido, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(sido, HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "시도 이름에 맞는 구군 가져오기")
    @GetMapping("/house/gugun")
    public ResponseEntity<List<String>> getGugunList(@RequestParam String sidoName){
        try{
            List<String> gugunList = houseRepository.getGugunList(sidoName);
            return new ResponseEntity<>(gugunList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "구군을 받아 동 리스트 반환")
    @GetMapping("/house/dong")
    public ResponseEntity<List<String>> getDongList(@RequestParam String gugunName){
        try{
            List<String> dongList = houseRepository.getDongList(gugunName);
            return new ResponseEntity<>(dongList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "시/도, 구/군 정보를 받아 코드 리턴")
    @GetMapping("/house")
    public ResponseEntity<Integer> getHouseCode(@RequestParam String sidoName, @RequestParam String gugunName){
        System.out.println(sidoName+" "+gugunName);
        try {
            Integer houseCode = houseRepository.getCode(sidoName, gugunName);

            return new ResponseEntity<>(houseCode, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "위치/연월(202212) 정보 받아 아파트 거래정보 리턴")
    @GetMapping("/houseDeal")
    public ResponseEntity<String> getHouseDeal(@RequestParam int code,
                                               @RequestParam String date){
        System.out.println(code+" "+date);
        try {
            String houseDeal = houseService.getHouse(code, date);

            return new ResponseEntity<>(houseDeal, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
