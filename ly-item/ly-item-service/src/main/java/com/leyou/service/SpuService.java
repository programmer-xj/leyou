package com.leyou.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leyou.mapper.SpuMapper;
import com.leyou.pojo.PageResult;
import com.leyou.pojo.Spu;
import com.leyou.pojo.SpuBo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auther:XUJIAN
 * @date=${DATA}23:04
 */
@Service
public class SpuService {
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;

    public PageResult<SpuBo> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {

        PageHelper.startPage(page, rows);

        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if (saleable != null) {
            criteria.andEqualTo("saleable", saleable);
        }
        if (StringUtils.isNotEmpty(key)) {
            criteria.andLike("title", "%" + key + "%");
        }

        Page<Spu> spuPage = (Page<Spu>) spuMapper.selectByExample(example);
        //ToDo 将spu转成spuBo

        ArrayList<SpuBo> list = new ArrayList<>();


        spuPage.getResult().forEach(spu -> {
            SpuBo spuBo = new SpuBo();
            BeanUtils.copyProperties(spu, spuBo);
            List<String> cnames = categoryService.queryNamesByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            //将字符串数组转换成字符串存入spuBocname中
            spuBo.setCname(StringUtils.join(cnames, "/"));
            //获取bname
            String bname = brandService.queryNamesById(spu.getBrandId());
            //将bname存入spuBo中
            spuBo.setBname(bname);
            //将对象存入集合中
            list.add(spuBo);
        });

        return new PageResult<SpuBo>(spuPage.getTotal(), new Long(spuPage.getPages()), list);

    }
}
