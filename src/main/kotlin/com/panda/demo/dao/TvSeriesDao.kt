package com.panda.demo.dao

import com.panda.demo.pojo.TvSeriesDto
import org.apache.ibatis.annotations.Select

interface TvSeriesDao {
    @Select("select * from tv_series")
    fun getAll(): List<TvSeriesDto>
}