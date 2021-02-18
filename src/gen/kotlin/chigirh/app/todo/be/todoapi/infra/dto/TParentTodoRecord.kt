/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-02-15T21:37:11.6141414+09:00
 */
package chigirh.app.todo.be.todoapi.infra.dto

import java.time.LocalDateTime

data class TParentTodoRecord(
    var pTodoId: String? = null,
    var pTodoName: String? = null,
    var isFinished: Boolean? = null,
    var limitDate: LocalDateTime? = null,
    var finishDate: LocalDateTime? = null,
    var createDate: LocalDateTime? = null,
    var createAt: String? = null,
    var updatedDate: LocalDateTime? = null,
    var updatedAt: String? = null,
    var version: Int? = null
)