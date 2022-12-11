package com.example.back.api.service.house;


import java.util.List;

public interface HouseService {
    List<String> getSidoList();
    String getHouse(int code, String date) throws Exception;
}
