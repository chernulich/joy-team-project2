package com.coffeeshop.repository.search;

import com.coffeeshop.model.web.product.ProductListResponse;
import com.coffeeshop.model.web.product.ProductRequest;
import com.coffeeshop.model.web.product.ProductResponse;
import com.coffeeshop.model.web.product.ProductParametersResponse;
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

    @Override
    public ProductListResponse getProductsViaSearchProductRequest(ProductRequest request) {
        String query = getQuery(request.getSortBy());

        List<Object[]> responseFromDB = new ArrayList<>();

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

        responseFromDB = typedQuery.getResultList();
        responseFromDB.forEach(res -> System.out.println(Arrays.toString(res)));
        if (responseFromDB.isEmpty()) {
            return new ProductListResponse(new ProductResponse(), new ArrayList<ProductResponse>());
        }
        List<ProductResponse> responseList = new ArrayList<>();
        responseList = responseFromDB.stream().map(array -> {
            List<Object> objects = Arrays.asList(array);
            List<String> names = objects.stream().map(object -> object.toString()).collect(Collectors.toList());
            return  new ProductResponse(Long.getLong(names.get(0)), names.get(1), names.get(2), names.get(3),
                    Double.valueOf(names.get(4)), names.get(5), Integer.getInteger(names.get(6)),
                    new ProductParametersResponse(Integer.getInteger(names.get(6)), Integer.getInteger(names.get(7)),
                            Integer.getInteger(names.get(9)), Boolean.getBoolean(names.get(10))));
        }).collect(Collectors.toList());

        return ProductListResponse.builder().popular(responseList.get(0)).products(responseList).build();
    }

    private String getQuery(String sortBy) {
        StringBuilder query = new StringBuilder()
//                .append("select new com.coffeeshop.model.web.product.ProductResponse")
//                .append("(pc.product.id, p.productName, p.shortDescription, p.unitPrice, p.previewImage, pq.quantity)")
                .append("select ")   //TODO
                .append("pc.product.id, p.productName, p.shortDescription, p.productCategory, p.unitPrice, p.previewImage, pq.quantity,")
                .append(" pc.strong, pc.sour, pc.bitter, pc.decaf")                        //TODO
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
                .append(" p.unitPrice, p.productName");
        return query.toString();
    }

}
