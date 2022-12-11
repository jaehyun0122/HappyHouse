package com.example.back.api.Entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "city_info")
@Getter
public class SiDoCode {
    @Id @GeneratedValue
    private int id;
    private int sidoCode;
    private String sidoName;
    private String gugunName;
    private String dongName;
}
