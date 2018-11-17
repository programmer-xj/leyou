package com.leyou.mapper;

import com.leyou.pojo.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @auther:XUJIAN
 * @date=${DATA}20:30
 */
public interface CategoryMapper extends Mapper<Category>, SelectByIdListMapper<Category,Long> {
    @Select("select * from tb_category where id in(select category_id from tb_category_brand where brand_id = #{bid})")
    List<Category> queryByBrandId(@Param("bid") Long bid);
}
