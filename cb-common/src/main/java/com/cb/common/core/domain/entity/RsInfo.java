package com.cb.common.core.domain.entity;

import com.cb.common.annotation.Excel;
import com.cb.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 档案系统用户信息对象 RS_INFO
 * 
 * @author ruoyi
 * @date 2025-02-26
 */
public class RsInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主建ID */
    @Excel (name = "序号", cellType = Excel.ColumnType.NUMERIC, prompt = "用户序号")
    private Integer id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Integer rsid;

    /** 关联用户ID */
    @Excel(name = "关联用户ID")
    private Integer userId;

    /** 档案 */
    @Excel(name = "档案")
    private String ar;

    /** 部门 */
    @Excel(name = "部门")
    private String bm;

    /** 出生年月 */
    @Excel(name = "出生年月")
    private String csny;

    /** 当前照片 */
    @Excel(name = "当前照片")
    private String dqzp;

    /** 调查单位 */
    @Excel(name = "调查单位")
    private String dcdw;

    /** 调查时间 */
    @Excel(name = "调查时间")
    private String dcsj;

    /** 调查原因 */
    @Excel(name = "调查原因")
    private String dcyy;

    /** 登记单位 */
    @Excel(name = "登记单位")
    private String djdw;

    /** 登记时间 */
    @Excel(name = "登记时间")
    private String djsj;

    /** 登记原因 */
    @Excel(name = "登记原因")
    private String djyy;

    /** 分数 */
    @Excel(name = "分数")
    private String fs;

    /** 核算规则 */
    @Excel(name = "核算规则")
    private String hsgz;

    /** 核算技术 */
    @Excel(name = "核算技术")
    private String hsjs;

    /** 籍贯 */
    @Excel(name = "籍贯")
    private String jg;

    /** 加入时间 */
    @Excel(name = "加入时间")
    private String jrsj;

    /** 简历 */
    @Excel(name = "简历")
    private String jl;

    /** 名族 */
    @Excel(name = "名族")
    private String mz;

    /** 评判时间 */
    @Excel(name = "评判时间")
    private String ppsj;

    /** 人员类别 */
    @Excel(name = "人员类别")
    private String rylb;

    /** 人员编号 */
    @Excel(name = "人员编号")
    private String rybh;

    /** 任免时间 */
    @Excel(name = "任免时间")
    private String rmsj;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String sfzh;

    /** 通知时间 */
    @Excel(name = "通知时间")
    private String tzsj;

    /** 通知原因 */
    @Excel(name = "通知原因")
    private String tzyy;

    /** 未验证 */
    @Excel(name = "未验证")
    private String wyz;

    /** 文化程度 */
    @Excel(name = "文化程度")
    private String whcd;

    /** 性别 */
    @Excel(name = "性别")
    private String xb;

    /** 姓名 */
    @Excel(name = "姓名")
    private String xm;

    /** 员工现状 */
    @Excel(name = "员工现状")
    private String ygxz;

    /** 政治面貌 */
    @Excel(name = "政治面貌")
    private String zzmm;

    /** 职务 */
    @Excel(name = "职务")
    private String zw;

    /** 专业职称 */
    @Excel(name = "专业职称")
    private String zyzc;

    /** 职能 */
    @Excel(name = "职能")
    private String zn;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String bmmc;

    /** 阐述 */
    @Excel(name = "阐述")
    private String cs;

    /** 工作岗位 */
    @Excel(name = "工作岗位")
    private String gzgw;

    /** 工作单位 */
    @Excel(name = "工作单位")
    private String gzdw;

    /** 人事复述 */
    @Excel(name = "人事复述")
    private String rsfs;

    /** 顺序号 */
    @Excel(name = "顺序号")
    private Integer sxh;

    /** 姓名拼音 */
    @Excel(name = "姓名拼音")
    private String xmpy;

    /** 起始时间 */
    @Excel(name = "起始时间")
    private String qssj;

    /** 毕业时间 */
    @Excel(name = "毕业时间")
    private String bysj;

    /** 毕业院校专业 */
    @Excel(name = "毕业院校专业")
    private String byyxzy;

    /** 旧的部门 */
    @Excel(name = "旧的部门")
    private Integer oldbm;

    /** 旧的用户ID */
    @Excel(name = "旧的用户ID")
    private Integer oldrsid;

    /** 出生地 */
    @Excel(name = "出生地")
    private String chushengdi;

    /** 工作时间 */
    @Excel(name = "工作时间")
    private String worktime;

    /** 入党时间 */
    @Excel(name = "入党时间")
    private String jointime;

    /** 工作单位 */
    @Excel(name = "工作单位")
    private String jobunit;

    /** 任职时间 */
    @Excel(name = "任职时间")
    private String appointtime;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idcard;

    /** 全日制学历 */
    @Excel(name = "全日制学历")
    private String quanrizixueli;

    /** 全日制学位 */
    @Excel(name = "全日制学位")
    private String quanrizixuewei;

    /** 全日制院校 */
    @Excel(name = "全日制院校")
    private String quanriziyuanxiao;

    /** 全日制专业 */
    @Excel(name = "全日制专业")
    private String quanrizizhuanye;

    /** 在职学历 */
    @Excel(name = "在职学历")
    private String zaizhixueli;

    /** 在职学位 */
    @Excel(name = "在职学位")
    private String zaizhixuewei;

    /** 在职院校 */
    @Excel(name = "在职院校")
    private String zaizhiyuanxiao;

    /** 在职专业 */
    @Excel(name = "在职专业")
    private String zaizhizhuanye;

    /** 情况说明 */
    @Excel(name = "情况说明")
    private String qingkuanshuomi;

    /** 档案整理人 */
    @Excel(name = "档案整理人")
    private String danganzhengliren;

    /** 档案审核人 */
    @Excel(name = "档案审核人")
    private String danganshenheren;

    /** 数字化采集人 */
    @Excel(name = "数字化采集人")
    private String shuzihuacaijiren;

    /** 数字化审核人 */
    @Excel(name = "数字化审核人")
    private String shuzihuashenheren;

    /** 档案卷书 */
    @Excel(name = "档案卷书")
    private String danganjuanshu;

    /** 报送单位 */
    @Excel(name = "报送单位")
    private String baosongdanwei;

    /** 报送日期 */
    @Excel(name = "报送日期")
    private String baosongriqi;

    /** 是否已修改 */
    @Excel(name = "是否已修改")
    private Integer modifyyes;

    /** 人物变动 */
    @Excel(name = "人物变动")
    private String renwubiandong;

    /** 短缺材料 */
    @Excel(name = "短缺材料")
    private String duanquecailiao;

    /** 文件夹数量 */
    @Excel(name = "文件夹数量")
    private Integer archfile;

    /** 状态 */
    @Excel(name = "状态")
    private Integer state;

    /** 开始姓名拼音 */
    @Excel(name = "开始姓名拼音")
    private String strxmpy;

    /** 密级标识 */
    @Excel(name = "密级标识")
    private String mijibiaoshi;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setRsid(Integer rsid) 
    {
        this.rsid = rsid;
    }

    public Integer getRsid() 
    {
        return rsid;
    }
    public void setUserId(Integer userId) 
    {
        this.userId = userId;
    }

    public Integer getUserId() 
    {
        return userId;
    }
    public void setAr(String ar) 
    {
        this.ar = ar;
    }

    public String getAr() 
    {
        return ar;
    }
    public void setBm(String bm) 
    {
        this.bm = bm;
    }

    public String getBm() 
    {
        return bm;
    }
    public void setCsny(String csny) 
    {
        this.csny = csny;
    }

    public String getCsny() 
    {
        return csny;
    }
    public void setDqzp(String dqzp) 
    {
        this.dqzp = dqzp;
    }

    public String getDqzp() 
    {
        return dqzp;
    }
    public void setDcdw(String dcdw) 
    {
        this.dcdw = dcdw;
    }

    public String getDcdw() 
    {
        return dcdw;
    }
    public void setDcsj(String dcsj) 
    {
        this.dcsj = dcsj;
    }

    public String getDcsj() 
    {
        return dcsj;
    }
    public void setDcyy(String dcyy) 
    {
        this.dcyy = dcyy;
    }

    public String getDcyy() 
    {
        return dcyy;
    }
    public void setDjdw(String djdw) 
    {
        this.djdw = djdw;
    }

    public String getDjdw() 
    {
        return djdw;
    }
    public void setDjsj(String djsj) 
    {
        this.djsj = djsj;
    }

    public String getDjsj() 
    {
        return djsj;
    }
    public void setDjyy(String djyy) 
    {
        this.djyy = djyy;
    }

    public String getDjyy() 
    {
        return djyy;
    }
    public void setFs(String fs) 
    {
        this.fs = fs;
    }

    public String getFs() 
    {
        return fs;
    }
    public void setHsgz(String hsgz) 
    {
        this.hsgz = hsgz;
    }

    public String getHsgz() 
    {
        return hsgz;
    }
    public void setHsjs(String hsjs) 
    {
        this.hsjs = hsjs;
    }

    public String getHsjs() 
    {
        return hsjs;
    }
    public void setJg(String jg) 
    {
        this.jg = jg;
    }

    public String getJg() 
    {
        return jg;
    }
    public void setJrsj(String jrsj) 
    {
        this.jrsj = jrsj;
    }

    public String getJrsj() 
    {
        return jrsj;
    }
    public void setJl(String jl) 
    {
        this.jl = jl;
    }

    public String getJl() 
    {
        return jl;
    }
    public void setMz(String mz) 
    {
        this.mz = mz;
    }

    public String getMz() 
    {
        return mz;
    }
    public void setPpsj(String ppsj) 
    {
        this.ppsj = ppsj;
    }

    public String getPpsj() 
    {
        return ppsj;
    }
    public void setRylb(String rylb) 
    {
        this.rylb = rylb;
    }

    public String getRylb() 
    {
        return rylb;
    }
    public void setRybh(String rybh) 
    {
        this.rybh = rybh;
    }

    public String getRybh() 
    {
        return rybh;
    }
    public void setRmsj(String rmsj) 
    {
        this.rmsj = rmsj;
    }

    public String getRmsj() 
    {
        return rmsj;
    }
    public void setSfzh(String sfzh) 
    {
        this.sfzh = sfzh;
    }

    public String getSfzh() 
    {
        return sfzh;
    }
    public void setTzsj(String tzsj) 
    {
        this.tzsj = tzsj;
    }

    public String getTzsj() 
    {
        return tzsj;
    }
    public void setTzyy(String tzyy) 
    {
        this.tzyy = tzyy;
    }

    public String getTzyy() 
    {
        return tzyy;
    }
    public void setWyz(String wyz) 
    {
        this.wyz = wyz;
    }

    public String getWyz() 
    {
        return wyz;
    }
    public void setWhcd(String whcd) 
    {
        this.whcd = whcd;
    }

    public String getWhcd() 
    {
        return whcd;
    }
    public void setXb(String xb) 
    {
        this.xb = xb;
    }

    public String getXb() 
    {
        return xb;
    }
    public void setXm(String xm) 
    {
        this.xm = xm;
    }

    public String getXm() 
    {
        return xm;
    }
    public void setYgxz(String ygxz) 
    {
        this.ygxz = ygxz;
    }

    public String getYgxz() 
    {
        return ygxz;
    }
    public void setZzmm(String zzmm) 
    {
        this.zzmm = zzmm;
    }

    public String getZzmm() 
    {
        return zzmm;
    }
    public void setZw(String zw) 
    {
        this.zw = zw;
    }

    public String getZw() 
    {
        return zw;
    }
    public void setZyzc(String zyzc) 
    {
        this.zyzc = zyzc;
    }

    public String getZyzc() 
    {
        return zyzc;
    }
    public void setZn(String zn) 
    {
        this.zn = zn;
    }

    public String getZn() 
    {
        return zn;
    }
    public void setBmmc(String bmmc) 
    {
        this.bmmc = bmmc;
    }

    public String getBmmc() 
    {
        return bmmc;
    }
    public void setCs(String cs) 
    {
        this.cs = cs;
    }

    public String getCs() 
    {
        return cs;
    }
    public void setGzgw(String gzgw) 
    {
        this.gzgw = gzgw;
    }

    public String getGzgw() 
    {
        return gzgw;
    }
    public void setGzdw(String gzdw) 
    {
        this.gzdw = gzdw;
    }

    public String getGzdw() 
    {
        return gzdw;
    }
    public void setRsfs(String rsfs) 
    {
        this.rsfs = rsfs;
    }

    public String getRsfs() 
    {
        return rsfs;
    }
    public void setSxh(Integer sxh) 
    {
        this.sxh = sxh;
    }

    public Integer getSxh() 
    {
        return sxh;
    }
    public void setXmpy(String xmpy) 
    {
        this.xmpy = xmpy;
    }

    public String getXmpy() 
    {
        return xmpy;
    }
    public void setQssj(String qssj) 
    {
        this.qssj = qssj;
    }

    public String getQssj() 
    {
        return qssj;
    }
    public void setBysj(String bysj) 
    {
        this.bysj = bysj;
    }

    public String getBysj() 
    {
        return bysj;
    }
    public void setByyxzy(String byyxzy) 
    {
        this.byyxzy = byyxzy;
    }

    public String getByyxzy() 
    {
        return byyxzy;
    }
    public void setOldbm(Integer oldbm) 
    {
        this.oldbm = oldbm;
    }

    public Integer getOldbm() 
    {
        return oldbm;
    }
    public void setOldrsid(Integer oldrsid) 
    {
        this.oldrsid = oldrsid;
    }

    public Integer getOldrsid() 
    {
        return oldrsid;
    }
    public void setChushengdi(String chushengdi) 
    {
        this.chushengdi = chushengdi;
    }

    public String getChushengdi() 
    {
        return chushengdi;
    }
    public void setWorktime(String worktime) 
    {
        this.worktime = worktime;
    }

    public String getWorktime() 
    {
        return worktime;
    }
    public void setJointime(String jointime) 
    {
        this.jointime = jointime;
    }

    public String getJointime() 
    {
        return jointime;
    }
    public void setJobunit(String jobunit) 
    {
        this.jobunit = jobunit;
    }

    public String getJobunit() 
    {
        return jobunit;
    }
    public void setAppointtime(String appointtime) 
    {
        this.appointtime = appointtime;
    }

    public String getAppointtime() 
    {
        return appointtime;
    }
    public void setIdcard(String idcard) 
    {
        this.idcard = idcard;
    }

    public String getIdcard() 
    {
        return idcard;
    }
    public void setQuanrizixueli(String quanrizixueli) 
    {
        this.quanrizixueli = quanrizixueli;
    }

    public String getQuanrizixueli() 
    {
        return quanrizixueli;
    }
    public void setQuanrizixuewei(String quanrizixuewei) 
    {
        this.quanrizixuewei = quanrizixuewei;
    }

    public String getQuanrizixuewei() 
    {
        return quanrizixuewei;
    }
    public void setQuanriziyuanxiao(String quanriziyuanxiao) 
    {
        this.quanriziyuanxiao = quanriziyuanxiao;
    }

    public String getQuanriziyuanxiao() 
    {
        return quanriziyuanxiao;
    }
    public void setQuanrizizhuanye(String quanrizizhuanye) 
    {
        this.quanrizizhuanye = quanrizizhuanye;
    }

    public String getQuanrizizhuanye() 
    {
        return quanrizizhuanye;
    }
    public void setZaizhixueli(String zaizhixueli) 
    {
        this.zaizhixueli = zaizhixueli;
    }

    public String getZaizhixueli() 
    {
        return zaizhixueli;
    }
    public void setZaizhixuewei(String zaizhixuewei) 
    {
        this.zaizhixuewei = zaizhixuewei;
    }

    public String getZaizhixuewei() 
    {
        return zaizhixuewei;
    }
    public void setZaizhiyuanxiao(String zaizhiyuanxiao) 
    {
        this.zaizhiyuanxiao = zaizhiyuanxiao;
    }

    public String getZaizhiyuanxiao() 
    {
        return zaizhiyuanxiao;
    }
    public void setZaizhizhuanye(String zaizhizhuanye) 
    {
        this.zaizhizhuanye = zaizhizhuanye;
    }

    public String getZaizhizhuanye() 
    {
        return zaizhizhuanye;
    }
    public void setQingkuanshuomi(String qingkuanshuomi) 
    {
        this.qingkuanshuomi = qingkuanshuomi;
    }

    public String getQingkuanshuomi() 
    {
        return qingkuanshuomi;
    }
    public void setDanganzhengliren(String danganzhengliren) 
    {
        this.danganzhengliren = danganzhengliren;
    }

    public String getDanganzhengliren() 
    {
        return danganzhengliren;
    }
    public void setDanganshenheren(String danganshenheren) 
    {
        this.danganshenheren = danganshenheren;
    }

    public String getDanganshenheren() 
    {
        return danganshenheren;
    }
    public void setShuzihuacaijiren(String shuzihuacaijiren) 
    {
        this.shuzihuacaijiren = shuzihuacaijiren;
    }

    public String getShuzihuacaijiren() 
    {
        return shuzihuacaijiren;
    }
    public void setShuzihuashenheren(String shuzihuashenheren) 
    {
        this.shuzihuashenheren = shuzihuashenheren;
    }

    public String getShuzihuashenheren() 
    {
        return shuzihuashenheren;
    }
    public void setDanganjuanshu(String danganjuanshu) 
    {
        this.danganjuanshu = danganjuanshu;
    }

    public String getDanganjuanshu() 
    {
        return danganjuanshu;
    }
    public void setBaosongdanwei(String baosongdanwei) 
    {
        this.baosongdanwei = baosongdanwei;
    }

    public String getBaosongdanwei() 
    {
        return baosongdanwei;
    }
    public void setBaosongriqi(String baosongriqi) 
    {
        this.baosongriqi = baosongriqi;
    }

    public String getBaosongriqi() 
    {
        return baosongriqi;
    }
    public void setModifyyes(Integer modifyyes) 
    {
        this.modifyyes = modifyyes;
    }

    public Integer getModifyyes() 
    {
        return modifyyes;
    }
    public void setRenwubiandong(String renwubiandong) 
    {
        this.renwubiandong = renwubiandong;
    }

    public String getRenwubiandong() 
    {
        return renwubiandong;
    }
    public void setDuanquecailiao(String duanquecailiao) 
    {
        this.duanquecailiao = duanquecailiao;
    }

    public String getDuanquecailiao() 
    {
        return duanquecailiao;
    }
    public void setArchfile(Integer archfile) 
    {
        this.archfile = archfile;
    }

    public Integer getArchfile() 
    {
        return archfile;
    }
    public void setState(Integer state) 
    {
        this.state = state;
    }

    public Integer getState() 
    {
        return state;
    }
    public void setStrxmpy(String strxmpy) 
    {
        this.strxmpy = strxmpy;
    }

    public String getStrxmpy() 
    {
        return strxmpy;
    }
    public void setMijibiaoshi(String mijibiaoshi) 
    {
        this.mijibiaoshi = mijibiaoshi;
    }

    public String getMijibiaoshi() 
    {
        return mijibiaoshi;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("rsid", getRsid())
            .append("userId", getUserId())
            .append("ar", getAr())
            .append("bm", getBm())
            .append("csny", getCsny())
            .append("dqzp", getDqzp())
            .append("dcdw", getDcdw())
            .append("dcsj", getDcsj())
            .append("dcyy", getDcyy())
            .append("djdw", getDjdw())
            .append("djsj", getDjsj())
            .append("djyy", getDjyy())
            .append("fs", getFs())
            .append("hsgz", getHsgz())
            .append("hsjs", getHsjs())
            .append("jg", getJg())
            .append("jrsj", getJrsj())
            .append("jl", getJl())
            .append("mz", getMz())
            .append("ppsj", getPpsj())
            .append("rylb", getRylb())
            .append("rybh", getRybh())
            .append("rmsj", getRmsj())
            .append("sfzh", getSfzh())
            .append("tzsj", getTzsj())
            .append("tzyy", getTzyy())
            .append("wyz", getWyz())
            .append("whcd", getWhcd())
            .append("xb", getXb())
            .append("xm", getXm())
            .append("ygxz", getYgxz())
            .append("zzmm", getZzmm())
            .append("zw", getZw())
            .append("zyzc", getZyzc())
            .append("zn", getZn())
            .append("bmmc", getBmmc())
            .append("cs", getCs())
            .append("gzgw", getGzgw())
            .append("gzdw", getGzdw())
            .append("rsfs", getRsfs())
            .append("sxh", getSxh())
            .append("xmpy", getXmpy())
            .append("qssj", getQssj())
            .append("bysj", getBysj())
            .append("byyxzy", getByyxzy())
            .append("oldbm", getOldbm())
            .append("oldrsid", getOldrsid())
            .append("chushengdi", getChushengdi())
            .append("worktime", getWorktime())
            .append("jointime", getJointime())
            .append("jobunit", getJobunit())
            .append("appointtime", getAppointtime())
            .append("idcard", getIdcard())
            .append("quanrizixueli", getQuanrizixueli())
            .append("quanrizixuewei", getQuanrizixuewei())
            .append("quanriziyuanxiao", getQuanriziyuanxiao())
            .append("quanrizizhuanye", getQuanrizizhuanye())
            .append("zaizhixueli", getZaizhixueli())
            .append("zaizhixuewei", getZaizhixuewei())
            .append("zaizhiyuanxiao", getZaizhiyuanxiao())
            .append("zaizhizhuanye", getZaizhizhuanye())
            .append("qingkuanshuomi", getQingkuanshuomi())
            .append("danganzhengliren", getDanganzhengliren())
            .append("danganshenheren", getDanganshenheren())
            .append("shuzihuacaijiren", getShuzihuacaijiren())
            .append("shuzihuashenheren", getShuzihuashenheren())
            .append("danganjuanshu", getDanganjuanshu())
            .append("baosongdanwei", getBaosongdanwei())
            .append("baosongriqi", getBaosongriqi())
            .append("modifyyes", getModifyyes())
            .append("renwubiandong", getRenwubiandong())
            .append("duanquecailiao", getDuanquecailiao())
            .append("archfile", getArchfile())
            .append("state", getState())
            .append("strxmpy", getStrxmpy())
            .append("mijibiaoshi", getMijibiaoshi())
            .toString();
    }
}
