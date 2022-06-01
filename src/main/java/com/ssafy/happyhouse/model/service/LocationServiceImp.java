package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoGugunDongCode;
import com.ssafy.happyhouse.model.mapper.LocationMapper;

@Service
public class LocationServiceImp implements LocationService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<SidoGugunDongCode> getSido() throws Exception {
		return sqlSession.getMapper(LocationMapper.class).getSido();
	}

	@Override
	public List<SidoGugunDongCode> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(LocationMapper.class).getGugunInSido(sido);
	}

	@Override
	public List<SidoGugunDongCode> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(LocationMapper.class).getDongInGugun(gugun);
	}

//	@Override
//	public List<HouseInfo> getHouseInDong(String dong) throws Exception {
//		return sqlSession.getMapper(LocationMapper.class).getAptInDong(dong);
//	}
}
