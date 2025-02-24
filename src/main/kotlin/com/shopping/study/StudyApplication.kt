package com.shopping.study

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude= [DataSourceAutoConfiguration::class])
class StudyApplication

fun main(args: Array<String>) {
    runApplication<StudyApplication>(*args)
}
