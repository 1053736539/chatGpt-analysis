package com.cb.docArchive.vo;

import com.cb.common.annotation.Excel;
import com.cb.common.utils.tree.AbstractTreeNode;

import java.io.Serializable;

/**
 * @Description:
 * @Author ARPHS
 * @Date 2023/12/21 15:13
 */
public interface DocArchiveVo {

    class RecordImportVo implements Serializable {

        private static final long serialVersionUID = 1L;

        /** 档号 */
        @Excel(name = "档号")
        private String archiveNo;

        /** 全宗号 */
        @Excel(name = "全宗号")
        private String fondsNo;

        /** 档案门类代码 */
        @Excel(name = "档案门类代码")
        private String archiveKindCode;

        /** 归档年度 */
        @Excel(name = "归档年度")
        private String archiveYear;

        /** 保管期限 */
        @Excel(name = "保管期限")
        private String retentionPeriod;

        /** 机构（问题） */
        @Excel(name = "机构（问题）")
        private String organizationProblem;

        /** 件号 */
        @Excel(name = "件号")
        private String partNo;

        /** 责任者 */
        @Excel(name = "责任者")
        private String responsibility;

        /** 题名 */
        @Excel(name = "题名")
        private String title;

        /** 文件编号 */
        @Excel(name = "文号")
        private String fileNo;

        /** 文件时间 */
        @Excel(name = "文件时间")
        private String fileDate;

        /** 页数 */
        @Excel(name = "页数")
        private Integer filePage;

        /** 密级 */
        @Excel(name = "密级")
        private String secretLevel;

        @Excel(name = "备注")
        private String remark;

        public String getArchiveNo() {
            return archiveNo;
        }

        public void setArchiveNo(String archiveNo) {
            this.archiveNo = archiveNo;
        }

        public String getFondsNo() {
            return fondsNo;
        }

        public void setFondsNo(String fondsNo) {
            this.fondsNo = fondsNo;
        }

        public String getArchiveKindCode() {
            return archiveKindCode;
        }

        public void setArchiveKindCode(String archiveKindCode) {
            this.archiveKindCode = archiveKindCode;
        }

        public String getArchiveYear() {
            return archiveYear;
        }

        public void setArchiveYear(String archiveYear) {
            this.archiveYear = archiveYear;
        }

        public String getRetentionPeriod() {
            return retentionPeriod;
        }

        public void setRetentionPeriod(String retentionPeriod) {
            this.retentionPeriod = retentionPeriod;
        }

        public String getOrganizationProblem() {
            return organizationProblem;
        }

        public void setOrganizationProblem(String organizationProblem) {
            this.organizationProblem = organizationProblem;
        }

        public String getPartNo() {
            return partNo;
        }

        public void setPartNo(String partNo) {
            this.partNo = partNo;
        }

        public String getResponsibility() {
            return responsibility;
        }

        public void setResponsibility(String responsibility) {
            this.responsibility = responsibility;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFileNo() {
            return fileNo;
        }

        public void setFileNo(String fileNo) {
            this.fileNo = fileNo;
        }

        public String getFileDate() {
            return fileDate;
        }

        public void setFileDate(String fileDate) {
            this.fileDate = fileDate;
        }

        public Integer getFilePage() {
            return filePage;
        }

        public void setFilePage(Integer filePage) {
            this.filePage = filePage;
        }

        public String getSecretLevel() {
            return secretLevel;
        }

        public void setSecretLevel(String secretLevel) {
            this.secretLevel = secretLevel;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

    class FolderOrRecordNode extends AbstractTreeNode<FolderOrRecordNode,String> {

        private String label;

        private String id;

        private String parentId;

        private Integer sort;

        private boolean enable;

        private String type;// folder-目录 record-档案记录

        public FolderOrRecordNode() {}

        @Override
        public String getId() {
            return id;
        }

        @Override
        public String getParentId() {
            return parentId;
        }

        @Override
        public int getSort() {
            if(null == sort){
                return 0;
            }
            return sort;
        }

        @Override
        public boolean isEnable() {
            return enable;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public void setSort(Integer sort) {
            this.sort = sort;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLabel() {
            return label;
        }

        public String getType() {
            return type;
        }
    }

}
