package com.panda.demo

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/tvseries")
class TvSeriesController {
    companion object {
        val log: Log = LogFactory.getLog(TvSeriesController.javaClass)
    }

//    @GetMapping
//    fun sayHello(): Map<String,String> {
//        val map = mutableMapOf<String,String>()
//        map.put("message", "hello,world")
//        return map
//    }


//    @GetMapping
//    fun getAll(): List<TvSeriesDto> {
//        if (log.isTraceEnabled) log.trace("getAll")
//        val list = mutableListOf<TvSeriesDto>()
//        val calendar = Calendar.getInstance()
//        calendar.set(2019,(11-1),15)
//        val dto = TvSeriesDto(1,"22",14,calendar.time)
//        val dto2 = TvSeriesDto(2,"234",111,calendar.time)
//        list.add(dto)
//        list.add(dto2)
//        return list
//    }

    @GetMapping("/{id}")
    private fun getOne(@PathVariable id: Int): TvSeriesDto {
        if (log.isWarnEnabled) log.warn("getOne:$id")
        return when(id) {
            100 -> createPoi()
            101 -> createWest()
            else -> throw ResourceNotFoundException()
        }
    }

    private fun createPoi(): TvSeriesDto {
        val calendar = Calendar.getInstance()
        calendar.set(2019, Calendar.SEPTEMBER, 22, 0, 0,0)
        return TvSeriesDto(100, "just for fun", 5, calendar.time)
    }

    private fun createWest(): TvSeriesDto {
        val calendar = Calendar.getInstance()
        calendar.set(2019, Calendar.DECEMBER, 11,0,0,0)
        return TvSeriesDto(101, "since time", 10,calendar.time)
    }
}