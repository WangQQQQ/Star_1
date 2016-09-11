package cn.zucc.edu.jxm.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name="EnterpriseInfo")
@Entity
public class EnterpriseInfo
{
    private Integer eId;   //企业id
    private String eName; //企业名称
    @DateTimeFormat(pattern="yyyy-MM-dd") //将页面上输入的字符串转为Date类型
    private Date createTime; //企业成立时间
    private Double registerFund; //注册资金
    private String eAddress;  //公司地址
    private Integer bidNum; //上年度企业累计工程招标代理数量
    private String eStatus; //状态
    
    private Users users;
//    private UserLevel userLevel;
    
    @Id
    @GeneratedValue
    public Integer geteId() {
		return eId;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
	}

	public String geteName()
    {
        return eName;
    }

    public void seteName(String eName)
    {
        this.eName = eName;
    }
    
    @Temporal(TemporalType.DATE)
    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Double getRegisterFund()
    {
        return registerFund;
    }

    public void setRegisterFund(Double registerFund)
    {
        this.registerFund = registerFund;
    }

    public String geteAddress()
    {
        return eAddress;
    }

    public void seteAddress(String eAddress)
    {
        this.eAddress = eAddress;
    }

    public Integer getBidNum()
    {
        return bidNum;
    }

    public void setBidNum(Integer bidNum)
    {
        this.bidNum = bidNum;
    }

    public String geteStatus()
    {
        return eStatus;
    }

    public void seteStatus(String eStatus)
    {
        this.eStatus = eStatus;
    }
    
    @JoinColumn(name="User_ID")
    @ManyToOne(fetch=FetchType.LAZY)
    public Users getUsers()
    {
        return users;
    }

    public void setUsers(Users users)
    {
        this.users = users;
    }
    

}
