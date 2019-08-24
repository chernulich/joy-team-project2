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
        Integer sourFrom = request.getCharacteristics().getSourFrom();
        Integer sourTo = request.getCharacteristics().getSourTo();
        Double priceMin = request.getPriceMin();
        Double priceMax = request.getPriceMax();
        Integer strongFrom = request.getCharacteristics().getStrongFrom();
        Integer strongTo = request.getCharacteristics().getStrongTo();
        Integer bitterFrom = request.getCharacteristics().getBitterFrom();
        Integer bitterTo = request.getCharacteristics().getBitterTo();
        String search = request.getSearch();
        String sortBy = request.getSortBy();

        String query = "SELECT p FROM PRODUCT p WHERE p.PRODUCT_NAME LIKE " + search + "% " +
                "INNER JOIN PRODUCT_COFFEE WHERE PRICE BETWEEN " + priceMin + " AND " + priceMax +
                " AND SOUR BETWEEN " + sourFrom + " AND " + sourTo +
                " AND STRONG BETWEEN " + strongFrom + " AND " + strongTo +
                " AND BITTER BETWEEN " + bitterFrom + " AND " + bitterTo + " ORDER BY " + sortBy +
                " ON p.ID = PRODUCT_COFFEE.PRODUCT_ID";

        List<ProductResponse> responseList = new ArrayList<>();
        TypedQuery<ProductResponse> typedQuery = entityManager
                .createQuery(query, ProductResponse.class);
        responseList = typedQuery.getResultList();

        return ProductListResponse.builder().popular(responseList.get(0)).products(responseList).build();
    }
}
