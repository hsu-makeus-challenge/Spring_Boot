package umc.spring.domain.store.repository;

import umc.spring.domain.store.data.Store;

import java.util.List;

public interface StoreRepositoryCustom {
    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
}
