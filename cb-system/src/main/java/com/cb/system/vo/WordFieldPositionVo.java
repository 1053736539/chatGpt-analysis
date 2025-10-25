package com.cb.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description:
 * @Author: ARPHS
 * @Date: 2025-01-16 14:51
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
public class WordFieldPositionVo {

    private String fieldName;
    private String label;
    private int rowIndex;
    private int colIndex;

    public static List<WordFieldPositionVo> of(String[] fieldPositionField){
        List<WordFieldPositionVo> rst = new LinkedList<>();
        for (int i = 0; i < fieldPositionField.length; i++) {
            String[] split = fieldPositionField[i].split(":");
            rst.add(new WordFieldPositionVo(split[0], split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3])));
        }
        return rst;
    }

}
