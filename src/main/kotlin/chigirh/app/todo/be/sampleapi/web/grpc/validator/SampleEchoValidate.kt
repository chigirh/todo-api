package chigirh.app.todo.be.sampleapi.web.grpc.validator

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@kotlin.annotation.Target(
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.FIELD,
)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@kotlin.annotation.MustBeDocumented
@Constraint(validatedBy = [])
@GrpcStringRepeatedSize(min = 3, max = 10)
@GrpcStringRepeatedPattern(regexp = "^[0-9].*$")
annotation class SampleEchoValidate(
    val message: String = "{javax.validation.constraints.Pattern.message}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)
