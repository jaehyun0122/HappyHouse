package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

//import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoGugunDongCode;

@Mapper
public interface LocationMapper {

	List<SidoGugunDongCode> getSido() throws SQLException;
	List<SidoGugunDongCode> getGugunInSido(String sido) throws SQLException;
	List<SidoGugunDongCode> getDongInGugun(String gugun) throws SQLException;
//	List<HouseInfo> getAptInDong(String dong) throws SQLException;
	
}
