package com.cb.vo;

import lombok.Data;

/**
 * Es基础对象
 * @author lsj
 * @date 2021/11/8 13:57
 */
@Data
public class EsBaseData {
    //索引
    public String indexName;
    //id
    public String esId;
}