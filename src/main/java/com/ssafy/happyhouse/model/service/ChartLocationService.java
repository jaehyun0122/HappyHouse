package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.ChartSidoGugunDongCode;

public interface ChartLocationService {
	List<ChartSidoGugunDongCode> getSido() throws Exception;
	List<ChartSidoGugunDongCode> getGugunInSido(String sido) throws Exception;
	List<ChartSidoGugunDongCode> getDongInGugun(String gugun) throws Exception;
	boolean update(ChartSidoGugunDongCode chartSidoGugunDongCode) throws Exception;
}
