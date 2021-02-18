/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-02-15T21:37:11.6151423+09:00
 */
package chigirh.app.todo.be.todoapi.infra.mapper

import chigirh.app.todo.be.todoapi.infra.dto.TParentTodoRecord
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
interface TParentTodoMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun count(selectStatement: SelectStatementProvider): Long

    @DeleteProvider(type=SqlProviderAdapter::class, method="delete")
    fun delete(deleteStatement: DeleteStatementProvider): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insert")
    fun insert(insertStatement: InsertStatementProvider<TParentTodoRecord>): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insertMultiple")
    fun insertMultiple(multipleInsertStatement: MultiRowInsertStatementProvider<TParentTodoRecord>): Int

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("TParentTodoRecordResult")
    fun selectOne(selectStatement: SelectStatementProvider): TParentTodoRecord?

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="TParentTodoRecordResult", value = [
        Result(column="p_todo_id", property="pTodoId", jdbcType=JdbcType.VARCHAR, id=true),
        Result(column="p_todo_name", property="pTodoName", jdbcType=JdbcType.VARCHAR),
        Result(column="is_finished", property="isFinished", jdbcType=JdbcType.BIT),
        Result(column="limit_date", property="limitDate", jdbcType=JdbcType.TIMESTAMP),
        Result(column="finish_date", property="finishDate", jdbcType=JdbcType.TIMESTAMP),
        Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        Result(column="create_at", property="createAt", jdbcType=JdbcType.VARCHAR),
        Result(column="updated_date", property="updatedDate", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.VARCHAR),
        Result(column="version", property="version", jdbcType=JdbcType.INTEGER)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<TParentTodoRecord>

    @UpdateProvider(type=SqlProviderAdapter::class, method="update")
    fun update(updateStatement: UpdateStatementProvider): Int
}