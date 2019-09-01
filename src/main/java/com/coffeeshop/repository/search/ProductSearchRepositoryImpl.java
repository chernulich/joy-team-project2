package com.coffeeshop.repository.search;

import com.coffeeshop.model.web.product.ProductListResponse;
import com.coffeeshop.model.web.product.ProductRequest;
import com.coffeeshop.model.web.product.ProductResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductSearchRepositoryImpl implements ProductSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ProductListResponse getProductsViaSearchProductRequest(ProductRequest request) {
        Integer page = request.getPage();
        Integer results = request.getResults();
        String search = request.getSearch();
        Integer sourFrom = request.getCharacteristics().getSourFrom();
        Integer sourTo = request.getCharacteristics().getSourTo();
        Double priceMin = request.getPriceMin();
        Double priceMax = request.getPriceMax();
        Integer strongFrom = request.getCharacteristics().getStrongFrom();
        Integer strongTo = request.getCharacteristics().getStrongTo();
        Integer bitterFrom = request.getCharacteristics().getBitterFrom();
        Integer bitterTo = request.getCharacteristics().getBitterTo();
        String sortBy = request.getSortBy();

//        StringBuilder query = new StringBuilder();
//        query.append("select new com.coffeeshop.model.web.product.ProductResponse");
//        query.append("pc.product.id, p.productName, p.shortDescription, p.unitPrice, p.previewImage");

        String query = "select new com.coffeeshop.model.web.product.ProductResponse" +
                "(pc.product.id, p.productName, p.shortDescription, p.productCategory, p.unitPrice, p.previewImage, pq.quantity)" +
                " from Product p " +
                " join ProductCoffee pc on p.id=pc.product.id" +
                " join ProductQuantity pq on pc.product.id=pq.product.id " +
                " where p.productName like :search " +
                " ";
        List<ProductResponse> responseList = new ArrayList<>();
        TypedQuery<ProductResponse> typedQuery = entityManager
                .createQuery(query, ProductResponse.class);
//        typedQuery.setParameter("priceMin", priceMin);
//        typedQuery.setParameter("priceMax", priceMax);
//        typedQuery.setParameter("sourFrom", sourFrom);
//        typedQuery.setParameter("sourTo", sourTo);
//        typedQuery.setParameter("strongFrom", strongFrom);
//        typedQuery.setParameter("strongTo", strongTo);
//        typedQuery.setParameter("bitterFrom", bitterFrom);
//        typedQuery.setParameter("bitterTo", bitterTo);
//        typedQuery.setParameter("sortBy", sortBy);
        typedQuery.setParameter("search", search.concat("%"));

        responseList = typedQuery.getResultList();

        return ProductListResponse.builder().popular(responseList.get(0)).products(responseList).build();
    }
}
