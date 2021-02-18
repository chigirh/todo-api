/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-02-15T21:37:11.5881399+09:00
 */
package chigirh.app.todo.be.todoapi.infra.mapper

import chigirh.app.todo.be.todoapi.infra.dto.MUserRecord
import org.apache.ibatis.annotations.DeleteProvider
import org.apache.ibatis.annotations.InsertProvider
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.annotations.UpdateProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter

@Mapper
interface MUserMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun count(selectStatement: SelectStatementProvider): Long

    @DeleteProvider(type=SqlProviderAdapter::class, method="delete")
    fun delete(deleteStatement: DeleteStatementProvider): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insert")
    fun insert(insertStatement: InsertStatementProvider<MUserRecord>): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insertMultiple")
    fun insertMultiple(multipleInsertStatement: MultiRowInsertStatementProvider<MUserRecord>): Int

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("MUserRecordResult")
    fun selectOne(selectStatement: SelectStatementProvider): MUserRecord?

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="MUserRecordResult", value = [
        Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR, id=true),
        Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        Result(column="create_at", property="createAt", jdbcType=JdbcType.VARCHAR),
        Result(column="updated_date", property="updatedDate", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.VARCHAR),
        Result(column="version", property="version", jdbcType=JdbcType.INTEGER)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<MUserRecord>

    @UpdateProvider(type=SqlProviderAdapter::class, method="update")
    fun update(updateStatement: UpdateStatementProvider): Int
}