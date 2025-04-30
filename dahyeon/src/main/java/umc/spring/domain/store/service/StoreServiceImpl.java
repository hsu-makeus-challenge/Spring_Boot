package umc.spring.domain.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import umc.spring.domain.store.entity.Store;
import umc.spring.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class StoreServiceImpl implements StoreService {
  private final StoreRepository storeRepository;

  @Override
  public Optional<Store> findStore(Long id) {
    return storeRepository.findById(id);
  }

  @Override
  public List<Store> findStoresByNameAndScore(String name, Float score) {
    List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);
    filteredStores.forEach(store -> log.info("Store: " + store));
    return filteredStores;
  }
}
