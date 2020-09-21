package com.yx.base.spring.boot;

import com.alibaba.fastjson.JSON;
import com.yx.base.spring.boot.user.User;
import org.apache.commons.lang3.RandomUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.ml.PostDataRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
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
    private String INDEX_NAME = "yang_xin_java_new";

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

    // 文档操作 ============================================================start
    // 创建内容  构建存储对象 构建发送请求
    @Test
    void testCreateDocumentData() throws Exception {
        // 创建请求
        IndexRequest createIndexRequest = new IndexRequest(INDEX_NAME);
        createIndexRequest.timeout(TimeValue.timeValueSeconds(10));

        // 创建存储对象
        User user = new User();
        user.setId(2L);
        user.setPassword("密码测试");
        user.setName("杨新测试");
        //  user.setPassword("密码");
        createIndexRequest.source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(index.status());
    }

    // 文档 是否存在某条记录
    @Test
    void testExistDocumentData() throws Exception {
        GetRequest getRequest = new GetRequest(INDEX_NAME, "1").fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println("是否存在某条记录==" + exists);
    }

    // 测试返回的文档数据
    @Test
    void testGetDocumentData() throws Exception {
        GetRequest getRequest = new GetRequest(INDEX_NAME);
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println("返回的结果===>" + getResponse.getSourceAsString());
        System.out.println(getResponse);
    }

    // 更新文档数据
    @Test
    void testUpdateDocumentData() throws Exception {
        UpdateRequest updateRequest = new UpdateRequest(INDEX_NAME, "1");
        updateRequest.timeout(TimeValue.timeValueMillis(10));
        // 创建存储对象
        User user = new User();
        user.setPassword("密码测试更新操作");
        user.setName("杨新测试更新操作");
        user.setId(1L);
        updateRequest.doc(JSON.toJSONString(user), XContentType.JSON);
        UpdateResponse update = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println("返回的结果===>" + update.getResult());
        System.out.println(update.status());
    }

    // 删除文档数据 id 为 3
    @Test
    void testDeleteDocumentData() throws Exception {
        DeleteRequest deleteRequest = new DeleteRequest(INDEX_NAME);
        deleteRequest.id("3");
        DeleteResponse delete = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(delete.status());
    }

    // 批量添加数据
    @Test
    void testBulkDocumentData() throws Exception {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout(TimeValue.timeValueMillis(20));
        for (int i = 1; i <= 20; i++) {
            User user = User.builder().name("张三" + i).build();
            user.setPassword("密码" + RandomUtils.nextInt(0, 100));
            user.setAccount("account" + RandomUtils.nextLong(0, 10));
            IndexRequest indexRequest = new IndexRequest(INDEX_NAME);
            indexRequest.id(String.valueOf(i));
            indexRequest.source(JSON.toJSONString(user), XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.hasFailures()); //是否失败
    }

    @Test
    void testSearchDocumentData() throws Exception {

        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        // 构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.highlighter();  /// 设置相关高亮
        // 查询条件 ，我们可以使用QueryBuilders 工具来实现
//        QueryBuilders.termQuery("name","张三")  // 精确
//        QueryBuilders.matchAllQuery();// 匹配所有
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "张");
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(10));
        searchRequest.source(searchSourceBuilder);

        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("返回的结果==>" + search.getHits().getTotalHits().value);
        System.out.println(search.getHits());
        for (SearchHit hit : search.getHits().getHits()) {
            System.out.println("显示结果==>" + hit.getSourceAsMap());
        }
    }


}