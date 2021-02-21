package chigirh.app.todo.be.sampleapi.web.grpc.validator

import com.google.protobuf.LazyStringList
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class GrpcStringRepeatedPatternValidator : ConstraintValidator<GrpcStringRepeatedPattern, LazyStringList> {
    lateinit var regexp: String

    override fun initialize(constraintAnnotation: GrpcStringRepeatedPattern) {
        regexp = constraintAnnotation.regexp

    }

    override fun isValid(
        value: LazyStringList,
        context: ConstraintValidatorContext?
    ): Boolean {
        val regex = Regex(regexp)
        for (s: String in value) {
            if (!regex.containsMatchIn(s)) return false
        }
        return true
    }
}