package chigirh.app.todo.be.todoapi.web.conf

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
@Aspect
class ControllerLogInterceptor {
    @Before("within(chigirh.app.todo.be.todoapi.oas3.controller.*Controller)")
    fun controllerStartLog(joinPoint: JoinPoint) {
        val string = joinPoint.toString()
        val args: String = Arrays.toString(joinPoint.args)
        logger.info("Start {}, args: {}", string, args)
    }

    @AfterReturning("within(chigirh.app.todo.be.todoapi.oas3.controller.*Controller)")
    fun controllerEndLog(joinPoint: JoinPoint) {
        val string = joinPoint.toString()
        val args: String = Arrays.toString(joinPoint.args)
        logger.info("End {}, args: {}", string, args)
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ControllerLogInterceptor::class.java)
    }

}