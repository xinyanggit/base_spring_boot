package com.yx.base.spring.boot.jd.service;

import com.alibaba.fastjson.JSON;
import com.yx.base.spring.boot.jd.mode.Good;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yangxin2@cnhnkj.com
 * @create 2020-09-21 11:45
 */
@Service
public class JdService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Resource
    private ContentParseService contentParseService;

    // 初始化数据
    public Boolean joinEsData(String keyWord) {
        List<Good> goodList = null;
        try {
            goodList = contentParseService.parse(keyWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("20s");
        goodList.forEach(good -> {
            IndexRequest indexRequest = new IndexRequest(JdGoodsConstant.ES_JD_GOOD_INDEX);
            bulkRequest.add(indexRequest.source(JSON.toJSONString(good), XContentType.JSON));
        });
        BulkResponse bulk = null;
        try {
            bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return !bulk.hasFailures();
    }

    //   从Es 中获取数据 ，显示搜索功能

    public List<Map<String, Object>> searchPage(String keyWord, int pageNo, int pageSize) throws IOException {
        if (pageNo <= 1) {
            pageNo = 1;
        }
        // 条件搜索
        SearchRequest searchRequest = new SearchRequest();

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 构建搜索

        // 构建分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);


        // 精准匹配
        searchSourceBuilder.query(QueryBuilders.termQuery("title", keyWord));
        searchRequest.source(searchSourceBuilder);


        // 进行查询
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        // 结果解析
        SearchHit[] hits = search.getHits().getHits();
        if (Objects.isNull(hits)) {
            return new ArrayList<>();
        }
        List<Map<String, Object>> list = new ArrayList<>(hits.length);
        for (SearchHit hit : hits) {
            list.add(hit.getSourceAsMap());
        }
        return list;
    }

    //   从Es 中获取数据 ，显示搜索功能

    public List<Map<String, Object>> searchHighlighterPage(String keyWord, int pageNo, int pageSize) throws IOException {
        if (pageNo <= 1) {
            pageNo = 1;
        }
        // 条件搜索
        SearchRequest searchRequest = new SearchRequest();

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 构建搜索

        // 构建分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);

        // 高亮搜索
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
//        highlightBuilder.requireFieldMatch(false); // 是否需要同一个文字中的 同时出现多个高亮显示
        highlightBuilder.preTags("<span  style='color:red'>");
        highlightBuilder.postTags("</style>");
        searchSourceBuilder.highlighter(highlightBuilder);

        // 精准匹配
        searchSourceBuilder.query(QueryBuilders.termQuery("title", keyWord));
        searchRequest.source(searchSourceBuilder);

        // 进行查询
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        // 结果解析
        SearchHit[] hits = search.getHits().getHits();
        if (Objects.isNull(hits)) {
            return new ArrayList<>();
        }
        List<Map<String, Object>> list = new ArrayList<>(hits.length);
        for (SearchHit hit : hits) {
            // 获取高亮的字段 , 将原来的字段替换即可
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField titleHighlightField = highlightFields.get("title");
            // 最开始的结果
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            if (Objects.nonNull(titleHighlightField)) {
                Text[] fragments = titleHighlightField.fragments();
                StringBuffer highlight = new StringBuffer();
                for (Text fragment : fragments) {
                    highlight.append(fragment);
                }
                sourceAsMap.put("title", new String(highlight)); // 用高亮的字段替换原来的内容即可
            }
            list.add(sourceAsMap);
        }
        return list;
    }

}
