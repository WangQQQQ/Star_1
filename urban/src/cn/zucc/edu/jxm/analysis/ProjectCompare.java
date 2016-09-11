package cn.zucc.edu.jxm.analysis;

import java.math.BigDecimal;
import java.util.Date;

public class ProjectCompare {
	private int gid; // ������Ϣ���
	private String pid; // ������Ŀ���
	private String projectName; // ��Ŀ����
	private double totalInvestment; // Ͷ���ܶ�(��Ԫ)
	private int projectPeriod; // ����
	private double buildTotalArea; // ���������
	private Date constructTime; // ����ʱ��
	private double maxSpan; // �����
	private String geographyAddress; // ���̵�ַ
	private String geographyRegion; // ��������
	private String lng; // ������Ϣ���꾭��Longitude
	private String lat; // ������Ϣ����γ��Latitude
	private String eName; //��ҵ����
	private BigDecimal value;
	
	private double percent1;
	private double percent2;
	private double percent3;
	private double percent4;
	private double percent5;
	private double percent6;
	private double percent7;
	private double percent8;
	
	public double getPercent1() {
		return percent1;
	}

	public void setPercent1(double percent1) {
		this.percent1 = percent1;
	}

	public double getPercent2() {
		return percent2;
	}

	public void setPercent2(double percent2) {
		this.percent2 = percent2;
	}

	public double getPercent3() {
		return percent3;
	}

	public void setPercent3(double percent3) {
		this.percent3 = percent3;
	}

	public double getPercent4() {
		return percent4;
	}

	public void setPercent4(double percent4) {
		this.percent4 = percent4;
	}

	public double getPercent5() {
		return percent5;
	}

	public void setPercent5(double percent5) {
		this.percent5 = percent5;
	}

	public double getPercent6() {
		return percent6;
	}

	public void setPercent6(double percent6) {
		this.percent6 = percent6;
	}

	public double getPercent7() {
		return percent7;
	}

	public void setPercent7(double percent7) {
		this.percent7 = percent7;
	}

	public double getPercent8() {
		return percent8;
	}

	public void setPercent8(double percent8) {
		this.percent8 = percent8;
	}

	// private String method = "addprojectcompareresult"; // ���ڱ�ʾ��һ���Ĵ�����
	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public double getTotalInvestment() {
		return totalInvestment;
	}

	public void setTotalInvestment(double totalInvestment) {
		this.totalInvestment = totalInvestment;
	}

	public int getProjectPeriod() {
		return projectPeriod;
	}

	public void setProjectPeriod(int projectPeriod) {
		this.projectPeriod = projectPeriod;
	}

	public double getBuildTotalArea() {
		return buildTotalArea;
	}

	public void setBuildTotalArea(double buildTotalArea) {
		this.buildTotalArea = buildTotalArea;
	}

	public Date getConstructTime() {
		return constructTime;
	}

	public void setConstructTime(Date constructTime) {
		this.constructTime = constructTime;
	}

	public double getMaxSpan() {
		return maxSpan;
	}

	public void setMaxSpan(double maxSpan) {
		this.maxSpan = maxSpan;
	}

	public String getGeographyAddress() {
		return geographyAddress;
	}

	public void setGeographyAddress(String geographyAddress) {
		this.geographyAddress = geographyAddress;
	}

	public String getGeographyRegion() {
		return geographyRegion;
	}

	public void setGeographyRegion(String geographyRegion) {
		this.geographyRegion = geographyRegion;
	}

	public ProjectCompare() {

	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
