/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-02-14T10:49:33.3684437+09:00
 */
package chigirh.app.todo.be.todoapi.infra.dto.result

import java.time.LocalDateTime

data class TParentTodoRecordResult(
    var pTodoId: String,
    var pTodoName: String,
    var isFinished: Boolean,
    var limitDate: LocalDateTime? = null,
    var finishDate: LocalDateTime? = null,
    var createDate: LocalDateTime? = null,
    var createAt: String? = null,
    var updatedDate: LocalDateTime? = null,
    var updatedAt: String? = null,
    var version: Int
)