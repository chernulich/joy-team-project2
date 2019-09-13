package com.coffeeshop.repository.search;

import com.coffeeshop.model.web.product.ProductListResponse;
import com.coffeeshop.model.web.product.ProductParametersResponse;
import com.coffeeshop.model.web.product.ProductRequest;
import com.coffeeshop.model.web.product.ProductResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductSearchRepositoryImpl implements ProductSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ProductListResponse searchProductsViaParams(ProductRequest request) {
        TypedQuery<Object[]> typedQuery = createTypedQuery(request);

        int pageNumber = request.getPage();
        int pageSize = request.getResults();

        typedQuery.setFirstResult((pageNumber - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);

        List<Object[]> dbResponse = typedQuery.getResultList();

        if (dbResponse.isEmpty()) {
            return new ProductListResponse();
        }
        List<ProductResponse> responseList = convertDBResponseToProductResponses(dbResponse);

        return ProductListResponse.builder()
                .popular(responseList.get(0))
                .products(responseList).build();
    }

    private TypedQuery<Object[]> createTypedQuery(ProductRequest request) {
        String query = getQuery();

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(query, Object[].class);
        typedQuery.setParameter("priceMin", request.getPriceMin());
        typedQuery.setParameter("priceMax", request.getPriceMax());
        typedQuery.setParameter("sourFrom", request.getCharacteristics().getSourFrom());
        typedQuery.setParameter("sourTo", request.getCharacteristics().getSourTo());
        typedQuery.setParameter("strongFrom", request.getCharacteristics().getStrongFrom());
        typedQuery.setParameter("strongTo", request.getCharacteristics().getStrongTo());
        typedQuery.setParameter("bitterFrom", request.getCharacteristics().getBitterFrom());
        typedQuery.setParameter("bitterTo", request.getCharacteristics().getBitterTo());
        typedQuery.setParameter("decaf", request.getCharacteristics().getDecaf());
        typedQuery.setParameter("ground", request.getCharacteristics().getGround());
        typedQuery.setParameter("search", request.getSearch().concat("%"));

        return typedQuery;
    }

    private List<ProductResponse> convertDBResponseToProductResponses(List<Object[]> dbResponse) {
        return dbResponse.stream()
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
                                            .decaf(Boolean.getBoolean(names.get(10)))
                                            .build()).build();
                }).collect(Collectors.toList());
    }

    private String getQuery() {
        return new StringBuilder()
                .append("select ")
                .append("pc.product.id, p.productName, p.shortDescription, p.productCategory, p.unitPrice, p.previewImage, pq.quantity,")
                .append(" pc.strong, pc.sour, pc.bitter, pc.decaf")
                .append(" from Product p ")
                .append(" join ProductCoffee pc on p.id=pc.product.id")
                .append(" join ProductQuantity pq on pc.product.id=pq.product.id ")
                .append(" where p.productName like :search")
                .append(" and p.unitPrice between :priceMin and :priceMax")
                .append(" and pc.bitter between :bitterFrom and :bitterTo")
                .append(" and pc.sour between :sourFrom and :sourTo")
                .append(" and pc.strong between :strongFrom and :strongTo")
                .append(" and pc.decaf = :decaf")
                .append(" and pc.ground = :ground")
                .append(" order by")
                .append(" p.unitPrice, p.productName")
                .toString();
    }

}
