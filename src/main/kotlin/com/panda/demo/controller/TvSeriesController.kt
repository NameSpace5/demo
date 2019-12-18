package com.panda.demo.controller

import com.panda.demo.pojo.TvSeriesDto
import com.panda.demo.service.TvSeriesService
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.RuntimeException
import java.util.*
import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import java.io.InputStream
import javax.servlet.http.HttpServletRequest
import kotlin.collections.HashMap

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
    @Autowired
    lateinit var tvSeriesService: TvSeriesService


    @GetMapping
    fun getAll(): List<TvSeriesDto> {
        val list = tvSeriesService.getAllTvSeries()
        if (log.isTraceEnabled) log.trace("getAll 查获到数据条数：" + list.size)
        return list
    }

    @GetMapping("/{id}")
    private fun getOne(@PathVariable id: Int): TvSeriesDto {
        if (log.isTraceEnabled) log.trace("getOne:$id")
        return when(id) {
            100 -> createPoi()
            101 -> createWest()
            else -> throw ResourceNotFoundException()
        }
    }

    @PostMapping
    private fun insertOne(@RequestBody tvSeriesDto: TvSeriesDto): TvSeriesDto {
        if (log.isTraceEnabled) log.trace("putOne:$tvSeriesDto")
        tvSeriesDto.id = 999
        return tvSeriesDto
    }

    @PutMapping("/{id}")
    private fun updateOne(@PathVariable id: Int, @RequestBody tvSeriesDto: TvSeriesDto): TvSeriesDto {
        if (log.isTraceEnabled) log.trace("update$id")
        if (id == 101 || id == 102) {
            return createPoi()
        } else {
            throw ResourceNotFoundException()
        }
    }

    @DeleteMapping("/{id}")
    private fun deleteOne(@PathVariable id: Int, request : HttpServletRequest,
                          @RequestParam(value = "delete_reason",required = false) deleteReason: String): HashMap<String, String> {
        if (log.isTraceEnabled) log.trace("delete :$id")
        val result = hashMapOf<String,String>()
        when(id) {
            101 -> result["message"] = "101被"+request.remoteAddr + "删除，原因："+ deleteReason
            102 -> throw RuntimeException("102 can't delete")
            else -> throw ResourceNotFoundException()
        }
        return result
    }

    @PostMapping("/{id}/photos", MediaType.MULTIPART_FORM_DATA_VALUE)
    private fun addPhoto(@PathVariable id: Int, @RequestParam("photo")imgFile: MultipartFile) {
        if (log.isTraceEnabled) log.trace("addPhoto:" + imgFile.originalFilename)
        val fos = FileOutputStream("target/" + imgFile.originalFilename)
        IOUtils.copy(imgFile.inputStream, fos)
        fos.close()
    }

    @GetMapping(value= ["/{id}/icon"], produces = [MediaType.IMAGE_JPEG_VALUE])
    private fun getImg(@PathVariable id: Int): ByteArray {
        if (log.isTraceEnabled) log.trace("getImg$id")
        val imgFile = "src/main/resources/005.jpg"
        val fis = FileInputStream(imgFile) as InputStream
        return IOUtils.toByteArray(fis)
    }

    private fun createPoi(): TvSeriesDto {
        val calendar = Calendar.getInstance()
        calendar.set(2019, Calendar.SEPTEMBER, 22, 0, 0,0)
        return TvSeriesDto(100, "just for fun", 5, calendar.time)
    }

    private fun createWest(): TvSeriesDto {
        val calendar = Calendar.getInstance()
        calendar.set(2019, Calendar.DECEMBER, 11,0,0,0)
        return TvSeriesDto(101, "since time", 10, calendar.time)
    }
}