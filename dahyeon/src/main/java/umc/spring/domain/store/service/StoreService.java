package umc.spring.domain.store.service;

import java.util.List;

import umc.spring.domain.store.entity.Store;

public interface StoreService {

  List<Store> findStoresByNameAndScore(String name, Float score);

  boolean storeExist(Long value);
}
