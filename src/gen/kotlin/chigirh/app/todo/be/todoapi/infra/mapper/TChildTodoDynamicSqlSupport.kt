/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-02-15T21:37:11.6171408+09:00
 */
package chigirh.app.todo.be.todoapi.infra.mapper

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.SqlTable

object TChildTodoDynamicSqlSupport {
    object TChildTodo : SqlTable("t_child_todo") {
        val cTodoId = column<String>("c_todo_id", JDBCType.VARCHAR)

        val cTodoName = column<String>("c_todo_name", JDBCType.VARCHAR)

        val isFinished = column<Boolean>("is_finished", JDBCType.BIT)

        val pTodoId = column<String>("p_todo_id", JDBCType.VARCHAR)

        val limitDate = column<LocalDateTime>("limit_date", JDBCType.TIMESTAMP)

        val finishDate = column<LocalDateTime>("finish_date", JDBCType.TIMESTAMP)

        val createDate = column<LocalDateTime>("create_date", JDBCType.TIMESTAMP)

        val createAt = column<String>("create_at", JDBCType.VARCHAR)

        val updatedDate = column<LocalDateTime>("updated_date", JDBCType.TIMESTAMP)

        val updatedAt = column<String>("updated_at", JDBCType.VARCHAR)

        val version = column<Int>("version", JDBCType.INTEGER)
    }
}