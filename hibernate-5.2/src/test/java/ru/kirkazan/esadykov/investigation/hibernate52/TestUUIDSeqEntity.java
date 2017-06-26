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
@Table(name = "version_uuid_seq")
public class TestUUIDSeqEntity {

    @Id
    @Column(columnDefinition = "UUID not null")
    private UUID id;

    @Column(columnDefinition = "UUID not null" )
    private UUID entityId;

    @Column
    private String value;

    public TestUUIDSeqEntity() {
    }

    public TestUUIDSeqEntity(UUID id) {
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

    static TimeBasedGenerator timeBasedGenerator = Generators.timeBasedGenerator();
    @PrePersist
    private void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();//Generators.timeBasedGenerator().generate();
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
