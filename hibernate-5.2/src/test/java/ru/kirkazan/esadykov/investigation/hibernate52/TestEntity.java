package ru.kirkazan.esadykov.investigation.hibernate52;

import javax.persistence.*;

/**
 * @author ser
 * @since 19.02.14 22:44
 */
@Entity
@Table(name = "version_long")
public class TestEntity {

    @Id
    @Column(columnDefinition = "BIGINT not null")
    private Long id;

    @Column(columnDefinition = "BIGINT not null")
    private Long entityId;

    @Column
    private String value;
    public TestEntity() {
    }

    public static long seq = 1;
    @PrePersist
    private void prePersist() {
        if (id == null) {
            id = seq++;
        }
        if (entityId == null)
            entityId = id;
    }

    public TestEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
