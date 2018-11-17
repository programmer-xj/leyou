package com.leyou.service;

import com.leyou.mapper.CategoryMapper;
import com.leyou.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther:XUJIAN
 * @date=${DATA}20:23
 */
@Service
public class CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    public List<Category> queryByParentId(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        return categoryMapper.select(category);
    }

    public List<Category> queryByBrandId(Long bid) {

      return categoryMapper.queryByBrandId(bid);

    }

    /**
     * 继承selectByIdlistMapper<T,K>
     * @param asList
     * @return
     */
    public  List<String> queryNamesByIds(List<Long> asList) {
        List<Category> categories = categoryMapper.selectByIdList(asList);
        List<String> cnames = new ArrayList<>();
        categories.forEach(category -> cnames.add(category.getName()));
        return cnames;
    }
}
