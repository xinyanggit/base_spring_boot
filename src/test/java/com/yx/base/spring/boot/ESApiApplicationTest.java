package com.yx.base.spring.boot;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * ES API测试
 *
 * @author yangxin2@cnhnkj.com
 * @create 2020-09-18 16:46
 */
@SpringBootTest
public class ESApiApplicationTest {


    @Autowired
    private RestHighLevelClient restHighLevelClient;
    private String INDEX_NAME = "yang_xin_java";

    //增=> 测试 索引的创建 。 request
    @Test
    public void testCreateIndex() throws IOException {
        // 创建索引库
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(INDEX_NAME);

        //客户端 ,执行对应的请求
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println("创建索引==>" + createIndexResponse);
    }

    // 判断索引库是否存在
    @Test
    void testIndexExist() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest(INDEX_NAME);
        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println("是否存在索引库==>" + exists);
    }

    // 删除索引库
    @Test
    void testDeleteExist() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(INDEX_NAME);
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println("删除索引库==>" + delete.isAcknowledged());
    }

    // 创建内容


}
