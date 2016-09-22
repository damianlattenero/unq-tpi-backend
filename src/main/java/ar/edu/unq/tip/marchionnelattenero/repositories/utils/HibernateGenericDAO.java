package ar.edu.unq.tip.marchionnelattenero.repositories.utils;

import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Generic hibernate DAO
 * See https://github.com/cristianelopez/unq-devapp/blob/master/src/spring-hibernate/src/main/java/ar/edu/unq/repositories/HibernateGenericDAO.java
 *
 * @param <T>
 */
public abstract class HibernateGenericDAO<T> extends HibernateDaoSupport implements GenericRepository<T>, Serializable {

    private static final long serialVersionUID = 5058950102420892922L;

    protected Class<T> persistentClass = this.getDomainClass();

    @Autowired
    public void provideSessionFactory(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    protected HibernateGenericDAO() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public int count() {
        return ((Long) this.getSession().createCriteria(this.persistentClass.getName()).setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public void delete(final T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    @Override
    public void deleteById(final Serializable id) {
        T obj = this.findById(id);
        this.getHibernateTemplate().delete(obj);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return (List<T>) this.getHibernateTemplate().find("from " + this.persistentClass.getName() + " o");
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByExample(final T exampleObject) {
        return this.getHibernateTemplate().findByExample(exampleObject);

    }

    @Override
    public T findById(final Serializable id) {
        return this.getHibernateTemplate().get(this.persistentClass, id);
    }

    protected abstract Class<T> getDomainClass();

    @Override
    public void save(final T entity) {
        this.getHibernateTemplate().save(entity);
        this.getHibernateTemplate().flush();
    }

    @Override
    public void update(final T entity) {
        this.getHibernateTemplate().update(entity);
    }

}

