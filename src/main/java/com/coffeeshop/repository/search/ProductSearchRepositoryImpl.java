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
import java.util.List;

@Repository
public class ProductSearchRepositoryImpl implements ProductSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ProductListResponse getProductsViaSearchProductRequest(ProductRequest request) {

        String query = getQuery(request.getSortBy());
//        String query = "select new com.coffeeshop.model.web.product.ProductResponse" +
//                "(pc.product.id, p.productName, p.shortDescription, p.unitPrice, p.previewImage, pq.quantity,) " +
//                " from Product p " +
//                " join ProductCoffee pc on p.id=pc.product.id  join ProductQuantity pq on pc.product.id=pq.product.id ";

        List<ProductResponse> responseList = new ArrayList<>();
        TypedQuery<ProductResponse> typedQuery = entityManager.createQuery(query, ProductResponse.class);
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

        responseList = typedQuery.getResultList();
        if (responseList.isEmpty()) {
            return new ProductListResponse(new ProductResponse(), new ArrayList<ProductResponse>());
        }
        return ProductListResponse.builder().popular(responseList.get(0)).products(responseList).build();
    }

    private String getQuery(String sortBy) {
        StringBuilder query = new StringBuilder()
                .append("select new com.coffeeshop.model.web.product.ProductResponse")
                .append("(pc.product.id, p.productName, p.shortDescription, p.unitPrice, p.previewImage, pq.quantity)")
//                .append(" new com.coffeeshop.model.web.product.ProductParametersResponse")   //TODO
//                .append("(pc.strong, pc.sour, pc.bitter, pc.decaf))")                        //TODO
                .append(" from Product p ")
                .append(" join ProductCoffee pc on p.id=pc.product.id")
                .append(" join ProductQuantity pq on pc.product.id=pq.product.id ");
        if (sortBy.equals("popular")) {
            query.append(" left join ProductItem pi on pi.product.id=pq.product.id " +    //TODO
                    " and pi.productStatus.id=2");             //TODO
        }
        query.append(" where p.productName like :search")
                .append(" and p.unitPrice between :priceMin and :priceMax")
                .append(" and pc.bitter between :bitterFrom and :bitterTo")
                .append(" and pc.sour between :sourFrom and :sourTo")
                .append(" and pc.strong between :strongFrom and :strongTo")
                .append(" and pc.decaf = :decaf")
                .append(" and pc.ground = :ground")
                .append(" order by");

        if (sortBy.equals("price")) {
            query.append(" p.unitPrice");
        }else if (sortBy.equals("name")) {
            query.append(" p.productName");
        }else {
            query.append(" count(pi)");                    //TODO
        }
        return query.toString();
    }

}
