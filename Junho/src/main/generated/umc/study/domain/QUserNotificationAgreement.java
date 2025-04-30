package umc.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserNotificationAgreement is a Querydsl query type for UserNotificationAgreement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserNotificationAgreement extends EntityPathBase<UserNotificationAgreement> {

    private static final long serialVersionUID = 1015380882L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserNotificationAgreement userNotificationAgreement = new QUserNotificationAgreement("userNotificationAgreement");

    public final umc.study.domain.common.QBaseEntity _super = new umc.study.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final BooleanPath event = createBoolean("event");

    public final BooleanPath inquiry = createBoolean("inquiry");

    public final BooleanPath review = createBoolean("review");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public QUserNotificationAgreement(String variable) {
        this(UserNotificationAgreement.class, forVariable(variable), INITS);
    }

    public QUserNotificationAgreement(Path<? extends UserNotificationAgreement> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserNotificationAgreement(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserNotificationAgreement(PathMetadata metadata, PathInits inits) {
        this(UserNotificationAgreement.class, metadata, inits);
    }

    public QUserNotificationAgreement(Class<? extends UserNotificationAgreement> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

