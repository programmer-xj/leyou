package com.leyou.contoller;

import com.leyou.pojo.*;
import com.leyou.service.GoodsService;
import com.leyou.service.SkuService;
import com.leyou.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther:XUJIAN
 * @date=${DATA}22:58
 */
@RestController
public class GoodsController {

    @Autowired
    private SpuService spuService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    private SkuService skuService;

    @GetMapping("spu/page")
    public ResponseEntity<PageResult<SpuBo>> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable", required = false) Boolean saleable,
            @RequestParam(value = "key", required = false) String key
    ) {

        PageResult<SpuBo> spuPageResult = spuService.querySpuByPage(page, rows, saleable, key);
        if (spuPageResult != null && spuPageResult.getItems().size() > 0) {
            return ResponseEntity.ok(spuPageResult);

        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody SpuBo spu) {
        try {
            goodsService.save(spu);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("spu/detail/{id}")
    public ResponseEntity<SpuDetail> querySpuDetailById(@PathVariable("id") Long id) {
        SpuDetail spuDetail = goodsService.querySpuDetailById(id);
        if (spuDetail != null) {
            return ResponseEntity.ok(spuDetail);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkuBySpuId(@RequestParam("id") Long spuId) {
        List<Sku> skuList = skuService.querySkuBySpuId(spuId);
        if (skuList != null && skuList.size()>0) {
            return ResponseEntity.ok(skuList);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
