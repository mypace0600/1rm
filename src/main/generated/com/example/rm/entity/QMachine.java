package com.example.rm.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMachine is a Querydsl query type for Machine
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMachine extends EntityPathBase<Machine> {

    private static final long serialVersionUID = -972736642L;

    public static final QMachine machine = new QMachine("machine");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final StringPath machineName = createString("machineName");

    public final StringPath machineType = createString("machineType");

    public final ListPath<Record, QRecord> records = this.<Record, QRecord>createList("records", Record.class, QRecord.class, PathInits.DIRECT2);

    public final StringPath stimulatePoint = createString("stimulatePoint");

    public final StringPath thumbImgUrl = createString("thumbImgUrl");

    public final StringPath videoUrl = createString("videoUrl");

    public QMachine(String variable) {
        super(Machine.class, forVariable(variable));
    }

    public QMachine(Path<? extends Machine> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMachine(PathMetadata metadata) {
        super(Machine.class, metadata);
    }

}

