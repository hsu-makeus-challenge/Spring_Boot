package umc.spring.domain.member.repository.memberStore;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.domain.member.data.mapping.MemberStore;

import java.util.Optional;

public interface MemberStoreRepository extends JpaRepository<MemberStore, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ms FROM MemberStore ms WHERE ms.member.id = :memberId AND ms.store.id = :storeId")
    Optional<MemberStore> findByMemberIdAndStoreId(@Param("memberId")Long memberId, @Param("storeId") Long storeId);

}
