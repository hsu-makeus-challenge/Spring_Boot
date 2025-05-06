package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPointRecord is a Querydsl query type for PointRecord
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointRecord extends EntityPathBase<PointRecord> {

    private static final long serialVersionUID = 534945237L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPointRecord pointRecord = new QPointRecord("pointRecord");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QMissionRecord missionRecord;

    public final StringPath reason = createString("reason");

    public final EnumPath<PointRecord.RecordType> recordType = createEnum("recordType", PointRecord.RecordType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public QPointRecord(String variable) {
        this(PointRecord.class, forVariable(variable), INITS);
    }

    public QPointRecord(Path<? extends PointRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPointRecord(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPointRecord(PathMetadata metadata, PathInits inits) {
        this(PointRecord.class, metadata, inits);
    }

    public QPointRecord(Class<? extends PointRecord> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.missionRecord = inits.isInitialized("missionRecord") ? new QMissionRecord(forProperty("missionRecord"), inits.get("missionRecord")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

