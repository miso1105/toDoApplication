package com.teamsparta.todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy


@EnableAspectJAutoProxy
@SpringBootApplication
class ToDoApplication

fun main(args: Array<String>) {
    runApplication<ToDoApplication>(*args)
}
