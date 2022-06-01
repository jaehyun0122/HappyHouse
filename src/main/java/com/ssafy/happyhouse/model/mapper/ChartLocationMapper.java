package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.ChartSidoGugunDongCode;

@Mapper
public interface ChartLocationMapper {
	List<ChartSidoGugunDongCode> getSido() throws SQLException;
	List<ChartSidoGugunDongCode> getGugunInSido(String sido) throws SQLException;
	List<ChartSidoGugunDongCode> getDongInGugun(String gugun) throws SQLException;
	int updateSido(String sidoCode) throws SQLException;
	int updateGugun(String gugunCode) throws SQLException;
	int updateDong(String dongCode) throws SQLException;
}
