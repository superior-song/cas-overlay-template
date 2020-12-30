package com.suncreate.repository;

import com.suncreate.entity.User;
import lombok.ToString;
import lombok.val;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@EnableTransactionManagement(proxyTargetClass = true)
@Transactional(transactionManager = "transactionManagerMobileId")
@ToString
public class JpaMobileUserRepository implements UserRepository {

    @PersistenceContext(unitName = "mobileUserEntityManagerFactory")
    private transient EntityManager entityManager;
    private final String SELECT_QUERY = "select r from Puser r ";

    @Override
    public User getPrincipal(String phoneNumber) {
        val query = SELECT_QUERY.concat("where r.phoneNumber = :phoneNumber");
        return entityManager.createQuery(query,User.class)
                .setParameter("phoneNumber",phoneNumber).getSingleResult();
    }
}