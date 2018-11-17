package com.leyou.service;

import com.leyou.mapper.SpecMapper;
import com.leyou.mapper.SpecParamMapper;
import com.leyou.pojo.SpecGroup;
import com.leyou.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther:XUJIAN
 * @date=${DATA}21:39
 */
@Service
public class SpecSeervice {
    @Autowired private SpecMapper specMapper;
    @Autowired private SpecParamMapper specParamMapper;

    /**
     * 查询规格列表
     * @param id
     * @return
     */
    public List<SpecGroup> querySpecGroups(Long id) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(id);
        List<SpecGroup> groupList = specMapper.select(specGroup);
        return groupList;
    }

    /**
     * 查询规格参数
     * @param gid
     * @param cid
     * @param searching
     * @param generic
     * @return
     */
    public List<SpecParam> querySpecParam(Long gid,Long cid,Boolean searching,Boolean generic) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        specParam.setCid(cid);
        specParam.setSearching(searching);
        specParam.setGeneric(generic);
        return specParamMapper.select(specParam);

    }
}
