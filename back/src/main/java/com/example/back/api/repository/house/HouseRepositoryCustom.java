package com.example.back.api.repository.house;

import java.util.List;

public interface HouseRepositoryCustom {
    List<String> getSidoList();
    List<String> getGugunList(String sidoName);
    List<String> getDongList(String gugunName);
    Integer getCode(String sidoName, String gugunName);
}
