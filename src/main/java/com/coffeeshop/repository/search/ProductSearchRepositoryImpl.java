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
        String sortBy = request.getSortBy();

        String query = "select new com.coffeeshop.model.web.product.ProductRequest(pc.product.id, p.title, " +
                "p.shortDescription, p.price, p.previewImage, pq.quantity)" +
                "from ProductCoffee pc, Product p, ProductQuantity pq where p.id = pc.product.id" +
                " and  pq.product.id = pc.product.id";
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

        responseList = typedQuery.getResultList();

        return ProductListResponse.builder().popular(responseList.get(0)).products(responseList).build();
    }
}
