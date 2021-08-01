package com.palo.oms.model;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "order_info")
public class OrderInfo {

    @Id
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "item_type", nullable = false)
    private String itemType;

    @Column(name = "sales_channel", nullable = false)
    private String salesChannel;

    @Column(name = "priority", nullable = false)
    private String orderPriority;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Column(name = "ship_date", nullable = false)
    private Date shipDate;

    @Column(name = "units_sold", nullable = false)
    private Integer noOfUnits;

    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    @Column(name = "unit_cost", nullable = false)
    private Double unitCost;

    @Column(name = "total_revenue", nullable = false)
    private Double totalRevenue;

    @Column(name = "total_cost", nullable = false)
    private Double totalCost;

    @Column(name = "total_profit", nullable = false)
    private Double totalProfit;

    @Column(name = "nric", nullable = false)
    private String nric;
}
