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
    private Integer eId;   //��ҵid
    private String eName; //��ҵ����
    @DateTimeFormat(pattern="yyyy-MM-dd") //��ҳ����������ַ���תΪDate����
    private Date createTime; //��ҵ����ʱ��
    private Double registerFund; //ע���ʽ�
    private String eAddress;  //��˾��ַ
    private Integer bidNum; //�������ҵ�ۼƹ����б��������
    private String eStatus; //״̬
    
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
