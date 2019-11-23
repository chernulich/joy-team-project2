package com.coffeeshop.repository.search;

import com.coffeeshop.model.common.CoffeeType;
import com.coffeeshop.model.web.product.ProductListResponse;
import com.coffeeshop.model.web.product.ProductParametersResponse;
import com.coffeeshop.model.web.product.ProductRequest;
import com.coffeeshop.model.web.product.ProductResponse;
import com.coffeeshop.model.web.product.type.SortStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductSearchRepositoryImpl implements ProductSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Double MAX_VALUE = Double.MAX_VALUE;

    @Override
    public ProductListResponse searchProductsViaParams(ProductRequest request) {

        if(requestIsEmpty(request)) {
            TypedQuery<Object[]> typedQuery = entityManager.createQuery(getQuery(), Object[].class);
            setPageAndMaxResult(request, typedQuery);
            List<Object[]> dbResponse = typedQuery.getResultList();

            if (dbResponse.isEmpty()) {
                return new ProductListResponse(new ProductResponse(), new ArrayList<>());
            }
            return convertDBResponseToProductResponses(dbResponse);
        }

        TypedQuery<Object[]> typedQuery = createTypedQuery(request);
        setPageAndMaxResult(request, typedQuery);
        List<Object[]> dbResponse = typedQuery.getResultList();

        if (dbResponse.isEmpty()) {
            return new ProductListResponse(new ProductResponse(), new ArrayList<>());
        }
        return convertDBResponseToProductResponses(dbResponse);
    }

    private void setPageAndMaxResult(ProductRequest request, TypedQuery<Object[]> typedQuery) {
        int pageNumber = request.getPage();
        int pageSize = request.getResults();
        typedQuery.setFirstResult((pageNumber - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);
    }

    private boolean requestIsEmpty(ProductRequest request) {
        return request.getPriceMax() == null && request.getPriceMin() == null && request.getCharacteristics() == null
                && request.getSearch() == null && request.getSortBy()==null;
    }

    private TypedQuery<Object[]> createTypedQuery(ProductRequest request) {
        if(request.getSortBy() == null) {
            request.setSortBy(SortStatus.PRICE);
        }
        String query = getQuery(request);

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(query, Object[].class);
        typedQuery.setParameter("priceMin",
                request.getPriceMin() != null ? request.getPriceMin() : 0);
        typedQuery.setParameter("priceMax",
                request.getPriceMax() != null ? request.getPriceMax() : MAX_VALUE);

        typedQuery.setParameter("sourFrom", setParamFrom(
                request.getCharacteristics().getSourFrom(), request.getCharacteristics().getSourTo()));
        typedQuery.setParameter("sourTo", setParamTo(
                request.getCharacteristics().getSourFrom(), request.getCharacteristics().getSourTo()));
        typedQuery.setParameter("strongFrom", setParamFrom(
                request.getCharacteristics().getStrongFrom(), request.getCharacteristics().getStrongTo()));
        typedQuery.setParameter("strongTo", setParamTo(
                request.getCharacteristics().getStrongFrom(), request.getCharacteristics().getStrongTo()));
        typedQuery.setParameter("bitterFrom", setParamFrom(
                request.getCharacteristics().getBitterFrom(), request.getCharacteristics().getBitterTo()));
        typedQuery.setParameter("bitterTo", setParamTo(
                request.getCharacteristics().getBitterFrom(), request.getCharacteristics().getBitterTo()));

        if (request.getCharacteristics().getDecaf() != null) {
            typedQuery.setParameter("decaf", request.getCharacteristics().getDecaf());
        }
        if (request.getCharacteristics().getGround() != null) {
            typedQuery.setParameter("ground", request.getCharacteristics().getGround());
        }
        if (request.getCharacteristics().getCoffeeType() != null) {
            typedQuery.setParameter("coffeeType", CoffeeType.getByName(request.getCharacteristics().getCoffeeType()));
        }
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
                                            .coffeeType(names.get(12).toLowerCase())
                                            .build()).build();
                }).collect(Collectors.toList());
        return ProductListResponse.builder()
                .popular(productResponseList.get(0))
                .products(productResponseList.subList(1,productResponseList.size())).build();
    }

    private String getQuery(ProductRequest request) {
        String sortBy = request.getSortBy().toString();
        String coffeeType = request.getCharacteristics().getCoffeeType() == null ? "" : " and pc.coffeeType = :coffeeType";
        String decaf = request.getCharacteristics().getDecaf() == null ? "" : " and pc.decaf = :decaf";
        String ground = request.getCharacteristics().getGround() == null ? "" : " and pc.ground = :ground";
        return new StringBuilder()
                .append("select ")
                .append("pc.product.id, p.productName, p.shortDescription, p.productCategory, p.unitPrice, p.previewImage, pq.quantity,")
                .append(" pc.strong, pc.sour, pc.bitter, pc.decaf, pc.ground, pc.coffeeType")
                .append(" from Product p ")
                .append(" join ProductCoffee pc on p.id=pc.product.id")
                .append(" join ProductQuantity pq on pc.product.id=pq.product.id")
                .append(" where p.productName like :search")
                .append(" and p.available = true")
                .append(" and p.unitPrice >= :priceMin")
                .append(" and p.unitPrice <= :priceMax")
                .append(" and pc.bitter between :bitterFrom and :bitterTo")
                .append(" and pc.sour between :sourFrom and :sourTo")
                .append(" and pc.strong between :strongFrom and :strongTo")
                .append(decaf)
                .append(ground)
                .append(coffeeType)
                .append(" order by ")
                .append(sortBy)
                .toString();
    }

    private String getQuery() {
        return new StringBuilder()
                .append("select ")
                .append("pc.product.id, p.productName, p.shortDescription, p.productCategory, p.unitPrice, p.previewImage, pq.quantity,")
                .append(" pc.strong, pc.sour, pc.bitter, pc.decaf, pc.ground, pc.coffeeType")
                .append(" from Product p ")
                .append(" join ProductCoffee pc on p.id=pc.product.id")
                .append(" join ProductQuantity pq on pc.product.id=pq.product.id ")
                .toString();
    }

    private Integer setParamTo(Integer from, Integer to){
        if (to != null) return to;
        if (from != null) return from;
        return 5;
    }

    private Integer setParamFrom(Integer from, Integer to){
        if (from != null) return from;
        if (to != null) return to;
        return 1;
    }

}