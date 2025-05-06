package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMissionRecord is a Querydsl query type for MissionRecord
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMissionRecord extends EntityPathBase<MissionRecord> {

    private static final long serialVersionUID = 1920022481L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMissionRecord missionRecord = new QMissionRecord("missionRecord");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> completedAt = createDateTime("completedAt", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QMission mission;

    public final ListPath<PointRecord, QPointRecord> pointRecordList = this.<PointRecord, QPointRecord>createList("pointRecordList", PointRecord.class, QPointRecord.class, PathInits.DIRECT2);

    public final EnumPath<MissionRecord.Status> status = createEnum("status", MissionRecord.Status.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public QMissionRecord(String variable) {
        this(MissionRecord.class, forVariable(variable), INITS);
    }

    public QMissionRecord(Path<? extends MissionRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMissionRecord(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMissionRecord(PathMetadata metadata, PathInits inits) {
        this(MissionRecord.class, metadata, inits);
    }

    public QMissionRecord(Class<? extends MissionRecord> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mission = inits.isInitialized("mission") ? new QMission(forProperty("mission"), inits.get("mission")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

