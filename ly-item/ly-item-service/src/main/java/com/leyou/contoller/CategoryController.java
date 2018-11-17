package com.leyou.contoller;

import com.leyou.pojo.Category;
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
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public ResponseEntity<List<Category>> queryByParentId(@RequestParam(value = "pid", defaultValue = "0") Long pid) {

        List<Category> categories = categoryService.queryByParentId(pid);
        if (categories != null && categories.size() > 0) {
            return ResponseEntity.ok(categories);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("bid/{bid}")
    public ResponseEntity< List<Category>> queryByBrandId(@PathVariable("bid") Long bid) {
        List<Category> categories = categoryService.queryByBrandId(bid);
        if (categories==null || categories.size()<1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        System.out.println("categories = " + categories);
        return ResponseEntity.ok(categories);

    }

}
