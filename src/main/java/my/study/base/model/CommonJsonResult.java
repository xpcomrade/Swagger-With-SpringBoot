package my.study.base.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xpcomrade on 2016/10/24.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: (接口统一返回对象). <br/>
 */
public class CommonJsonResult implements Serializable {

    /**
     * 接口调用后的反应值
     */
    protected static final String STATUS_CODE = "statusCode";

    /**
     * 接口调用后的反应信息
     */
    protected static final String REASON = "reason";

    /**
     * 数据总条数
     */
    public static final String TOTAL_RECORDS = "totalRecords";

    /**
     * 页面大小
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 下个页面
     */
    public static final String NEXT_PAGE = "nextPage";

    /**
     * 是否有下一页
     */
    public static final String HAS_NEXT_PAGE = "hasNextPage";

    /**
     * 信息表
     */
    private Map<String, Object> infoMap = new HashMap<String, Object>();

    /**
     * 结果列表
     */
    private List<Object> resultList;

    public CommonJsonResult(int code, String message) {
        setInfoMap(STATUS_CODE, "" + code);
        setInfoMap(REASON, message);
    }

    public CommonJsonResult() {
        setSuccess();
    }

    public void setInfoMap(String key, Object value) {
        this.infoMap.put(key, value);
    }

    public void setInfoMap(Map<String, Object> infoMap) {
        this.infoMap.putAll(infoMap);
    }

    public void addResultList(Object obj) {
        if (resultList == null) {
            resultList = new ArrayList<Object>();
        }
        this.resultList.add(obj);
    }

    public void setResultList(List<? extends Object> list) {
        if (resultList == null) {
            resultList = new ArrayList<Object>();
        }

        this.resultList.addAll(list);
    }

    public void setSuccess(String reason) {
        setInfoMap(STATUS_CODE, "200");
        setInfoMap(REASON, reason);
    }

    public void setSuccess() {
        setSuccess(StatusCode.SUCCESS);
    }

    public void setFailure(int code, String reason) {
        setInfoMap(STATUS_CODE, code+"");
        setInfoMap(REASON, reason);
    }

    public void setFailure(String reason) {
        setInfoMap(STATUS_CODE, "500");
        setInfoMap(REASON, reason);
    }

    public void setFailure() {
        setFailure(StatusCode.FAIL);
    }

    public void setPaging(long totalRecords, int pageSize, int nextPage) {
        setInfoMap(TOTAL_RECORDS, totalRecords);
        setInfoMap(PAGE_SIZE, pageSize);
        setInfoMap(NEXT_PAGE, nextPage);
        setInfoMap(HAS_NEXT_PAGE, nextPage > 0 ? true : false);
    }

    public void setPaging(long totalRecords, int pageSize, int nextPage, boolean hasNextPage) {
        setInfoMap(TOTAL_RECORDS, totalRecords);
        setInfoMap(PAGE_SIZE, pageSize);
        setInfoMap(NEXT_PAGE, nextPage);
        setInfoMap(HAS_NEXT_PAGE, hasNextPage);
    }

    public Map<String, Object> getInfoMap() {
        return infoMap;
    }

    public List<Object> getResultList() {
        return resultList;
    }

    public void setPage(Page<?> page) {
        setInfoMap(TOTAL_RECORDS, page.getTotal());
        setInfoMap(PAGE_SIZE, page.getLength());
        boolean hasNextPage = page.getPage_num() * page.getLength() < page.getTotal();
        if (hasNextPage) {
            setInfoMap(NEXT_PAGE, page.getPage_num() + 1);
        }
        setInfoMap(HAS_NEXT_PAGE, hasNextPage);
    }

}
