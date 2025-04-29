package umc.spring.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.spring.domain.store.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {}
