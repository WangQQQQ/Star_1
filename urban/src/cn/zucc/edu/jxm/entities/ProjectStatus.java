package cn.zucc.edu.jxm.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Table(name="ProjectStatus")
@Entity
public class ProjectStatus
{
    //����״̬��
    private Integer sId; //״̬���
    private String sName; //״̬����
    
    @Id
    public Integer getsId()
    {
        return sId;
    }
    public void setsId(Integer sId)
    {
        this.sId = sId;
    }
    public String getsName()
    {
        return sName;
    }
    public void setsName(String sName)
    {
        this.sName = sName;
    }
  
    
    
}
