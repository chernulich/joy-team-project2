package com.coffeeshop.repository.search;

import com.coffeeshop.model.web.product.ProductListResponse;
import com.coffeeshop.model.web.product.ProductParametersResponse;
import com.coffeeshop.model.web.product.ProductRequest;
import com.coffeeshop.model.web.product.ProductResponse;
import com.coffeeshop.model.web.product.type.SortStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProductSearchRepositoryImpl implements ProductSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public ProductListResponse searchProductsViaParams(ProductRequest request) {

        if(requestIsEmpty(request)) {
            TypedQuery<Object[]> typedQuery = entityManager.createQuery(getQuery(), Object[].class);
            List<Object[]> dbResponse = typedQuery.getResultList();
            return convertDBResponseToProductResponses(dbResponse);
        }
        TypedQuery<Object[]> typedQuery = createTypedQuery(request);

        int pageNumber = request.getPage();
        int pageSize = request.getResults();

        typedQuery.setFirstResult((pageNumber - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);

        List<Object[]> dbResponse = typedQuery.getResultList();

        if (dbResponse.isEmpty()) {
            return new ProductListResponse();
        }

        return convertDBResponseToProductResponses(dbResponse);
    }

    private boolean requestIsEmpty(ProductRequest request) {
        return request.getPriceMax() == null && request.getPriceMin() == null && request.getCharacteristics() == null
                && request.getSearch() == null && request.getSortBy()==null;
    }

    private TypedQuery<Object[]> createTypedQuery(ProductRequest request) {
        if(request.getSortBy() == null) {
            request.setSortBy(SortStatus.PRICE);
        }
        String sortBy = request.getSortBy().toString();
        String query = getQuery(sortBy);

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(query, Object[].class);
        typedQuery.setParameter("priceMin", request.getPriceMin());
        typedQuery.setParameter("priceMax", request.getPriceMax());
        typedQuery.setParameter("sourFrom",
                request.getCharacteristics().getSourFrom() != null ? request.getCharacteristics().getSourFrom() : 1);
        typedQuery.setParameter("sourTo",
                request.getCharacteristics().getSourTo() != null ? request.getCharacteristics().getSourTo() : 5);
        typedQuery.setParameter("strongFrom",
                request.getCharacteristics().getStrongFrom() != null ? request.getCharacteristics().getStrongFrom() : 1);
        typedQuery.setParameter("strongTo",
                request.getCharacteristics().getStrongTo() != null ? request.getCharacteristics().getSourTo() : 5);
        typedQuery.setParameter("bitterFrom",
                request.getCharacteristics().getBitterFrom() != null ? request.getCharacteristics().getBitterFrom() : 1);
        typedQuery.setParameter("bitterTo",
                request.getCharacteristics().getBitterTo() != null ? request.getCharacteristics().getBitterTo() : 5);
        typedQuery.setParameter("decaf",
                request.getCharacteristics().getDecaf() != null ? request.getCharacteristics().getDecaf() : true);
        typedQuery.setParameter("ground",
                request.getCharacteristics().getGround() != null ? request.getCharacteristics().getGround() : true);
        typedQuery.setParameter("search", request.getSearch().concat("%"));

        return typedQuery;
    }

    private ProductListResponse convertDBResponseToProductResponses(List<Object[]> dbResponse) {
        List<ProductResponse> productResponseList = dbResponse.stream()
                .map(array -> {
                    List<Object> objects = Arrays.asList(array);
                    List<String> names = objects.stream().map(Object::toString).collect(Collectors.toList());
                    return ProductResponse.builder()
                            .productId(Long.parseLong(names.get(0)))
                            .title(names.get(1))
                            .shortDescription(names.get(2))
                            .type(names.get(3))
                            .price(Double.valueOf(names.get(4)))
                            .previewImage(names.get(5))
                            .availableAmount(Integer.parseInt(names.get(6)))
                            .productParametersResponse(
                                    ProductParametersResponse.builder()
                                            .strong(Integer.parseInt(names.get(7)))
                                            .sour(Integer.parseInt(names.get(8)))
                                            .bitter(Integer.parseInt(names.get(9)))
                                            .decaf(Boolean.valueOf(names.get(10)))
                                            .ground(Boolean.valueOf(names.get(11)))
                                            .build()).build();
                }).collect(Collectors.toList());
        return ProductListResponse.builder()
                .popular(productResponseList.get(0))
                .products(productResponseList.subList(1,productResponseList.size())).build();
    }

    private String getQuery(String sortBy) {
        return new StringBuilder()
                .append("select ")
                .append("pc.product.id, p.productName, p.shortDescription, p.productCategory, p.unitPrice, p.previewImage, pq.quantity,")
                .append(" pc.strong, pc.sour, pc.bitter, pc.decaf, pc.ground")
                .append(" from Product p ")
                .append(" join ProductCoffee pc on p.id=pc.product.id")
                .append(" join ProductQuantity pq on pc.product.id=pq.product.id")
                .append(" where p.productName like :search")
                .append(" and p.unitPrice between :priceMin and :priceMax")
                .append(" and pc.bitter between :bitterFrom and :bitterTo")
                .append(" and pc.sour between :sourFrom and :sourTo")
                .append(" and pc.strong between :strongFrom and :strongTo")
                .append(" and pc.decaf = :decaf")
                .append(" and pc.ground = :ground")
                .append(" order by")
                .append(sortBy)
                .toString();
    }

    private String getQuery() {
        return new StringBuilder()
                .append("select ")
                .append("pc.product.id, p.productName, p.shortDescription, p.productCategory, p.unitPrice, p.previewImage, pq.quantity,")
                .append(" pc.strong, pc.sour, pc.bitter, pc.decaf")
                .append(" from Product p ")
                .append(" join ProductCoffee pc on p.id=pc.product.id")
                .append(" join ProductQuantity pq on pc.product.id=pq.product.id ")
                .toString();
    }

}
