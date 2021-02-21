package chigirh.app.todo.be.sampleapi.web.grpc.validator

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@kotlin.annotation.Target(
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.FIELD,
    AnnotationTarget.CLASS,
)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@kotlin.annotation.MustBeDocumented
@Constraint(validatedBy = [GrpcStringRepeatedPatternValidator::class])
annotation class GrpcStringRepeatedPattern(
    val message: String = "{javax.validation.constraints.Pattern.message}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val regexp: String
)
