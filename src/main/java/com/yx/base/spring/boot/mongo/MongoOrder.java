package com.yx.base.spring.boot.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * @author yx start
 * @create 2020/10/18,20:47
 */
@Document(collation = "t_order")
@Data
public class MongoOrder {

    @MongoId
    private String orderId;

    private String orderName;
    

}
