package com.example.back.api.repository.house;

import com.example.back.api.Entity.SiDoCode;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;
import java.util.Map;

import static com.example.back.api.Entity.QSiDoCode.siDoCode;

public class HouseRepositoryImpl implements HouseRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public HouseRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<String> getSidoList() {

        List<String> fetch = queryFactory
                .select(siDoCode.sidoName)
                .from(siDoCode)
                .groupBy(siDoCode.sidoName)
                .fetch();

        return fetch;
    }

    @Override
    public List<String> getGugunList(String sidoName) {
        List<String> fetch = queryFactory
                .select(siDoCode.gugunName)
                .from(siDoCode)
                .where(siDoCode.sidoName.eq(sidoName))
                .fetch();
        return fetch;
    }

    @Override
    public List<String> getDongList(String gugunName) {
        List<String> result = queryFactory
                .select(siDoCode.dongName)
                .from(siDoCode)
                .where(siDoCode.gugunName.eq(gugunName))
                .fetch();

        return result;
    }

    @Override
    public Integer getCode(String sidoName, String gugunName) {
        Integer result = queryFactory
                .select(siDoCode.sidoCode)
                .from(siDoCode)
                .where(siDoCode.sidoName.eq(sidoName).and(siDoCode.gugunName.eq(gugunName)))
                .groupBy(siDoCode.gugunName)
                .fetchOne();

        String preFive = String.valueOf(result).trim().substring(0,5);
        result = Integer.parseInt(preFive);

        return result;
    }
}
