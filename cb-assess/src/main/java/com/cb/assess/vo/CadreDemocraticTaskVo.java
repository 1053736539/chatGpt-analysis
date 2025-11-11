package com.cb.assess.vo;

import com.cb.assess.domain.BizAssessCadreDemocraticRecord;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Description:
 * @Author ARPHS
 * @Date 2023/11/25 14:52
 */
public interface CadreDemocraticTaskVo {

    /**
     * 创建请求参数
     */
    @Data
    class CreateReq{
        @NotBlank(message = "评议年度不能为空")
        private String year;//年度
        @NotEmpty(message = "被评价人不能为空")
        private List<Long> assessedUserIds;//被评价人ids
        @NotEmpty(message = "评价人不能为空")
        private List<Long> voteUserIds;//评价人ids
        private String remark;//备注
    }

    @Data
    class assessedUserInfo{

        private Long userId;//被评价人userId
        private String userName;//被评价人名称
        private String userWorkPost;//被评价人职务

        private Long overallCount1;//整体评价 1-优秀数量
        private Long overallCount2;//整体评价 2-称职数量
        private Long overallCount3;//整体评价 3-基本称职数量
        private Long overallCount4;//整体评价 4-不称职数量

        private Long deCount1;//德的评价 1-优秀数量
        private Long deCount2;//德的评价 2-良好数量
        private Long deCount3;//德的评价 3-一般数量
        private Long deCount4;//德的评价 4-较差数量

        private Long nengCount1;//能的评价 1-优秀数量
        private Long nengCount2;//能的评价 2-良好数量
        private Long nengCount3;//能的评价 3-一般数量
        private Long nengCount4;//能的评价 4-较差数量

        private Long qinCount1;//勤的评价 1-优秀数量
        private Long qinCount2;//勤的评价 2-良好数量
        private Long qinCount3;//勤的评价 3-一般数量
        private Long qinCount4;//勤的评价 4-较差数量

        private Long jiCount1;//绩的评价 1-优秀数量
        private Long jiCount2;//绩的评价 2-良好数量
        private Long jiCount3;//绩的评价 3-一般数量
        private Long jiCount4;//绩的评价 4-较差数量

        private Long lianCount1;//廉的评价 1-优秀数量
        private Long lianCount2;//廉的评价 2-良好数量
        private Long lianCount3;//廉的评价 3-一般数量
        private Long lianCount4;//廉的评价 4-较差数量

        List<BizAssessCadreDemocraticRecord> recordList;

    }

}
