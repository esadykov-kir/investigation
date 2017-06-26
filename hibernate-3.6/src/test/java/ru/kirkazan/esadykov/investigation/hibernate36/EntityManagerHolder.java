package ru.kirkazan.esadykov.investigation.hibernate36;

import javax.persistence.EntityManager;

/**
 * @author esadykov
 * @since 26.01.2017 10:06
 */
public class EntityManagerHolder {
    public static EntityManager manager;

    public static EntityManager getManager() {
        return manager;
    }

    public static void setManager(EntityManager manager) {
        EntityManagerHolder.manager = manager;
    }
}
