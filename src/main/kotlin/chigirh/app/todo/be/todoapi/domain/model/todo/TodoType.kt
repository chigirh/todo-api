package chigirh.app.todo.be.todoapi.domain.model.todo

import chigirh.app.todo.be.todoapi.domain.exception.InternalException
import org.apache.commons.lang3.StringUtils

/**
 * Todo種別.
 */
enum class TodoType(val v: String) {
    /** 親 */
    PARENT("1"),
    /** 子 */
    CHILD("2");

    companion object {
        fun of(v: String): TodoType {
            return values().firstOrNull { StringUtils.equals(v, it.v) }
                ?: throw InternalException(
                    "TodoType",
                    "undefined."
                )
        }
    }
}