package chigirh.app.todo.be.todoapi.web.grpc.validator

import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.constraints.Pattern
import kotlin.reflect.KClass

@kotlin.annotation.Target(
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.FIELD,
)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@kotlin.annotation.MustBeDocumented
@Constraint(validatedBy = [])
@Pattern(regexp = "^U[0-9]{5}\$", message = "UserIdはU + 数字5桁の6桁にしてください。")
annotation class GrpcUserId(
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)


