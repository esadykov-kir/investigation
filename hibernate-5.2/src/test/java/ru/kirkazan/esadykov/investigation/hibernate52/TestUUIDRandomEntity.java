package ru.kirkazan.esadykov.investigation.hibernate52;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author ser
 * @since 19.02.14 22:44
 */
@Entity
@Table(name = "version_uuid_rnd")
public class TestUUIDRandomEntity {

    @Id
    @Column(columnDefinition = "UUID not null")
    private UUID id;

    @Column(columnDefinition = "UUID not null" )
    private UUID entityId;

    @Column
    private String value;

    public TestUUIDRandomEntity() {
    }

    public TestUUIDRandomEntity(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public UUID getEntityId() {
        return entityId;
    }

    public void setEntityId(UUID entityId) {
        this.entityId = entityId;
    }

    @PrePersist
    private void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        if (entityId == null)
            entityId = id;
    }


    @Override
    public String toString() {
        return "Test2Entity{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
