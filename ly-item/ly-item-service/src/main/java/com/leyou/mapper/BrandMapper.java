package com.leyou.mapper;

import com.leyou.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @auther:XUJIAN
 * @date=${DATA}20:30
 */
public interface BrandMapper extends Mapper<Brand> {
    @Insert("insert into tb_category_brand values(#{cid},#{bid})")
    void insertCategoryBrand(@Param("cid") Long cid, @Param("bid") Long bid);

    @Select("select b.* from tb_category_brand cb left join tb_brand b on cb.brand_id = b.id where cb.category_id = #{cid}")
    List<Brand> queryBrandByCategory(@Param("cid") Long cid);
}
