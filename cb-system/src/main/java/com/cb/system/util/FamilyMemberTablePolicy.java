package com.cb.system.util;

import com.cb.system.vo.WordUserVo;
import com.deepoove.poi.data.CellRenderData;
import com.deepoove.poi.data.ParagraphRenderData;
import com.deepoove.poi.data.Paragraphs;
import com.deepoove.poi.data.Texts;
import com.deepoove.poi.data.style.CellStyle;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

/**
 * @Description:
 * @Author: ARPHS
 * @Date: 2025-02-13 14:16
 * @Version: 1.0
 **/
public class FamilyMemberTablePolicy extends DynamicTableRenderPolicy {

    //起始行号
    private int beginRowIndex = 4;

    @Override
    public void render(XWPFTable table, Object data) throws Exception {
        if (null == data) return;
        List<WordUserVo.FamilyMemberInfo> familyMemberInfoList = (List<WordUserVo.FamilyMemberInfo>) data;
        for (int i = 0; i < familyMemberInfoList.size(); i++) {
            WordUserVo.FamilyMemberInfo familyMemberInfo = familyMemberInfoList.get(i);
            int index = beginRowIndex + i;
            XWPFTableRow row;
            if(i < 7){
                row = table.getRow(index);
            } else {
                row = table.insertNewTableRow(beginRowIndex + i);
            }

            for (int j = 1; j < 6; j++) {
                XWPFTableCell cell = row.getCell(j);
                String text = "";
                if(j == 1){
                    text = familyMemberInfo.getRelation();
                } else if(j == 2){
                    text = familyMemberInfo.getName();
                } else if(j == 3){
                    text = familyMemberInfo.getAge();
                } else if(j == 4){
                    text = familyMemberInfo.getPoliticStatus();
                } else if(j == 5){
                    text = familyMemberInfo.getWorkDeptAndPosition();
                }
                //设置表格字体大小
                Style style = Style.builder().buildFontSize(12).build();
                ParagraphRenderData paragraphRenderData = Paragraphs.of(Texts.of(text).style(style).create()).center().create();
                CellRenderData cellData = new CellRenderData();
                cellData.addParagraph(paragraphRenderData);
                CellStyle cellStyle = new CellStyle();
                cellStyle.setDefaultParagraphStyle(paragraphRenderData.getParagraphStyle());
                cellData.setCellStyle(cellStyle);
                TableRenderPolicy.Helper.renderCell(cell, cellData, cellStyle);
            }

        }
    }

}
