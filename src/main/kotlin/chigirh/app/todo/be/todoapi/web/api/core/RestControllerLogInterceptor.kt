package chigirh.app.todo.be.todoapi.web.api.core

import java.util.Arrays
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@Aspect
class RestControllerLogInterceptor {
    @Before("within(chigirh.app.todo.be.todoapi.oas3.controller.*Controller)")
    fun controllerStartLog(joinPoint: JoinPoint) {
        val string = joinPoint.toString()
        val args: String = Arrays.toString(joinPoint.args)
        logger.info("[REST-API]Start {}, args: {}", string, args.toString())
    }

    @AfterReturning("within(chigirh.app.todo.be.todoapi.oas3.controller.*Controller)")
    fun controllerEndLog(joinPoint: JoinPoint) {
        val string = joinPoint.toString()
        val args: String = Arrays.toString(joinPoint.args)
        logger.info("[REST-API]End {}, args: {}", string, args.toString())
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(RestControllerLogInterceptor::class.java)
    }

}