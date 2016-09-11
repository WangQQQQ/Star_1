package cn.zucc.edu.jxm.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Table(name="GeographyInfo")
@Entity
public class GeographyInfo
{
    //地理信息表
    
  
	private Integer gId; //地理信息编号
    private String geographyAddress; //地理信息地址
    private String lng;  //地理信息坐标经度Longitude 
    private String lat;  //地理信息坐标纬度Latitude 
    private String geographyRegion; //地理区域
    @DateTimeFormat(pattern="yyyy-MM-dd") //将页面上输入的字符串转为Date类型
    private Date constructTime; //报建时间
    private String gStatus; //状态
    
    private ProjectInfo projectInfo;

    @Id
    @GeneratedValue
    public Integer getgId()
    {
        return gId;
    }

    public void setgId(Integer gId)
    {
        this.gId = gId;
    }

    public String getGeographyAddress()
    {
        return geographyAddress;
    }

    public void setGeographyAddress(String geographyAddress)
    {
        this.geographyAddress = geographyAddress;
    }

    public String getLng()
    {
        return lng;
    }

    public void setLng(String lng)
    {
        this.lng = lng;
    }

    public String getLat()
    {
        return lat;
    }

    public void setLat(String lat)
    {
        this.lat = lat;
    }

    public String getGeographyRegion()
    {
        return geographyRegion;
    }

    public void setGeographyRegion(String geographyRegion)
    {
        this.geographyRegion = geographyRegion;
    }

    public String getgStatus()
    {
        return gStatus;
    }

    public void setgStatus(String gStatus)
    {
        this.gStatus = gStatus;
    }

    @JoinColumn(name="p_Id")
    @ManyToOne(fetch=FetchType.LAZY)
    public ProjectInfo getProjectInfo()
    {
        return projectInfo;
    }

    public void setProjectInfo(ProjectInfo projectInfo)
    {
        this.projectInfo = projectInfo;
    }
   
    public Date getConstructTime() {
		return constructTime;
	}

	public void setConstructTime(Date constructTime) {
		this.constructTime = constructTime;
	}

	@Override
	public String toString() {
		return "GeographyInfo [gId=" + gId + ", geographyAddress="
				+ geographyAddress + ", lng=" + lng + ", lat=" + lat
				+ ", geographyRegion=" + geographyRegion + ", constructTime="
				+ constructTime + ", gStatus=" + gStatus + "]";
	}
}
