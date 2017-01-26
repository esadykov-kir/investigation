package ru.kirkazan.esadykov.investigation.hibernate52;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ser
 * @since 19.02.14 22:44
 */
@Entity
@Table(name = "version")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@Cacheable
public class TestEntity {
/*
    @Id
    @Column( columnDefinition = "UUID not null")
    private UUID id;

    @Column(columnDefinition = "UUID not null" )
    private UUID entityId;
*/
    @Id
    private Integer id;

    private Integer entityId;

    @Column
    private String value;
    public TestEntity() {
    }

//    static TimeBasedGenerator timeBasedGenerator = Generators.timeBasedGenerator();
    public static int seq = 1;
    private void prePersist() {
        if (id == null) {
            id = seq++;
//            id = Generators.timeBasedGenerator().generate();
//            id = UUID.randomUUID();
        }
        if (entityId == null)
            entityId = id;
    }

/*
    public TestEntity(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

        public UUID getEntityId() {
        return entityId;
    }

    public void setEntityId(UUID entityId) {
        this.entityId = entityId;
    }

*/

    public TestEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
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
