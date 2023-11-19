package dev.chanwook.springstudy.infra.post.repository.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPosts is a Querydsl query type for Posts
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPosts extends EntityPathBase<Posts> {

    private static final long serialVersionUID = -1487373187L;

    public static final QPosts posts = new QPosts("posts");

    public final dev.chanwook.springstudy.infra.QBaseEntity _super = new dev.chanwook.springstudy.infra.QBaseEntity(this);

    public final StringPath author = createString("author");

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPosts(String variable) {
        super(Posts.class, forVariable(variable));
    }

    public QPosts(Path<? extends Posts> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPosts(PathMetadata metadata) {
        super(Posts.class, metadata);
    }

}

