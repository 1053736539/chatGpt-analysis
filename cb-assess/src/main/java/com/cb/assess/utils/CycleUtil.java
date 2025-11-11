package com.cb.assess.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.cb.common.utils.StringUtils;

public class CycleUtil {

    public static String parseCycle(String cycle, String value) {
        String text = null;
        switch (cycle) {
            case "1":
                // case 1的代码块
                text = value.replace('-', '年') + "月";
                break;
            case "2":
                text = showQuarterText(value);
                break;
            case "3":
                text = value + "年";
                break;
            default:
                text = value;
        }
        return text;
    }

    public static String showQuarterText(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }
        DateTime date = DateUtil.parse(value, "yyyy-MM-dd");
        String quarterText = "";
        int quarter = date.quarter();
        if (quarter == 1) {
            quarterText = "第一季度";
        } else if (quarter == 2) {
            quarterText = "第二季度";
        } else if (quarter == 3) {
            quarterText = "第三季度";
        } else {
            quarterText = "第四季度";
        }
        return String.format("%s年%s", date.year(), quarterText);
    }
}
