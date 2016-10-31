package com.dytt;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public abstract class DBHelper {
    private static SessionFactory factory;
    static {
        Configuration cg = new Configuration().configure();
        StandardServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cg.getProperties()).build();
        factory = cg.buildSessionFactory(sr);
    }

    public static Session getSession() {
        return factory.openSession();
    }

    /**
     * 打印日志
     * 
     * @param msg
     */
    protected void info(String msg) {
        Logger.info(this, msg);
    }
}
