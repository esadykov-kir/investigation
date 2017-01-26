package ru.kirkazan.esadykov.investigation.hibernate52;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author ser
 * @since 19.02.14 22:44
 */
//@Entity
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@Cacheable
public class Test2Entity {
    @Id
    private Integer id;
    @Column
    private String value;

    public Test2Entity() {
    }

    public Test2Entity(Integer id) {
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
        return "Test2Entity{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
