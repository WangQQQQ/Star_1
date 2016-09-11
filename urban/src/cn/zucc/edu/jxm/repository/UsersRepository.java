package cn.zucc.edu.jxm.repository;

import cn.zucc.edu.jxm.entities.Users;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface UsersRepository extends JpaRepository<Users, Integer>,PagingAndSortingRepository<Users, Integer>
{
	Page<Users> findByUserNameLike(String userName, Pageable pageRequest);
	
	/*@QueryHints({@QueryHint(name=org.hibernate.ejb.QueryHints.HINT_CACHEABLE,value="true")})
	@Query("select * FROM Users where userName!='admin'")
	Page<Users> findAllExceptAdmin(Pageable pageRequest);*/
	Page<Users> findByUStatusOrderByUserLevelDesc(String u,Pageable pageRequest);
	
	List<Users> getByUserLevel(String u);
	
    Users getByUserNameAndPassword(String userName, String password);
    
    Users getByUserName(String userName);
    
    Users getByUserId(int userId);

    @QueryHints({@QueryHint(name=org.hibernate.ejb.QueryHints.HINT_CACHEABLE,value="true")})
    @Query("FROM Users u where uStatus!='ÒÑÉ¾³ý'")
    List<Users> getAll();
    
}
