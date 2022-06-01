package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dto.ChartSidoGugunDongCode;
import com.ssafy.happyhouse.model.mapper.ChartLocationMapper;

@Service
public class ChartLocationServiceImpl implements ChartLocationService {

	private final ChartLocationMapper chartLocationMapper;
	
	@Autowired
	public ChartLocationServiceImpl(ChartLocationMapper chartLocationMapper) {
		this.chartLocationMapper = chartLocationMapper;
	}

	@Override
	public List<ChartSidoGugunDongCode> getSido() throws Exception {
		// TODO Auto-generated method stub
		return chartLocationMapper.getSido();
	}

	@Override
	public List<ChartSidoGugunDongCode> getGugunInSido(String sido) throws Exception {
		// TODO Auto-generated method stub
		return chartLocationMapper.getGugunInSido(sido);
	}

	@Override
	public List<ChartSidoGugunDongCode> getDongInGugun(String gugun) throws Exception {
		// TODO Auto-generated method stub
		return chartLocationMapper.getDongInGugun(gugun);
	}

	@Transactional
	@Override
	public boolean update(ChartSidoGugunDongCode chartSidoGugunDongCode) throws Exception {
		boolean bool = true;
		if(chartLocationMapper.updateSido(chartSidoGugunDongCode.getSidoCode()) != 1)
			bool = false;
		if(chartLocationMapper.updateGugun(chartSidoGugunDongCode.getGugunCode()) != 1)
			bool = false;
		if(chartLocationMapper.updateDong(chartSidoGugunDongCode.getDongCode()) != 1)
			bool = false;
		return bool;
	}

}
