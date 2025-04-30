package umc.spring.domain.store.service;

import java.util.List;
import java.util.Optional;

import umc.spring.domain.store.entity.Store;

public interface StoreService {
  Optional<Store> findStore(Long id);

  List<Store> findStoresByNameAndScore(String name, Float score);
}
