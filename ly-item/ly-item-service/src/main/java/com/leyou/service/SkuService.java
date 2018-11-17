package com.leyou.service;

import com.leyou.mapper.SkuMapper;
import com.leyou.pojo.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther:XUJIAN
 * @date=${DATA}10:17
 */
@Service
public class SkuService {
    @Autowired
    SkuMapper skuMapper;
    @Autowired
    StockService stockService;

    public void insert(Sku sku) {
        skuMapper.insert(sku);
    }

    /**
     * @param spuId
     * @return
     */
    public List<Sku> querySkuBySpuId(Long spuId) {
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        List<Sku> skuList = skuMapper.select(sku);
        for (Sku sku1 : skuList) {
            int stock = stockService.queryStockBySkuId(sku1.getId());
            sku1.setStock(stock);
        }
        return skuList;
    }
}
