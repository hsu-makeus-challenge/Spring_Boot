//package umc.spring.repository.StoreRepository;
//
//import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import umc.spring.domain.Store;
//
//import java.util.List;
//
//@Repository
//public interface StoreRepositoryImpl extends JpaRepository<Store, Long>{
//
//}
//
//@Repository
//@RequiredArgsConstructor
//public class StoreRepositoryImpl implements StoreRepositoryCustom {
//    private final JPAQueryFactory jpaQueryFactory;
//    private final QStore store = QStore.store;
//
//    @Override
//    public List<Store> dynamicQueryWithBooleanBuilder(String name, Float score) {
//        BooleanBuilder predicate = new BooleanBuilder();
//
//        if (name != null) {
//            predicate.and(store.storeName.eq(name));
//        }
//
//        if (score != null) {
//            predicate.and(store.score.goe(4.0f));
//        }
//
//        return jpaQueryFactory
//                .selectFrom(store)
//                .where(predicate)
//                .fetch();
//    }
//}