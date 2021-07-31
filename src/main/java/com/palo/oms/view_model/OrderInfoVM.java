package com.palo.oms.view_model;

import com.palo.oms.model.OrderInfo;
import lombok.Data;

import java.util.List;

@Data
public class OrderInfoVM {
    private int totalCount;
    private List<OrderInfo> orderInfos;
}
