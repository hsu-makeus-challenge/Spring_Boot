package umc.spring.domain.store.repository;

import java.util.List;

import umc.spring.domain.store.entity.Store;

public interface StoreRepositoryCustom {
  List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
}
