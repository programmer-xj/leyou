package com.leyou.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leyou.mapper.BrandMapper;
import com.leyou.pojo.Brand;
import com.leyou.pojo.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @auther:XUJIAN
 * @date=${DATA}0:35
 */
@Service
public class BrandService {
    @Autowired
    private BrandMapper brandMapper;

    public PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String pageBy, Boolean desc, String key) {

        PageHelper.startPage(page, rows);
        Example example = new Example(Brand.class);
        if (StringUtils.isNotEmpty(key)) {
            example.createCriteria().andLike("name", "%" + key + "%").orEqualTo("letter", key.toUpperCase());

        }
        if (StringUtils.isNotBlank(pageBy)) {
            // 排序
            String orderByClause = pageBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        Page<Brand> brands = (Page<Brand>) brandMapper.selectByExample(example);
        return new PageResult<>(brands.getTotal(), new Long(brands.getPages()), brands.getResult());
    }

    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        brandMapper.insertSelective(brand);
        for (Long cid : cids) {
            brandMapper.insertCategoryBrand(cid, brand.getId());
        }
    }

    /**
     * 获取bnames
     * @param brandId
     * @return
     */
    public String queryNamesById(Long brandId) {
        Brand brand = brandMapper.selectByPrimaryKey(brandId);
        return brand.getName();
    }

    /**
     * 通过分类cid查询品牌
     * @param cid
     * @return
     */
    public List<Brand> queryBrandByCategory(Long cid) {

        List<Brand> brandList =  brandMapper.queryBrandByCategory(cid);
        return brandList;
    }
}
