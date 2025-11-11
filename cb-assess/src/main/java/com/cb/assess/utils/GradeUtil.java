package com.cb.assess.utils;

import com.cb.common.utils.StringUtils;

import java.util.HashMap;

public class GradeUtil {

    /**
     *
     * @param type 1 公务员，2事业单位
     * @param grade 等次
     * @return
     */
    public static String  getGrade(String type,String grade){
        HashMap<String, String> civilServant = new HashMap<>();
        civilServant.put("1","优秀");
        civilServant.put("2","称职");
        civilServant.put("3","基本称职");
        civilServant.put("4","不称职");
        civilServant.put("5","不定等次");
        HashMap<String, String> cause = new HashMap<>();
        cause.put("1","优秀");
        cause.put("2","合格");
        cause.put("3","基本合格");
        cause.put("4","不合格");
        cause.put("5","不定等次");
        if ("1".equals(type)){
            return StringUtils.isNotBlank(grade)?civilServant.get(grade):"";
        }else {
            return StringUtils.isNotBlank(grade)?cause.get(grade):"";
        }
    }
}
