package com.yx.base.spring.boot;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.yx.base.spring.boot.mongo.Account;
import com.yx.base.spring.boot.mongo.MongoOrder;
import com.yx.base.spring.boot.mongo.MongoUser;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * springboot 整合mongoDB测试使用 -- 掌握
 *
 * @author yx start
 * @create 2020/10/18,20:18
 */
@SpringBootTest(classes = BaseSpringBootApplication.class)
public class MongoDBApplicationTest {
    @Resource
    private MongoTemplate mongoTemplate;

    // 新增单个
    @Test
    public void testCreate() {
        MongoUser mongoUser = new MongoUser();
        mongoUser.setAge(12);
        mongoUser.setBir(LocalDateTime.now());
        mongoUser.setId(UUID.randomUUID().toString().replace("-", ""));
        mongoUser.setName("杨新");
        MongoUser save = mongoTemplate.save(mongoUser);
        System.out.println(save.toString());
    }

    /**
     * 如果insert 操作， 存在相同的id 就会插入失败
     */
    @Test
    public void testInsert() {
        MongoUser mongoUser = new MongoUser();
        mongoUser.setAge(12);
        mongoUser.setBir(LocalDateTime.now());
        mongoUser.setId("6dca3e084df74fe39ffc65b958bada35");
        mongoUser.setName("杨新");
        MongoUser save = mongoTemplate.insert(mongoUser);
        System.out.println(save.toString());
    }

    // 测试有级联关系的两个对象
    @Test
    public void testCreate2() {
        MongoUser mongoUser = new MongoUser();
        mongoUser.setName("杨新测试");
        mongoUser.setId(UUID.randomUUID().toString().replace("-", ""));
        mongoUser.setBir(LocalDateTime.now());
        List<Account> accountList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Account account = new Account();
            account.setAccountName("account" + i);
            account.setAccountZh("zh_" + i);
            accountList.add(account);
        }
        List<MongoOrder> orderList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            MongoOrder mongoOrder = new MongoOrder();
            mongoOrder.setOrderId(UUID.randomUUID().toString().replace("-", ""));
            mongoOrder.setOrderName("orderName" + i);
            //
            mongoTemplate.save(mongoOrder); // 如果不添加则不会新增表t_order存储
            orderList.add(mongoOrder);
        }
        mongoUser.setAccountList(accountList);
        mongoUser.setMongoOrderList(orderList);
        mongoTemplate.save(mongoUser);
    }

    /**
     * 测试批量插入数据
     */
    @Test
    public void testBatchSave() {
        List<MongoUser> mongoUserList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MongoUser mongoUser = new MongoUser();
            mongoUser.setId(String.valueOf(i));
            mongoUser.setBir(LocalDateTime.now());
            mongoUser.setName("name" + i);
            mongoUser.setAge(i);
            mongoUserList.add(mongoUser);
        }
        mongoTemplate.insert(mongoUserList, MongoUser.class);
    }

    // 删除
    @Test
    public void testDelete() {
        MongoUser mongoUser = new MongoUser();
        mongoUser.setId("156b4be2870f438db5bfb9e05d9840e6");
        mongoTemplate.remove(mongoUser);
    }


    // 主键查询1，获取级联对象
    @Test
    public void testSelectById() {
        MongoUser mongoUser = mongoTemplate.findById("156b4be2870f438db5bfb9e05d9840e6", MongoUser.class);
        System.out.println("mongoUser==>" + mongoUser.toString());
        mongoUser.getMongoOrderList().stream().forEach((order) -> {
            System.out.println(order.toString());
        });
    }

    /**
     * 测试排序
     */
    @Test
    public void testSort() {
        Query query = new Query();
        query.addCriteria(new Criteria().where("age").gt(2));
        query.limit(2);
        query.skip(1);
        query.with(Sort.by(Sort.Direction.DESC, "age"));
        List<MongoUser> mongoUsers = mongoTemplate.find(query, MongoUser.class);
        mongoUsers.stream().forEach(mongoUser -> {
            System.out.println(mongoUser);
        });
    }

    /**
     * 分页排序
     */
    @Test
    public void testSelectByPage() {
        Query query = new Query();
        query.with(PageRequest.of(1, 2, Sort.by(Sort.Direction.DESC, "age")));
        List<MongoUser> mongoUserList = mongoTemplate.find(query, MongoUser.class);
        mongoUserList.stream().forEach((mongoUser) ->
                System.out.println(mongoUser.toString()));
    }

    // 范围查询  大于 小于
    @Test
    public void testSelectRange() {
        Query query = new Query();
//        query.addCriteria(Criteria.where("age").gt(1).lte(8)); // and
        //   query.addCriteria(new Criteria().andOperator(Criteria.where("age").gt(1),Criteria.where("age").lt(8))); // and 操作
        query.addCriteria(new Criteria().orOperator(Criteria.where("age").gt(1), Criteria.where("age").lt(8))); // or 的关系
//        query.addCriteria(new Criteria().)
        List<MongoUser> mongoUserList = mongoTemplate.find(query, MongoUser.class);
        mongoUserList.stream().forEach((mongoUser) ->
                System.out.println(mongoUser.toString()));
    }


    // 更新操作 不会被覆盖，只会更新当前字段
    @Test
    public void testUpdate() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("name4"));
        Update update = new Update();
        // update.set("name","name_yangxin_update");
        update.set("name", new Object[]{"name_yangxin_update1", "name_yangxin_update2"});
        mongoTemplate.updateFirst(query, update, MongoUser.class);
    }

    /**
     * 换一种更新方式 BasicUpdate  会覆盖其他字段 -- 警告 警告
     */
    @Test
    public void testUpdate2() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("name9"));
        // 新元素替换旧元素
        Document basicDBObject = new Document("name", "name_yangxin_update_basicDBObject");
        BasicUpdate basicUpdate = new BasicUpdate(basicDBObject);
        mongoTemplate.updateFirst(query, basicUpdate, MongoUser.class);
    }


}
