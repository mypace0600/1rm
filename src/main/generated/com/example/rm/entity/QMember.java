package com.example.rm.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -443034493L;

    public static final QMember member = new QMember("member1");

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath email = createString("email");

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.sql.Timestamp> lastLoginDate = createDateTime("lastLoginDate", java.sql.Timestamp.class);

    public final StringPath loginId = createString("loginId");

    public final StringPath oauth = createString("oauth");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final ListPath<Record, QRecord> records = this.<Record, QRecord>createList("records", Record.class, QRecord.class, PathInits.DIRECT2);

    public final EnumPath<com.example.rm.enums.RoleType> role = createEnum("role", com.example.rm.enums.RoleType.class);

    public final StringPath userName = createString("userName");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}
