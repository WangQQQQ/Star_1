package cn.zucc.edu.jxm.test;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.ejb.QueryHints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JPATest {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;
	
	@Before
	public void init(){
		entityManagerFactory = Persistence.createEntityManagerFactory("jpa-1");
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
		transaction.begin();
	}
	
	@After
	public void destroy(){
		transaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}
	/**
     * JPQL 的关联查询同 HQL 的关联查询. 
     */
    @Test
    public void testLeftOuterJoinFetch(){
        String jpql = "From ProjectInfo p INNER JOIN GeographyInfo g on p.pId = g.pId INNER JOIN EnterpriseInfo e on p.eId = e.eId WHERE p.pId=?";
        //FROM Customer c LEFT OUTER JOIN FETCH c.orders WHERE c.id = ?
//   Customer customer =  (Customer) entityManager.createQuery(jpql).setParameter(1, 12).getSingleResult();
//        System.out.println(customer.getLastName());
//        System.out.println(customer.getOrders().size());
        
      List<Object[]> result = entityManager.createQuery(jpql).setParameter(1, "312000006").getResultList();
      System.out.println(result);
    }

}
