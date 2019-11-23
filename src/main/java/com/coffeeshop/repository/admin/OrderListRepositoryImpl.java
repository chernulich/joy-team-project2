package com.coffeeshop.repository.admin;

import com.coffeeshop.model.entity.type.OrderPaymentStatus;
import com.coffeeshop.model.entity.type.OrderStatus;
import com.coffeeshop.model.entity.type.OrderTransitStatus;
import com.coffeeshop.model.web.admin.OrderListRequest;
import com.coffeeshop.model.web.admin.OrderListResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderListRepositoryImpl implements OrderListRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<OrderListResponse> getOrderListByQuery(OrderListRequest request) {
        TypedQuery<Object[]> typedQuery;
        if (request.getQuery().equals("") || request.getQuery() == null) {
            typedQuery = entityManager.createQuery(getQueryTemplate(request.getQuery()), Object[].class);
        } else {
            typedQuery = entityManager.createQuery(getQueryTemplate(request.getQuery()), Object[].class);
            typedQuery.setParameter("search", request.getQuery().concat("%"));
        }
        setPageAndMaxResult(request, typedQuery);
        List<Object[]> dbResponse = typedQuery.getResultList();

        if (dbResponse.isEmpty()) {
            return new ArrayList<OrderListResponse>();
        }

        return convertDBResponseToOrderListResponse(dbResponse);
    }

    private void setPageAndMaxResult(OrderListRequest request, TypedQuery<Object[]> typedQuery) {
        int pageNumber = request.getPage();
        int pageSize = request.getResult();
        typedQuery.setFirstResult((pageNumber - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);
    }

    private String getQueryTemplate(String request) {

        StringBuilder template = new StringBuilder()
                .append("select ")
                .append("od.customerName, od.contactFirstName , od.contactLastName , od.contactPhoneNumber, od" +
                        ".orderEmail, ")
                .append("op.subtotalPrice, o.orderTransitStatus, o.orderPaymentStatus, o.orderStatus, od" +
                        ".deliveryComment ")
                .append("from OrderDetails od ")
                .append("join Orders o on o.id=od.order.id ")
                .append("join OrderPrice op on op.order.id=od.order.id ");

        if (request != null && !request.isEmpty()) {
            template
                    .append("where od.orderEmail like :search ")
                    .append("or od.customerName like :search ")
                    .append("or od.contactFirstName like :search ")
                    .append("or od.contactLastName like :search");
        }
        return template.toString();
    }

    private List<OrderListResponse> convertDBResponseToOrderListResponse(List<Object[]> dbResponse) {
        List<OrderListResponse> responseList = dbResponse.stream()
                .map(array -> {
                    List<Object> objects = Arrays.asList(array);
                    List<String> names = objects.stream().map(Object::toString).collect(Collectors.toList());
                    return OrderListResponse.builder()
                            .companyName(names.get(0))
                            .contactName(names.get(1) + " " + names.get(2))
                            .phone(names.get(3))
                            .email(names.get(4))
                            .orderTotal(Double.valueOf(names.get(5)))
                            .transitStatus(OrderTransitStatus.valueOf(names.get(6)))
                            .paymentStatus(OrderPaymentStatus.valueOf(names.get(7)))
                            .orderStatus(OrderStatus.valueOf(names.get(8)))
                            .customerComment(names.get(9))
                            .build();
                }).collect(Collectors.toList());
        return responseList;
    }
}
