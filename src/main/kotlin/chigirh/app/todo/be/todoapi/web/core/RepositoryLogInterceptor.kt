package chigirh.app.todo.be.todoapi.web.core

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
class RepositoryLogInterceptor {
    @Before("execution(* chigirh.app.todo.be.todoapi.application.repository.*..*Repository.*(..))")
    fun usecaseStartLog(joinPoint: JoinPoint) {
        val string = joinPoint.toString()
        val args: String = Arrays.toString(joinPoint.args)
        logger.info("Start {}, args: {}", string, args.toString())
    }

    @AfterReturning("execution(* chigirh.app.todo.be.todoapi.application.repository.*..*Repository.*(..))")
    fun usecasellerEndLog(joinPoint: JoinPoint) {
        val string = joinPoint.toString()
        val args: String = Arrays.toString(joinPoint.args)
        logger.info("End {}, args: {}", string, args.toString())
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(RepositoryLogInterceptor::class.java)
    }

}