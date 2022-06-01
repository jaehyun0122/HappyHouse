package com.ssafy.happyhouse.model.service;

import java.util.List;

//import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoGugunDongCode;

public interface LocationService {

	List<SidoGugunDongCode> getSido() throws Exception;
	List<SidoGugunDongCode> getGugunInSido(String sido) throws Exception;
	List<SidoGugunDongCode> getDongInGugun(String gugun) throws Exception;
//	List<HouseInfo> getHouseInDong(String dong) throws Exception;
	
}
