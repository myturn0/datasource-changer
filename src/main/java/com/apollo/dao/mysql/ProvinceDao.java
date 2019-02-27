package com.apollo.dao.mysql;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author ：apollo
 * @since ：Created in 2019/2/27
 */
@Mapper
public interface ProvinceDao {

    @Select("SELECT COUNT(*) FROM t_province")
    Integer getCount();
}
