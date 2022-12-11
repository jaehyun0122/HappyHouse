package com.example.back.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HouseDto {

    @ApiModelProperty(value = "시도코드")
    private String sidoCode;
    @ApiModelProperty(value = "시도이름")
    private String sidoName;
    @ApiModelProperty(value = "구군코드")
    private String gugunCode;
    @ApiModelProperty(value = "구군이름")
    private String gugunName;
    @ApiModelProperty(value = "동코드")
    private String dongCode;
    @ApiModelProperty(value = "동이름")
    private String dongName;

}
