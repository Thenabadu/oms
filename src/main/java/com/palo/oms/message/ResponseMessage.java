package com.palo.oms.message;

import com.palo.oms.view_model.OrderInfoVM;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ResponseMessage {
    private String message;
}
