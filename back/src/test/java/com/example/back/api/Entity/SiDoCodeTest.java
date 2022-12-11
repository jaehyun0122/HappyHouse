package com.example.back.api.Entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

import static com.example.back.api.Entity.QSiDoCode.siDoCode;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SiDoCodeTest {

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory = new JPAQueryFactory(em);

    @Test
    public void select(){

        Query query = em.createQuery("select s from SiDoCode s group by s.sidoName");
        List resultList = new ArrayList();
        resultList = query.getResultList();

        for (Object o : resultList) {
            System.out.println(o);
        }

    }
}
