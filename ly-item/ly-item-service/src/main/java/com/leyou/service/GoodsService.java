package com.leyou.service;

import com.leyou.mapper.GoodsMapper;
import com.leyou.pojo.Sku;
import com.leyou.pojo.SpuBo;
import com.leyou.pojo.SpuDetail;
import com.leyou.pojo.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @auther:XUJIAN
 * @date=${DATA}21:39
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private SpuDetailService spuDetailService;
    @Autowired
    private StockService stockService;
    @Autowired
    private SkuService skuService;
    @Transactional
    public void save(SpuBo spu) {
        //保存spu
        spu.setSaleable(true);
        spu.setValid(true);
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(spu.getCreateTime());
        goodsMapper.insert(spu);
        //保存spu详情
        spu.getSpuDetail().setSpuId(spu.getId());
        spuDetailService.insert(spu.getSpuDetail());
        //保存sku
        List<Sku> skus = spu.getSkus();
        skus.forEach(sku -> {
            sku.setSpuId(spu.getId());
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            skuService.insert(sku);
            //保存库存
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            stockService.insert(stock);
        });
    }

    /**
     * spu详情查询
     * @param id
     * @return
     */
    public SpuDetail querySpuDetailById(Long id) {
       return spuDetailService.querySpuDetailById(id);
    }
}
