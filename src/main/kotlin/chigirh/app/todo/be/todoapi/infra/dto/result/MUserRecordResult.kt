/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-02-14T10:49:33.3374434+09:00
 */
package chigirh.app.todo.be.todoapi.infra.dto.result

import java.time.LocalDateTime

data class MUserRecordResult(
    var userId: String,
    var password: String,
    var userName: String,
    var createDate: LocalDateTime? = null,
    var createAt: String? = null,
    var updatedDate: LocalDateTime? = null,
    var updatedAt: String? = null,
    var version: Int
)