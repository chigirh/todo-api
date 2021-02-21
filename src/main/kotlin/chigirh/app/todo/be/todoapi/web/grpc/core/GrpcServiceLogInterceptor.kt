package chigirh.app.todo.be.todoapi.web.grpc.core

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
class GrpcServiceLogInterceptor {
    @Before("within(chigirh.app.todo.be.todoapi.web.grpc.service.*Service)")
    fun controllerStartLog(joinPoint: JoinPoint) {
        val string = joinPoint.toString()
        val args: String = Arrays.toString(joinPoint.args)
        logger.info("[gRPC]Start {}, args: {}", string, args.toString())
    }

    @AfterReturning("within(chigirh.app.todo.be.todoapi.web.grpc.service.*Service)")
    fun controllerEndLog(joinPoint: JoinPoint) {
        val string = joinPoint.toString()
        val args: String = Arrays.toString(joinPoint.args)
        logger.info("[gRPC]End {}, args: {}", string, args.toString())
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(GrpcServiceLogInterceptor::class.java)
    }

}