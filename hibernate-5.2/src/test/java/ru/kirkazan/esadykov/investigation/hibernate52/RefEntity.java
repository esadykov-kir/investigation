package ru.kirkazan.esadykov.investigation.hibernate52;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author esadykov
 * @since 31.05.2017
 */
@Entity
@Table(name = "ref")
public class RefEntity {
    @Id
    @Column(columnDefinition = "UUID not null")
    private UUID id;

    @Column
    private String value;

    @PrePersist
    private void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    public RefEntity() {
    }

    public RefEntity(String value) {
        this.value = value;
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
}
