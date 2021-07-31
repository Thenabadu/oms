package com.palo.oms.service;

import com.palo.oms.view_model.OrderInfoVM;
import org.springframework.web.multipart.MultipartFile;

public interface OrderService {

    void save(MultipartFile file);

    OrderInfoVM getOrderDetails(int pageIndex, int pageSize);

}
