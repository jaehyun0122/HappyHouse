package com.ssafy.happyhouse.model.dto;

public class ChartSidoGugunDongCode {

	private String sidoCode;
	private String sidoName;
	private int sidoHit;

	private String gugunCode;
	private String gugunName;
	private int gugunHit;
	
	private String dongCode;
	private String dongName;
	private int dongHit;
	public String getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public int getSidoHit() {
		return sidoHit;
	}
	public void setSidoHit(int sidoHit) {
		this.sidoHit = sidoHit;
	}
	public String getGugunCode() {
		return gugunCode;
	}
	public void setGugunCode(String gugunCode) {
		this.gugunCode = gugunCode;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public int getGugunHit() {
		return gugunHit;
	}
	public void setGugunHit(int gugunHit) {
		this.gugunHit = gugunHit;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public int getDongHit() {
		return dongHit;
	}
	public void setDongHit(int dongHit) {
		this.dongHit = dongHit;
	}
	@Override
	public String toString() {
		return "ChartSidoGugunDongCode [sidoCode=" + sidoCode + ", sidoName=" + sidoName + ", sidoHit=" + sidoHit
				+ ", gugunCode=" + gugunCode + ", gugunName=" + gugunName + ", gugunHit=" + gugunHit + ", dongCode="
				+ dongCode + ", dongName=" + dongName + ", dongHit=" + dongHit + "]";
	}
	
	
	
}
