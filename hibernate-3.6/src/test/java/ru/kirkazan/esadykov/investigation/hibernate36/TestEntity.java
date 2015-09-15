package ru.kirkazan.esadykov.investigation.hibernate36;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ser
 * @since 19.02.14 22:44
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@Cacheable
public class TestEntity {
    @Id
    private Integer id;
    @Column
    private String value;

    public TestEntity() {
    }

    public TestEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
