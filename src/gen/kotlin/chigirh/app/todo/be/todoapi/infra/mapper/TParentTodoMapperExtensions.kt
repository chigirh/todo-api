/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-02-15T21:37:11.6151423+09:00
 */
package chigirh.app.todo.be.todoapi.infra.mapper

import chigirh.app.todo.be.todoapi.infra.dto.TParentTodoRecord
import chigirh.app.todo.be.todoapi.infra.mapper.TParentTodoDynamicSqlSupport.TParentTodo
import chigirh.app.todo.be.todoapi.infra.mapper.TParentTodoDynamicSqlSupport.TParentTodo.createAt
import chigirh.app.todo.be.todoapi.infra.mapper.TParentTodoDynamicSqlSupport.TParentTodo.createDate
import chigirh.app.todo.be.todoapi.infra.mapper.TParentTodoDynamicSqlSupport.TParentTodo.finishDate
import chigirh.app.todo.be.todoapi.infra.mapper.TParentTodoDynamicSqlSupport.TParentTodo.isFinished
import chigirh.app.todo.be.todoapi.infra.mapper.TParentTodoDynamicSqlSupport.TParentTodo.limitDate
import chigirh.app.todo.be.todoapi.infra.mapper.TParentTodoDynamicSqlSupport.TParentTodo.pTodoId
import chigirh.app.todo.be.todoapi.infra.mapper.TParentTodoDynamicSqlSupport.TParentTodo.pTodoName
import chigirh.app.todo.be.todoapi.infra.mapper.TParentTodoDynamicSqlSupport.TParentTodo.updatedAt
import chigirh.app.todo.be.todoapi.infra.mapper.TParentTodoDynamicSqlSupport.TParentTodo.updatedDate
import chigirh.app.todo.be.todoapi.infra.mapper.TParentTodoDynamicSqlSupport.TParentTodo.version
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun TParentTodoMapper.count(completer: CountCompleter) =
    countFrom(this::count, TParentTodo, completer)

fun TParentTodoMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, TParentTodo, completer)

fun TParentTodoMapper.deleteByPrimaryKey(pTodoId_: String) =
    delete {
        where(pTodoId, isEqualTo(pTodoId_))
    }

fun TParentTodoMapper.insert(record: TParentTodoRecord) =
    insert(this::insert, record, TParentTodo) {
        map(pTodoId).toProperty("pTodoId")
        map(pTodoName).toProperty("pTodoName")
        map(isFinished).toProperty("isFinished")
        map(limitDate).toProperty("limitDate")
        map(finishDate).toProperty("finishDate")
        map(createDate).toProperty("createDate")
        map(createAt).toProperty("createAt")
        map(updatedDate).toProperty("updatedDate")
        map(updatedAt).toProperty("updatedAt")
        map(version).toProperty("version")
    }

fun TParentTodoMapper.insertMultiple(records: Collection<TParentTodoRecord>) =
    insertMultiple(this::insertMultiple, records, TParentTodo) {
        map(pTodoId).toProperty("pTodoId")
        map(pTodoName).toProperty("pTodoName")
        map(isFinished).toProperty("isFinished")
        map(limitDate).toProperty("limitDate")
        map(finishDate).toProperty("finishDate")
        map(createDate).toProperty("createDate")
        map(createAt).toProperty("createAt")
        map(updatedDate).toProperty("updatedDate")
        map(updatedAt).toProperty("updatedAt")
        map(version).toProperty("version")
    }

fun TParentTodoMapper.insertMultiple(vararg records: TParentTodoRecord) =
    insertMultiple(records.toList())

fun TParentTodoMapper.insertSelective(record: TParentTodoRecord) =
    insert(this::insert, record, TParentTodo) {
        map(pTodoId).toPropertyWhenPresent("pTodoId", record::pTodoId)
        map(pTodoName).toPropertyWhenPresent("pTodoName", record::pTodoName)
        map(isFinished).toPropertyWhenPresent("isFinished", record::isFinished)
        map(limitDate).toPropertyWhenPresent("limitDate", record::limitDate)
        map(finishDate).toPropertyWhenPresent("finishDate", record::finishDate)
        map(createDate).toPropertyWhenPresent("createDate", record::createDate)
        map(createAt).toPropertyWhenPresent("createAt", record::createAt)
        map(updatedDate).toPropertyWhenPresent("updatedDate", record::updatedDate)
        map(updatedAt).toPropertyWhenPresent("updatedAt", record::updatedAt)
        map(version).toPropertyWhenPresent("version", record::version)
    }

private val columnList = listOf(pTodoId, pTodoName, isFinished, limitDate, finishDate, createDate, createAt, updatedDate, updatedAt, version)

fun TParentTodoMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, TParentTodo, completer)

fun TParentTodoMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, TParentTodo, completer)

fun TParentTodoMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, TParentTodo, completer)

fun TParentTodoMapper.selectByPrimaryKey(pTodoId_: String) =
    selectOne {
        where(pTodoId, isEqualTo(pTodoId_))
    }

fun TParentTodoMapper.update(completer: UpdateCompleter) =
    update(this::update, TParentTodo, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: TParentTodoRecord) =
    apply {
        set(pTodoId).equalTo(record::pTodoId)
        set(pTodoName).equalTo(record::pTodoName)
        set(isFinished).equalTo(record::isFinished)
        set(limitDate).equalTo(record::limitDate)
        set(finishDate).equalTo(record::finishDate)
        set(createDate).equalTo(record::createDate)
        set(createAt).equalTo(record::createAt)
        set(updatedDate).equalTo(record::updatedDate)
        set(updatedAt).equalTo(record::updatedAt)
        set(version).equalTo(record::version)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: TParentTodoRecord) =
    apply {
        set(pTodoId).equalToWhenPresent(record::pTodoId)
        set(pTodoName).equalToWhenPresent(record::pTodoName)
        set(isFinished).equalToWhenPresent(record::isFinished)
        set(limitDate).equalToWhenPresent(record::limitDate)
        set(finishDate).equalToWhenPresent(record::finishDate)
        set(createDate).equalToWhenPresent(record::createDate)
        set(createAt).equalToWhenPresent(record::createAt)
        set(updatedDate).equalToWhenPresent(record::updatedDate)
        set(updatedAt).equalToWhenPresent(record::updatedAt)
        set(version).equalToWhenPresent(record::version)
    }

fun TParentTodoMapper.updateByPrimaryKey(record: TParentTodoRecord) =
    update {
        set(pTodoName).equalTo(record::pTodoName)
        set(isFinished).equalTo(record::isFinished)
        set(limitDate).equalTo(record::limitDate)
        set(finishDate).equalTo(record::finishDate)
        set(createDate).equalTo(record::createDate)
        set(createAt).equalTo(record::createAt)
        set(updatedDate).equalTo(record::updatedDate)
        set(updatedAt).equalTo(record::updatedAt)
        set(version).equalTo(record::version)
        where(pTodoId, isEqualTo(record::pTodoId))
    }

fun TParentTodoMapper.updateByPrimaryKeySelective(record: TParentTodoRecord) =
    update {
        set(pTodoName).equalToWhenPresent(record::pTodoName)
        set(isFinished).equalToWhenPresent(record::isFinished)
        set(limitDate).equalToWhenPresent(record::limitDate)
        set(finishDate).equalToWhenPresent(record::finishDate)
        set(createDate).equalToWhenPresent(record::createDate)
        set(createAt).equalToWhenPresent(record::createAt)
        set(updatedDate).equalToWhenPresent(record::updatedDate)
        set(updatedAt).equalToWhenPresent(record::updatedAt)
        set(version).equalToWhenPresent(record::version)
        where(pTodoId, isEqualTo(record::pTodoId))
    }