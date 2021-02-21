package chigirh.app.todo.be.sampleapi.web.grpc.validator

import com.google.protobuf.LazyStringList
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class GrpcStringRepeatedSizeValidator : ConstraintValidator<GrpcStringRepeatedSize, LazyStringList> {
    var min: Int = 0
    var max: Int = 0

    override fun initialize(constraintAnnotation: GrpcStringRepeatedSize) {
        min = constraintAnnotation.min
        max = constraintAnnotation.max
    }

    override fun isValid(
        value: LazyStringList,
        context: ConstraintValidatorContext?
    ): Boolean {
        for (s: String in value) {
            if (s.length < min.toInt()) return false
            if (max.toInt() < s.length) return false
        }
        return true
    }
}