package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewRely is a Querydsl query type for ReviewRely
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewRely extends EntityPathBase<ReviewRely> {

    private static final long serialVersionUID = 1439314980L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewRely reviewRely = new QReviewRely("reviewRely");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QReview review;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QReviewRely(String variable) {
        this(ReviewRely.class, forVariable(variable), INITS);
    }

    public QReviewRely(Path<? extends ReviewRely> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewRely(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewRely(PathMetadata metadata, PathInits inits) {
        this(ReviewRely.class, metadata, inits);
    }

    public QReviewRely(Class<? extends ReviewRely> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
    }

}

