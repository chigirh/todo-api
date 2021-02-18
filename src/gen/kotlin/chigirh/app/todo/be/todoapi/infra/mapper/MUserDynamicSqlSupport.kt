/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-02-15T21:37:11.5841387+09:00
 */
package chigirh.app.todo.be.todoapi.infra.mapper

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.SqlTable

object MUserDynamicSqlSupport {
    object MUser : SqlTable("m_user") {
        val userId = column<String>("user_id", JDBCType.VARCHAR)

        val password = column<String>("password", JDBCType.VARCHAR)

        val userName = column<String>("user_name", JDBCType.VARCHAR)

        val createDate = column<LocalDateTime>("create_date", JDBCType.TIMESTAMP)

        val createAt = column<String>("create_at", JDBCType.VARCHAR)

        val updatedDate = column<LocalDateTime>("updated_date", JDBCType.TIMESTAMP)

        val updatedAt = column<String>("updated_at", JDBCType.VARCHAR)

        val version = column<Int>("version", JDBCType.INTEGER)
    }
}