package com.palo.oms.service;

import com.palo.oms.model.OrderInfo;
import com.palo.oms.repository.OrderRepository;
import com.palo.oms.util.CSVUtil;
import com.palo.oms.view_model.OrderInfoVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void save(MultipartFile file) {
        try {
            //InputStream inputStream  = new FileInputStream("D:\\Interviews\\Palo-IT\\500000-Sales-Records\\500000 Sales Records.csv");
            //List<OrderInfo> orders = CSVUtil.convertToOrders(inputStream);
            List<OrderInfo> orders = CSVUtil.convertToOrders(file.getInputStream());
            orderRepository.saveAll(orders);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    @Override
    public OrderInfoVM getOrderDetails(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by("orderDate").descending());
        Page<OrderInfo> page = orderRepository.findAll(pageable);
        OrderInfoVM orderInfoVM = new OrderInfoVM();
        orderInfoVM.setTotalCount(page.getTotalPages());
        orderInfoVM.setOrderInfos(page.getContent());
        return orderInfoVM;
    }
}
