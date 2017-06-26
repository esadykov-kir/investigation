package ru.kirkazan.esadykov.investigation.hibernate52;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author esadykov
 * @since 31.05.2017
 */
@Entity
@Table(name = "master")
public class MasterEntity {

    @Id
    @Column(columnDefinition = "UUID not null")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ref_id", referencedColumnName = "id")
    private RefEntity ref;

    @PrePersist
    private void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    public MasterEntity() {
    }

    public MasterEntity(RefEntity ref) {
        this.ref = ref;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RefEntity getRef() {
        return ref;
    }

    public void setRef(RefEntity ref) {
        this.ref = ref;
    }
}
