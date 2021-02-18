/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-02-14T10:49:33.3704635+09:00
 */
package chigirh.app.todo.be.todoapi.infra.dto.result

import java.time.LocalDateTime

/**
 * TodoRecordResultとt_child_todoを内部結合したDTO.
 */
data class TodoRecordResult(
    //子Todo
    var cTodoId: String,
    var cTodoName: String,
    var cIsFinished: Boolean,
    var cLimitDate: LocalDateTime? = null,
    var cFinishDate: LocalDateTime? = null,
    var cVersion: Int,
    // 親Todo.
    var pTodoId: String,
    var pTodoName: String,
    var pIsFinished: Boolean,
    var pLimitDate: LocalDateTime? = null,
    var pFinishDate: LocalDateTime? = null,
    var pVarsion: Int
)