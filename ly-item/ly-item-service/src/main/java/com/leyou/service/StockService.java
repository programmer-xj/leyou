package com.leyou.service;

import com.leyou.mapper.StockMapper;
import com.leyou.pojo.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther:XUJIAN
 * @date=${DATA}9:59
 */
@Service
public class StockService {
@Autowired private StockMapper stockMapper;
    public void insert(Stock stock) {
        stockMapper.insert(stock);
    }

    public int queryStockBySkuId(Long id) {

       return stockMapper.selectByPrimaryKey(id).getStock();

    }
}
