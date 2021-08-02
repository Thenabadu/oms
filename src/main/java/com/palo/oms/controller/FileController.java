package com.palo.oms.controller;

import com.palo.oms.message.ResponseMessage;
import com.palo.oms.service.OrderService;
import com.palo.oms.util.CSVUtil;
import com.palo.oms.view_model.OrderInfoVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = ("${app-allow-cross-origin-domain}"))
@RestController
public class FileController {

    @Value("${app-title}")
    private String projectTitle;

    @Autowired
    @Qualifier("orderServiceImpl")
    private OrderService orderService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message;
        if (CSVUtil.isInCSVFormat(file)) {
            try {
                orderService.save(file);
                message = "Successfully uploaded the file: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Error occurred while upoading the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));//this need to change to internal server error status
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/order-info")
    public OrderInfoVM getOrderInfo(@RequestParam("pageIndex") int pageIndex,
                                    @RequestParam("pageSize") int pageSize) {
        return orderService.getOrderDetails(pageIndex, pageSize);
    }

    @GetMapping("/")
    public ResponseEntity<String> welcome(){
        return ResponseEntity.status(HttpStatus.OK).body("welcome to "+projectTitle);
    }
}
