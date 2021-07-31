package com.palo.oms.repository;

import com.palo.oms.model.OrderInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<OrderInfo, String> {
}
