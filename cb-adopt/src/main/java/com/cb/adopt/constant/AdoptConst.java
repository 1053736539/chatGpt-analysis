package com.cb.adopt.constant;

import cn.hutool.core.map.MapUtil;

import java.util.Map;
import java.util.stream.Collectors;

public interface AdoptConst {

    // 采用级别的字典类型
    String LEVEL_DICT_TYPE = "adopt_level";
    // 不同采用级别对应的载体字典类型的Map
    Map<String, String> LEVEL_CARRIER_DICT_TYPE_MAP = MapUtil.of(
                    new Object[][]{
                            {"中央纪委国家监委", "adopt_carrier_central"},
                            {"省委办公厅", "adopt_carrier_swbgt"},
                            {"省纪委监委", "adopt_carrier_sjwjw"},
                            {"市委办公室", "adopt_carrier_swbgs"},
                            {"市纪委监委", "adopt_carrier_shijwjw"},
                            {"领导批示", "adopt_carrier_ldps"}
                    }
            ).entrySet().stream()
            .collect(Collectors.toMap(
                    e -> (String) e.getKey(),
                    e -> (String) e.getValue()
            ));

}
