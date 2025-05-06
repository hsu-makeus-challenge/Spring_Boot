package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInquiryRely is a Querydsl query type for InquiryRely
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInquiryRely extends EntityPathBase<InquiryRely> {

    private static final long serialVersionUID = -2073265861L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInquiryRely inquiryRely = new QInquiryRely("inquiryRely");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QInquiry inquiry;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QInquiryRely(String variable) {
        this(InquiryRely.class, forVariable(variable), INITS);
    }

    public QInquiryRely(Path<? extends InquiryRely> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInquiryRely(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInquiryRely(PathMetadata metadata, PathInits inits) {
        this(InquiryRely.class, metadata, inits);
    }

    public QInquiryRely(Class<? extends InquiryRely> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.inquiry = inits.isInitialized("inquiry") ? new QInquiry(forProperty("inquiry"), inits.get("inquiry")) : null;
    }

}

