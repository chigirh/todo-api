package chigirh.app.todo.be.todoapi.web.grpc.validator

import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.constraints.NotEmpty
import kotlin.reflect.KClass

@kotlin.annotation.Target(
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.FIELD,
)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@kotlin.annotation.MustBeDocumented
@Constraint(validatedBy = [])
@NotEmpty(message = "{javax.validation.constraints.NotNull.message}")
annotation class GrpcNotNullString(
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
