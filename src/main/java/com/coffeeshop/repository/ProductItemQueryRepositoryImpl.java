package com.coffeeshop.repository;

import com.coffeeshop.model.entity.Product;
import com.coffeeshop.model.entity.ProductItem;
import com.coffeeshop.model.entity.type.ProductStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ProductItemQueryRepositoryImpl implements ProductItemQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductItem> findProductItemByProductAndProductStatusLimitIs(Product product, ProductStatus status,
                                                                             int limit) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductItem> query = criteriaBuilder.createQuery(ProductItem.class);
        Root<ProductItem> root = query.from(ProductItem.class);
        Predicate productPredicate = criteriaBuilder.equal(root.get("product"), product);
        Predicate productStatusPredicate = criteriaBuilder.equal(root.get("productStatus"),status);
        query.where(productPredicate, productStatusPredicate);

        TypedQuery<ProductItem> typedQuery = entityManager.createQuery(query);
        List<ProductItem> resultList = typedQuery.setMaxResults(limit).getResultList();
        entityManager.close();
        return resultList;
    }
}
