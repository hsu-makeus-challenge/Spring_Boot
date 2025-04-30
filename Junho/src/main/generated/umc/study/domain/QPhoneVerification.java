package umc.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPhoneVerification is a Querydsl query type for PhoneVerification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPhoneVerification extends EntityPathBase<PhoneVerification> {

    private static final long serialVersionUID = 215239143L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPhoneVerification phoneVerification = new QPhoneVerification("phoneVerification");

    public final umc.study.domain.common.QBaseEntity _super = new umc.study.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final BooleanPath isVerified = createBoolean("isVerified");

    public final StringPath phoneNum = createString("phoneNum");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public QPhoneVerification(String variable) {
        this(PhoneVerification.class, forVariable(variable), INITS);
    }

    public QPhoneVerification(Path<? extends PhoneVerification> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPhoneVerification(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPhoneVerification(PathMetadata metadata, PathInits inits) {
        this(PhoneVerification.class, metadata, inits);
    }

    public QPhoneVerification(Class<? extends PhoneVerification> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

