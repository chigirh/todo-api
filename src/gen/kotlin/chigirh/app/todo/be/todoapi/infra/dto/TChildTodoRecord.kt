/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-02-15T21:37:11.6161401+09:00
 */
package chigirh.app.todo.be.todoapi.infra.dto

import java.time.LocalDateTime

data class TChildTodoRecord(
    var cTodoId: String? = null,
    var cTodoName: String? = null,
    var isFinished: Boolean? = null,
    var pTodoId: String? = null,
    var limitDate: LocalDateTime? = null,
    var finishDate: LocalDateTime? = null,
    var createDate: LocalDateTime? = null,
    var createAt: String? = null,
    var updatedDate: LocalDateTime? = null,
    var updatedAt: String? = null,
    var version: Int? = null
)