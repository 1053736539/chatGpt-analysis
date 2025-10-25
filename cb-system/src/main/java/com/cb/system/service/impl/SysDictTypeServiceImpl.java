package com.cb.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import com.alibaba.fastjson.JSONArray;
import com.cb.common.core.domain.vo.DictDataVo;
import com.cb.system.mapper.SysDictDataMapper;
import com.cb.system.mapper.SysDictTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cb.common.constant.UserConstants;
import com.cb.common.core.domain.entity.SysDictData;
import com.cb.common.core.domain.entity.SysDictType;
import com.cb.common.exception.CustomException;
import com.cb.common.utils.DictUtils;
import com.cb.common.utils.StringUtils;
import com.cb.system.service.ISysDictTypeService;

/**
 * 字典 业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService
{
    @Autowired
    private SysDictTypeMapper dictTypeMapper;

    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 项目启动时，初始化字典到缓存
     */
    @PostConstruct
    public void init()
    {
        List<SysDictType> dictTypeList = dictTypeMapper.selectDictTypeAll();
        for (SysDictType dictType : dictTypeList)
        {
            List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(dictType.getDictType());
            DictUtils.setDictCache(dictType.getDictType(), dictDatas);
        }
    }

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType)
    {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeAll()
    {
        return dictTypeMapper.selectDictTypeAll();
    }

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType)
    {
        List<SysDictData> dictDatas = DictUtils.getDictCache(dictType);
        if (StringUtils.isNotEmpty(dictDatas))
        {
            return dictDatas;
        }
        dictDatas = dictDataMapper.selectDictDataByType(dictType);
        if (StringUtils.isNotEmpty(dictDatas))
        {
            DictUtils.setDictCache(dictType, dictDatas);
            return dictDatas;
        }
        return null;
    }

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Override
    public SysDictType selectDictTypeById(Long dictId)
    {
        return dictTypeMapper.selectDictTypeById(dictId);
    }

    /**
     * 根据字典类型查询信息
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    @Override
    public SysDictType selectDictTypeByType(String dictType)
    {
        return dictTypeMapper.selectDictTypeByType(dictType);
    }

    /**
     * 批量删除字典类型信息
     *
     * @param dictIds 需要删除的字典ID
     * @return 结果
     */
    @Override
    public int deleteDictTypeByIds(Long[] dictIds)
    {
        for (Long dictId : dictIds)
        {
            SysDictType dictType = selectDictTypeById(dictId);
            if (dictDataMapper.countDictDataByType(dictType.getDictType()) > 0)
            {
                throw new CustomException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
        }
        int count = dictTypeMapper.deleteDictTypeByIds(dictIds);
        if (count > 0)
        {
            DictUtils.clearDictCache();
        }
        return count;
    }

    /**
     * 清空缓存数据
     */
    @Override
    public void clearCache()
    {
        DictUtils.clearDictCache();
    }

    /**
     * 新增保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int insertDictType(SysDictType dictType)
    {
        int row = dictTypeMapper.insertDictType(dictType);
        if (row > 0)
        {
            DictUtils.clearDictCache();
        }
        return row;
    }

    /**
     * 修改保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateDictType(SysDictType dictType)
    {
        SysDictType oldDict = dictTypeMapper.selectDictTypeById(dictType.getDictId());
        dictDataMapper.updateDictDataType(oldDict.getDictType(), dictType.getDictType());
        int row = dictTypeMapper.updateDictType(dictType);
        if (row > 0)
        {
            DictUtils.clearDictCache();
        }
        return row;
    }

    /**
     * 校验字典类型称是否唯一
     *
     * @param dict 字典类型
     * @return 结果
     */
    @Override
    public String checkDictTypeUnique(SysDictType dict)
    {
        Long dictId = StringUtils.isNull(dict.getDictId()) ? -1L : dict.getDictId();
        SysDictType dictType = dictTypeMapper.checkDictTypeUnique(dict.getDictType());
        if (StringUtils.isNotNull(dictType) && dictType.getDictId().longValue() != dictId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public List<DictDataVo> selectDictDataTreeByType(String dictType) {
        List<SysDictData> dictDatas = DictUtils.getDictCache(dictType);
        if(StringUtils.isEmpty(dictDatas)){
            dictDatas =  dictDataMapper.selectDictDataByType(dictType);
            DictUtils.setDictCache(dictType, dictDatas);
        }
        List<DictDataVo> collect = dictDatas.stream().map(item -> {
            DictDataVo vo = new DictDataVo();
            vo.setId(item.getDictCode());
            vo.setpId(item.getParentCode() == null? 0L :item.getParentCode());
            vo.setLabel(item.getDictLabel());
            vo.setValue(item.getDictValue());
            return vo;
        }).collect(Collectors.toList());
        return buildTree(collect, 0L);
    }

    private   List<DictDataVo> buildTree(List<DictDataVo> list, Long pId) {

        return list.stream().filter(vo -> {
            DictDataVo parent = list.stream().filter(item -> item.getId() == vo.getpId()).findFirst().orElse(null);
            if(vo.getpId() != 0 && parent != null){
                vo.setFullLabel(parent.getLabel() + " / " + vo.getLabel());
            }else {
                vo.setFullLabel(vo.getLabel());
            }
            return vo.getpId().equals(pId);
        }).map(vo -> {
            List<DictDataVo> childList = buildTree(list, vo.getId());
            if (childList != null && childList.size() > 0) {
                vo.setChildren(childList);
            }
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public Map<String, SysDictData> selectDictMapByLabel(String dictType) {
        List<SysDictData> dataList = selectDictDataByType(dictType);
        Map<String, SysDictData> dataMap = new HashMap<>();
        for(SysDictData data : dataList) {
            dataMap.put(data.getDictLabel(), data);
        }
        return dataMap;
    }

    @Override
    public Map<String, String> selectDictDataMapByType(String dictType) {
        List<SysDictData> dictDatas = DictUtils.getDictCache(dictType);
        if(StringUtils.isEmpty(dictDatas)){
            dictDatas = dictDataMapper.selectDictDataByType(dictType);
        }
        if (StringUtils.isNotEmpty(dictDatas)) {
            DictUtils.setDictCache(dictType, dictDatas);
        }
        Map<String, String> map = dictDatas.stream()
                .collect(Collectors.toMap(item -> item.getDictValue(), item -> item.getDictLabel()));
        return map;
    }
}
