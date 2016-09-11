package cn.zucc.edu.jxm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zucc.edu.jxm.entities.Users;
import cn.zucc.edu.jxm.repository.UsersRepository;

@Service
public class UsersService
{
    @Autowired
    private UsersRepository usersRepository;
    
    @Transactional(readOnly=true)
    public Users existUser(String userName,String password){
        return usersRepository.getByUserNameAndPassword(userName, password);
    }
    
    @Transactional(readOnly=true)
    public Page<Users> getPage(int pageNo, int pageSize){
        PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
        return usersRepository.findByUStatusOrderByUserLevelDesc("正常",pageable);
    }
    
    @Transactional(readOnly=true)
    public Page<Users> getSearchPage(String userName,int pageNo, int pageSize){
        PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
        return usersRepository.findByUserNameLike(userName, pageable);
      
    }
    
    @Transactional(readOnly=true)
    public Users getByUserName(String userName){
        return usersRepository.getByUserName(userName);
    }
    
    @Transactional(readOnly=true)
    public Users getByUserId(int userId){
        return usersRepository.getByUserId(userId);
    }
    
    
    @Transactional
    public void save(Users user){
        user.setuStatus("正常");
        usersRepository.saveAndFlush(user);
    }
    
    @Transactional(readOnly=true)
    public Users getModifyUser(int userId){
        return usersRepository.getByUserId(userId);
    }
    
    @Transactional
    public void deleteUser(int userId){
       /* Users user=usersRepository.getByUserId(userId);
        user.setuStatus("已删除");
        usersRepository.saveAndFlush(user);*/
        
        Users user=usersRepository.getByUserId(userId);
        usersRepository.delete(user);
    }
   
    @Transactional
    public List<Users> findAllUser(){
    	return usersRepository.findAll();
    }
}
