package com.panda.demo

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@MapperScan("com.panda.demo.dao")
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}