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
    //������Ϣ��
    
  
	private Integer gId; //������Ϣ���
    private String geographyAddress; //������Ϣ��ַ
    private String lng;  //������Ϣ���꾭��Longitude 
    private String lat;  //������Ϣ����γ��Latitude 
    private String geographyRegion; //��������
    @DateTimeFormat(pattern="yyyy-MM-dd") //��ҳ����������ַ���תΪDate����
    private Date constructTime; //����ʱ��
    private String gStatus; //״̬
    
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
