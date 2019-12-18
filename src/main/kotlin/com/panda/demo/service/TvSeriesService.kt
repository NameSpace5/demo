package com.panda.demo.service

import com.panda.demo.pojo.TvSeriesDto
import com.panda.demo.dao.TvSeriesDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.stereotype.Component

@Service @Component
class TvSeriesService {

    @Autowired lateinit var tvSeriesDao: TvSeriesDao

    fun getAllTvSeries(): List<TvSeriesDto> {
        return tvSeriesDao.getAll()
    }
}