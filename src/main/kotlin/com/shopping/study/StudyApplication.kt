package com.shopping.study

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
@EnableAspectJAutoProxy
class StudyApplication

fun main(args: Array<String>) {
    runApplication<StudyApplication>(*args)
}
