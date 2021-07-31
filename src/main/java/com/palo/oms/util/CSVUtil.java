package com.palo.oms.util;

import com.palo.oms.model.OrderInfo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVUtil {

    private static final String TYPE = "text/csv";

    public static boolean isInCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<OrderInfo> convertToOrders(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<OrderInfo> orderInfos = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                OrderInfo orderInfo = validateCSVRecordAndConvertToOrder(csvRecord);
                if(orderInfo != null){
                    orderInfos.add(orderInfo);
                }
            }
            return orderInfos;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    private static OrderInfo validateCSVRecordAndConvertToOrder(CSVRecord csvRecord){
        String region = csvRecord.get("Region");
        if(region == null || region.isEmpty()){
            return null;
        }
        String country = csvRecord.get("Country");
        if(country == null || country.isEmpty()){
            return null;
        }
        String itemType = csvRecord.get("Item Type");
        if(itemType == null || itemType.isEmpty()){
            return null;
        }
        String salesChannel = csvRecord.get("Sales Channel");
        if(salesChannel == null || salesChannel.isEmpty()){
            return null;
        }
        String orderPriority = csvRecord.get("Order Priority");
        if(orderPriority == null || orderPriority.isEmpty()){
            return null;
        }
        String orderDate = csvRecord.get("Order Date");
        if(orderDate == null || orderDate.isEmpty()){
            return null;
        }
        String orderID = csvRecord.get("Order ID");
        if(orderID == null || orderID.isEmpty()){
            return null;
        }
        String shipDate = csvRecord.get("Ship Date");
        if(shipDate == null || shipDate.isEmpty()){
            return null;
        }
        String unitsSold = csvRecord.get("Units Sold");
        if(unitsSold == null || unitsSold.isEmpty()){
            return null;
        }
        String unitsPrice = csvRecord.get("Unit Price");
        if(unitsPrice == null || unitsPrice.isEmpty()){
            return null;
        }
        String unitCost = csvRecord.get("Unit Cost");
        if(unitCost == null || unitCost.isEmpty()){
            return null;
        }
        String totalRevenue = csvRecord.get("Total Revenue");
        if(totalRevenue == null || totalRevenue.isEmpty()){
            return null;
        }
        String totalCost = csvRecord.get("Total Cost");
        if(totalCost == null || totalCost.isEmpty()){
            return null;
        }
        String totalProfit = csvRecord.get("Total Profit");
        if(totalProfit == null || totalProfit.isEmpty()){
            return null;
        }
        String nric = NRICUtil.createRandomNRIC();
        if(nric == null || nric.isEmpty() || !NRICUtil.isValidNRIC(nric)){
            return null;
        }

        try {
            OrderInfo orderInfo = new OrderInfo();

            orderInfo.setOrderId(orderID);
            orderInfo.setRegion(region);
            orderInfo.setCountry(country);
            orderInfo.setItemType(itemType);
            orderInfo.setSalesChannel(salesChannel);
            orderInfo.setOrderPriority(orderPriority);
            orderInfo.setOrderDate(DateUtil.convertStringToDate(orderDate, "d/M/yyyy"));
            orderInfo.setShipDate(DateUtil.convertStringToDate(shipDate, "d/M/yyyy"));
            orderInfo.setNoOfUnits(Integer.parseInt(unitsSold));
            orderInfo.setUnitPrice(Double.parseDouble(unitsPrice));
            orderInfo.setUnitCost(Double.parseDouble(unitCost));
            orderInfo.setTotalRevenue(Double.parseDouble(totalRevenue));
            orderInfo.setTotalCost(Double.parseDouble(totalCost));
            orderInfo.setTotalProfit(Double.parseDouble(totalProfit));
            orderInfo.setNric(nric);
            return orderInfo;
        }catch (Exception ex){
            return null;
        }
    }

}
