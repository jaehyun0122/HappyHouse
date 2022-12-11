package com.example.back.api.service.house;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HouseServiceImplTest {

    @PersistenceContext
    EntityManager em;

    @Test
    public void getSido(){
        Query query = em.createQuery("select s.sidoCode from sidocode s");
        List resultList = query.getResultList();

        for (Object o : resultList) {
            System.out.println(o.toString());
        }
    }

}
