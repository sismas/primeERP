package com.sismas.erp.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author Ismael Juchazara
 * @param <T> Clase a manejar por el Facade
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    /**
     * @param entityClass Clase a persistir
     */
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    protected void create(T entity) {
        getEntityManager().persist(entity);
    }

    /**
     * @param entity Clase a persistir
     */
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    /**
     * @param entity Objeto a eliminar
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     * @param id del Objeto buscado
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * @return Lista de todos los objetos de la clase
     */
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    protected List<T> findByField(String field, Object object) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> root = cq.from(entityClass);
        cq.where(criteriaBuilder.equal(root.get(field), object));
        cq.select(root);
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * @param attributeOrder Nombre del atributo para la ordenación
     * @return Lista de todos los objetos de la clase ordenados por un atributo
     */
    public List<T> findAll(String attributeOrder) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass)).orderBy(getEntityManager().getCriteriaBuilder().asc(cq.from(entityClass).get(attributeOrder)));
        return getEntityManager().createQuery(cq).getResultList();
    }

    protected List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    protected int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    /**
     * @param first Número de pagina a obtener
     * @param pageSize Cantidad de objetos a devolver
     * @param sortField Nombre del campo de objeto para la ordenación
     * @param asc Forma de ordenación
     * @param filters Lista de filtros para la busqueda de objetos
     * @return Página de objetos de la clase ordenados por un atributo
     */
    public List<T> findLazy(int first, int pageSize, String sortField, boolean asc, Map<String, Object> filters) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        List<Predicate> predicates = new ArrayList();
        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            if (entry.getValue().getClass() == String.class) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get(entry.getKey())), "%" + filters.get(entry.getKey()).toString().toUpperCase() + "%"));
            } else {
                predicates.add(criteriaBuilder.equal(root.get(entry.getKey()), filters.get(entry.getKey())));
            }
        }
        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        if (sortField != null) {
            criteriaQuery.select(root);
            if (asc) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortField)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sortField)));
            }
        }
        return getEntityManager().createQuery(criteriaQuery).setFirstResult(first).setMaxResults(pageSize).getResultList();
    }

    /**
     * @param filters Lista de filtros para la busqueda de objetos
     * @return Cantidad de objetos que cumplen con los filtros indicados
     */
    public int count(Map<String, Object> filters) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        List<Predicate> predicates = new ArrayList();
        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            if (entry.getValue().getClass() == String.class) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get(entry.getKey())), "%" + filters.get(entry.getKey()).toString().toUpperCase() + "%"));
            } else {
                predicates.add(criteriaBuilder.equal(root.get(entry.getKey()), filters.get(entry.getKey())));
            }
        }
        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        return getEntityManager().createQuery(criteriaQuery).getResultList().size();
    }

}
