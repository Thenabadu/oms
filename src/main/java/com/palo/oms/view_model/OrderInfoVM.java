package com.palo.oms.view_model;

import com.palo.oms.model.OrderInfo;
import lombok.Data;

import java.util.List;

@Data
public class OrderInfoVM {
    private long totalCount;
    private List<OrderInfo> orderInfos;
}
