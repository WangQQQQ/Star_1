package cn.zucc.edu.jxm.entities;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Cacheable
@Table(name="Users")
@Entity
public class Users
{
    private Integer userId;
   // @NotEmpty
    private String userName;
   // @NotEmpty
    private String password;
    private String userLevel;  //用户等级:1-管理员 2-普通用户 3-游客
    private String uStatus;


	@Id
    @GeneratedValue
    public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getuStatus()
    {
        return uStatus;
    }

    public void setuStatus(String uStatus)
    {
        this.uStatus = uStatus;
    }

    public String getUserLevel()
    {
        return userLevel;
    }

    public void setUserLevel(String userLevel)
    {
        this.userLevel = userLevel;
    }

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", userLevel=" + userLevel
				+ ", uStatus=" + uStatus + "]";
	}
    


}
