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
@Table(name = "version_varchar_rnd")
public class TestVarCharRandomEntity {

    @Id
    @Column(columnDefinition = "varchar(36) collate \"C\" not null")
    private String id;

    @Column(columnDefinition = "varchar(36) collate \"C\" not null" )
    private String entityId;

    @Column
    private String value;

    public TestVarCharRandomEntity() {
    }

    public TestVarCharRandomEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    static TimeBasedGenerator timeBasedGenerator = Generators.timeBasedGenerator();
    @PrePersist
    private void prePersist() {
        if (id == null) {
            id = UUID.randomUUID().toString();//Generators.timeBasedGenerator().generate();
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
