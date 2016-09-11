package cn.zucc.edu.jxm.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Table(name="ProjectInfo")
@Entity
public class ProjectInfo
{
    //������Ϣ��
    private String pId; //������Ŀ��� 
    private String projectName; //��Ŀ����
    private Double totalInvestment; //��Ͷ�ʶ�(��Ԫ)
    private Integer projectPeriod; //����
    private Double buildTotalArea; //���������
    @DateTimeFormat(pattern="yyyy-MM-dd") //��ҳ����������ַ���תΪDate����
    private Date constructTime; //����ʱ��
    private Double maxSpan; //�����
    private String pStatus;
    
    private EnterpriseInfo enterpriseInfo; //���赥λ���
//    private GeographyInfo geographyInfo; //������Ϣ���
    private ProjectStatus projectStatus; //��Ŀ״̬
    
   /* @Id
    * @GeneratedValue
    public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}*/
    
    @Id
    public String getpId()
    {
        return pId;
    }
    public void setpId(String pId)
    {
        this.pId = pId;
    }
    public String getProjectName()
    {
        return projectName;
    }
    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }
    public Double getTotalInvestment()
    {
        return totalInvestment;
    }
    public void setTotalInvestment(Double totalInvestment)
    {
        this.totalInvestment = totalInvestment;
    }
    public Integer getProjectPeriod()
    {
        return projectPeriod;
    }
    public void setProjectPeriod(Integer projectPeriod)
    {
        this.projectPeriod = projectPeriod;
    }
    public Double getBuildTotalArea()
    {
        return buildTotalArea;
    }
    public void setBuildTotalArea(Double buildTotalArea)
    {
        this.buildTotalArea = buildTotalArea;
    }
   
    @Temporal(TemporalType.DATE)
    public Date getConstructTime()
    {
        return constructTime;
    }
    public void setConstructTime(Date constructTime)
    {
        this.constructTime = constructTime;
    }
    public Double getMaxSpan()
    {
        return maxSpan;
    }
    public void setMaxSpan(Double maxSpan)
    {
        this.maxSpan = maxSpan;
    }
    public String getpStatus()
    {
        return pStatus;
    }
    public void setpStatus(String pStatus)
    {
        this.pStatus = pStatus;
    }
    
    @JoinColumn(name="e_Id")
    @ManyToOne(fetch=FetchType.LAZY)
    public EnterpriseInfo getEnterpriseInfo()
    {
        return enterpriseInfo;
    }
    public void setEnterpriseInfo(EnterpriseInfo enterpriseInfo)
    {
        this.enterpriseInfo = enterpriseInfo;
    }
    
   /* @JoinColumn(name="g_Id")
    @ManyToOne(fetch=FetchType.LAZY)
    public GeographyInfo getGeographyInfo()
    {
        return geographyInfo;
    }
    public void setGeographyInfo(GeographyInfo geographyInfo)
    {
        this.geographyInfo = geographyInfo;
    }*/
    
    @JoinColumn(name="s_Id")
    @ManyToOne(fetch=FetchType.LAZY)
    public ProjectStatus getProjectStatus()
    {
        return projectStatus;
    }
    public void setProjectStatus(ProjectStatus projectStatus)
    {
        this.projectStatus = projectStatus;
    }
    
    
    
}
