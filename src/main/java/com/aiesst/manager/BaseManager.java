package com.aiesst.manager;

import org.jinq.jpa.JinqJPAStreamProvider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ychost on 17-6-28.
 * 基础管理层，主要为jinq提供支持
 */
public class BaseManager {
    @PersistenceContext
    protected EntityManager em;

    @Autowired
    protected JinqJPAStreamProvider jinqStreams;

    @Transactional
    public <T> T persist(T entity) {
        this.em.persist(entity);
        return entity;
    }

    @Transactional
    public <T> T remove(T entity) {
        this.em.remove(entity);
        return entity;
    }

    @Transactional
    public <T> T merge(T entity) {
        this.em.merge(entity);
        return entity;
    }

    @Transactional
    public <T> List<T> persistList(List<T> entities) {
        entities.forEach(this.em::merge);
        return entities;
    }

    @Transactional
    public <T> List<T> removeList(List<T> entities) {
        entities.forEach(this.em::remove);
        return entities;
    }

}
