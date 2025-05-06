package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBusinessHours is a Querydsl query type for BusinessHours
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBusinessHours extends EntityPathBase<BusinessHours> {

    private static final long serialVersionUID = 436971011L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBusinessHours businessHours = new QBusinessHours("businessHours");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final TimePath<java.time.LocalTime> closeTime = createTime("closeTime", java.time.LocalTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final EnumPath<BusinessHours.day> dayOfWeek = createEnum("dayOfWeek", BusinessHours.day.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<TandF> isOpen = createEnum("isOpen", TandF.class);

    public final TimePath<java.time.LocalTime> openTime = createTime("openTime", java.time.LocalTime.class);

    public final QStore store;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QBusinessHours(String variable) {
        this(BusinessHours.class, forVariable(variable), INITS);
    }

    public QBusinessHours(Path<? extends BusinessHours> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBusinessHours(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBusinessHours(PathMetadata metadata, PathInits inits) {
        this(BusinessHours.class, metadata, inits);
    }

    public QBusinessHours(Class<? extends BusinessHours> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store"), inits.get("store")) : null;
    }

}

