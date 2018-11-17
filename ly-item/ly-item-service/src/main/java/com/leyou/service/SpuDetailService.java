package com.leyou.service;

import com.leyou.mapper.SpuDetailMapper;
import com.leyou.pojo.SpuDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther:XUJIAN
 * @date=${DATA}9:55
 */
@Service
public class SpuDetailService {
    @Autowired
    SpuDetailMapper spuDetailMapper;

    public void insert(SpuDetail spuDetail) {
        spuDetailMapper.insert(spuDetail);
    }

    /**
     * spu详情查询
     * @param id
     * @return
     */
    public SpuDetail querySpuDetailById(Long id) {
      return spuDetailMapper.selectByPrimaryKey(id);
    }
}
