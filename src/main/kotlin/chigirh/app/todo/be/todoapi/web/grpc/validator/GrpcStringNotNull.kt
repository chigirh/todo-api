package chigirh.app.todo.be.todoapi.web.grpc.validator

import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy
import javax.validation.constraints.NotBlank

@Documented
@Target(ElementType.FIELD, ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@NotBlank
annotation class GrpcStringNotNull()

