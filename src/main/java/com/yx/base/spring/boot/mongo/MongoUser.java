package com.yx.base.spring.boot.mongo;

import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yx start
 * @create 2020/10/18,20:24
 */
@Data
@Document("t_user")
public class MongoUser {

    @MongoId
    private String id;
    @Indexed // 创建索引
    private String name;
    @Field("age") // 重命名字段
    private Integer age;
    private LocalDateTime bir;

    @Transient // 不进行序列化
    private String remark;

    @DBRef
    private List<MongoOrder> mongoOrderList;

    private List<Account> accountList;
}
