package umc.spring.domain;

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

    private static final long serialVersionUID = -1348045193L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<Alarm, QAlarm> alarmList = this.<Alarm, QAlarm>createList("alarmList", Alarm.class, QAlarm.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final EnumPath<User.Gender> gender = createEnum("gender", User.Gender.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final ListPath<Inquiry, QInquiry> inquiryList = this.<Inquiry, QInquiry>createList("inquiryList", Inquiry.class, QInquiry.class, PathInits.DIRECT2);

    public final EnumPath<TandF> inquriyRelyAlarm = createEnum("inquriyRelyAlarm", TandF.class);

    public final EnumPath<TandF> LocationConsent = createEnum("LocationConsent", TandF.class);

    public final EnumPath<TandF> marketingConsent = createEnum("marketingConsent", TandF.class);

    public final ListPath<MissionRecord, QMissionRecord> missionRecordList = this.<MissionRecord, QMissionRecord>createList("missionRecordList", MissionRecord.class, QMissionRecord.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final EnumPath<TandF> newEventAlarm = createEnum("newEventAlarm", TandF.class);

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final ListPath<PointRecord, QPointRecord> pointRecordList = this.<PointRecord, QPointRecord>createList("pointRecordList", PointRecord.class, QPointRecord.class, PathInits.DIRECT2);

    public final ListPath<Preference, QPreference> preferenceList = this.<Preference, QPreference>createList("preferenceList", Preference.class, QPreference.class, PathInits.DIRECT2);

    public final QRegion region;

    public final StringPath regionDetail = createString("regionDetail");

    public final ListPath<Review, QReview> reviewList = this.<Review, QReview>createList("reviewList", Review.class, QReview.class, PathInits.DIRECT2);

    public final EnumPath<TandF> reviewRelyAlarm = createEnum("reviewRelyAlarm", TandF.class);

    public final EnumPath<User.Active> status = createEnum("status", User.Active.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

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
        this.region = inits.isInitialized("region") ? new QRegion(forProperty("region")) : null;
    }

}

