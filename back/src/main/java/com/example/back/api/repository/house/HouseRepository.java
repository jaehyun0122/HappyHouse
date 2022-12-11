package com.example.back.api.repository.house;

import com.example.back.api.Entity.SiDoCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<SiDoCode, Integer>, HouseRepositoryCustom{
}
