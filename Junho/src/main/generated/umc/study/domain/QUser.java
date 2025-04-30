package umc.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 479029197L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final umc.study.domain.common.QBaseEntity _super = new umc.study.domain.common.QBaseEntity(this);

    public final EnumPath<umc.study.domain.enums.MemberStatus> active = createEnum("active", umc.study.domain.enums.MemberStatus.class);

    public final StringPath address = createString("address");

    public final DatePath<java.time.LocalDate> birthDate = createDate("birthDate", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final EnumPath<umc.study.domain.enums.Gender> gender = createEnum("gender", umc.study.domain.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Inquiry, QInquiry> inquiryList = this.<Inquiry, QInquiry>createList("inquiryList", Inquiry.class, QInquiry.class, PathInits.DIRECT2);

    public final BooleanPath isLocationInfoAgreed = createBoolean("isLocationInfoAgreed");

    public final BooleanPath isMarketingAgreed = createBoolean("isMarketingAgreed");

    public final BooleanPath isPrivacyPolicyAgreed = createBoolean("isPrivacyPolicyAgreed");

    public final BooleanPath isTermsAgreed = createBoolean("isTermsAgreed");

    public final StringPath name = createString("name");

    public final StringPath personalCategory = createString("personalCategory");

    public final QPhoneVerification phoneVerification;

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final ListPath<umc.study.domain.mapping.Review, umc.study.domain.mapping.QReview> reviewList = this.<umc.study.domain.mapping.Review, umc.study.domain.mapping.QReview>createList("reviewList", umc.study.domain.mapping.Review.class, umc.study.domain.mapping.QReview.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<umc.study.domain.mapping.UserMission, umc.study.domain.mapping.QUserMission> userMissionList = this.<umc.study.domain.mapping.UserMission, umc.study.domain.mapping.QUserMission>createList("userMissionList", umc.study.domain.mapping.UserMission.class, umc.study.domain.mapping.QUserMission.class, PathInits.DIRECT2);

    public final QUserNotificationAgreement userNotificationAgreement;

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.phoneVerification = inits.isInitialized("phoneVerification") ? new QPhoneVerification(forProperty("phoneVerification"), inits.get("phoneVerification")) : null;
        this.userNotificationAgreement = inits.isInitialized("userNotificationAgreement") ? new QUserNotificationAgreement(forProperty("userNotificationAgreement"), inits.get("userNotificationAgreement")) : null;
    }

}

