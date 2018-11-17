package com.leyou.contoller;

import com.leyou.pojo.Brand;
import com.leyou.pojo.Category;
import com.leyou.pojo.PageResult;
import com.leyou.service.BrandService;
import com.leyou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther:XUJIAN
 * @date=${DATA}20:20
 */
@RestController
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "pageBy", required = false) String pageBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key
    ) {
        PageResult<Brand> result = brandService.queryBrandByPage(page, rows, pageBy, desc, key);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        try {
            brandService.saveBrand(brand, cids);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCategory(@PathVariable("cid") Long cid) {
        List<Brand> brandList = brandService.queryBrandByCategory(cid);
        if (brandList != null && brandList.size() > 0) {
            return ResponseEntity.ok(brandList);

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
