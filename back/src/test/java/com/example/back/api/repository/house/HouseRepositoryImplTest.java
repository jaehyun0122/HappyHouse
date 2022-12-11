package com.example.back.api.repository.house;

import com.example.back.api.Entity.QSiDoCode;
import com.example.back.api.Entity.SiDoCode;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

import static com.example.back.api.Entity.QSiDoCode.siDoCode;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HouseRepositoryImplTest {

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before(){
        queryFactory  = new JPAQueryFactory(em);
    }

    @Test
    public void getSidoList(){
        List<SiDoCode> fetch = new ArrayList<>();
                fetch = queryFactory.selectFrom(siDoCode)
                .groupBy(siDoCode.sidoName)
                .fetch();

        for (SiDoCode doCode : fetch) {
            System.out.println(doCode.getSidoName());
        }
    }

    @Test
    public void getGugunList(){
        String sidoName = "전라북도";
        List<String> result = queryFactory
                .select(siDoCode.gugunName)
                .from(siDoCode)
                .where(siDoCode.sidoName.eq(sidoName))
                .fetch();

        for (String s : result) {
            System.out.println(s);
        }
    }

    @Test
    public void getDongList(){
        String gugunName = "유성구";

        List<String> result = queryFactory
                .select(siDoCode.dongName)
                .from(siDoCode)
                .where(siDoCode.gugunName.eq(gugunName))
                .fetch();

        for (String s : result) {
            System.out.println(s);
        }
    }

    @Test
    public void getHouse(){
        String sidoName = "전라북도";
        String gugunName = "전주시덕진구";

        Integer result = queryFactory
                .select(siDoCode.sidoCode)
                .from(siDoCode)
                .where(siDoCode.sidoName.eq(sidoName).and(siDoCode.gugunName.eq(gugunName)))
                .groupBy(siDoCode.gugunName)
                .fetchOne();
        String preFive = String.valueOf(result).substring(0,5);
        result = Integer.parseInt(preFive);

        Assertions.assertThat(result).isEqualTo(45113);
    }
}
