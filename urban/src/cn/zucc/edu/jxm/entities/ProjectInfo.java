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
    //建设信息表
    private String pId; //工程项目编号 
    private String projectName; //项目名称
    private Double totalInvestment; //总投资额(万元)
    private Integer projectPeriod; //工期
    private Double buildTotalArea; //工程总面积
    @DateTimeFormat(pattern="yyyy-MM-dd") //将页面上输入的字符串转为Date类型
    private Date constructTime; //报建时间
    private Double maxSpan; //最大跨度
    private String pStatus;
    
    private EnterpriseInfo enterpriseInfo; //建设单位编号
//    private GeographyInfo geographyInfo; //地理信息编号
    private ProjectStatus projectStatus; //项目状态
    
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
