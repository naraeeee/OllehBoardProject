package com.example.ollethboardproject.domain.entity.audit;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity {
    @CreatedDate
    @Column( updatable = false)
    protected String createdAt;

    @CreatedBy
    @Column( updatable = false)
    protected String createdBy;

    @LastModifiedDate
    protected String updatedAt;

    @LastModifiedBy
    protected String updatedBy;

    @Column(name = "deleted_at")
    protected LocalDateTime deletedAt;

}
