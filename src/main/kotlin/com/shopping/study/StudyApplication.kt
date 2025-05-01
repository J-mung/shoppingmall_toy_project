package com.shopping.study

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class StudyApplication

fun main(args: Array<String>) {
    runApplication<StudyApplication>(*args)
}
