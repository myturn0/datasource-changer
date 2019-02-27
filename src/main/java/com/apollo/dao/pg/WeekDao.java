package com.apollo.dao.pg;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author ：apollo
 * @since ：Created in 2019/2/27
 */
@Mapper
public interface WeekDao {

    @Select("SELECT COUNT(*) FROM t_week")
    Integer getCount();
}
