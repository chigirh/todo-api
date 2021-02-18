/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-02-15T21:37:11.596138+09:00
 */
package chigirh.app.todo.be.todoapi.infra.mapper

import chigirh.app.todo.be.todoapi.infra.dto.MUserRecord
import chigirh.app.todo.be.todoapi.infra.mapper.MUserDynamicSqlSupport.MUser
import chigirh.app.todo.be.todoapi.infra.mapper.MUserDynamicSqlSupport.MUser.createAt
import chigirh.app.todo.be.todoapi.infra.mapper.MUserDynamicSqlSupport.MUser.createDate
import chigirh.app.todo.be.todoapi.infra.mapper.MUserDynamicSqlSupport.MUser.password
import chigirh.app.todo.be.todoapi.infra.mapper.MUserDynamicSqlSupport.MUser.updatedAt
import chigirh.app.todo.be.todoapi.infra.mapper.MUserDynamicSqlSupport.MUser.updatedDate
import chigirh.app.todo.be.todoapi.infra.mapper.MUserDynamicSqlSupport.MUser.userId
import chigirh.app.todo.be.todoapi.infra.mapper.MUserDynamicSqlSupport.MUser.userName
import chigirh.app.todo.be.todoapi.infra.mapper.MUserDynamicSqlSupport.MUser.version
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun MUserMapper.count(completer: CountCompleter) =
    countFrom(this::count, MUser, completer)

fun MUserMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, MUser, completer)

fun MUserMapper.deleteByPrimaryKey(userId_: String) =
    delete {
        where(userId, isEqualTo(userId_))
    }

fun MUserMapper.insert(record: MUserRecord) =
    insert(this::insert, record, MUser) {
        map(userId).toProperty("userId")
        map(password).toProperty("password")
        map(userName).toProperty("userName")
        map(createDate).toProperty("createDate")
        map(createAt).toProperty("createAt")
        map(updatedDate).toProperty("updatedDate")
        map(updatedAt).toProperty("updatedAt")
        map(version).toProperty("version")
    }

fun MUserMapper.insertMultiple(records: Collection<MUserRecord>) =
    insertMultiple(this::insertMultiple, records, MUser) {
        map(userId).toProperty("userId")
        map(password).toProperty("password")
        map(userName).toProperty("userName")
        map(createDate).toProperty("createDate")
        map(createAt).toProperty("createAt")
        map(updatedDate).toProperty("updatedDate")
        map(updatedAt).toProperty("updatedAt")
        map(version).toProperty("version")
    }

fun MUserMapper.insertMultiple(vararg records: MUserRecord) =
    insertMultiple(records.toList())

fun MUserMapper.insertSelective(record: MUserRecord) =
    insert(this::insert, record, MUser) {
        map(userId).toPropertyWhenPresent("userId", record::userId)
        map(password).toPropertyWhenPresent("password", record::password)
        map(userName).toPropertyWhenPresent("userName", record::userName)
        map(createDate).toPropertyWhenPresent("createDate", record::createDate)
        map(createAt).toPropertyWhenPresent("createAt", record::createAt)
        map(updatedDate).toPropertyWhenPresent("updatedDate", record::updatedDate)
        map(updatedAt).toPropertyWhenPresent("updatedAt", record::updatedAt)
        map(version).toPropertyWhenPresent("version", record::version)
    }

private val columnList = listOf(userId, password, userName, createDate, createAt, updatedDate, updatedAt, version)

fun MUserMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, MUser, completer)

fun MUserMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, MUser, completer)

fun MUserMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, MUser, completer)

fun MUserMapper.selectByPrimaryKey(userId_: String) =
    selectOne {
        where(userId, isEqualTo(userId_))
    }

fun MUserMapper.update(completer: UpdateCompleter) =
    update(this::update, MUser, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: MUserRecord) =
    apply {
        set(userId).equalTo(record::userId)
        set(password).equalTo(record::password)
        set(userName).equalTo(record::userName)
        set(createDate).equalTo(record::createDate)
        set(createAt).equalTo(record::createAt)
        set(updatedDate).equalTo(record::updatedDate)
        set(updatedAt).equalTo(record::updatedAt)
        set(version).equalTo(record::version)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: MUserRecord) =
    apply {
        set(userId).equalToWhenPresent(record::userId)
        set(password).equalToWhenPresent(record::password)
        set(userName).equalToWhenPresent(record::userName)
        set(createDate).equalToWhenPresent(record::createDate)
        set(createAt).equalToWhenPresent(record::createAt)
        set(updatedDate).equalToWhenPresent(record::updatedDate)
        set(updatedAt).equalToWhenPresent(record::updatedAt)
        set(version).equalToWhenPresent(record::version)
    }

fun MUserMapper.updateByPrimaryKey(record: MUserRecord) =
    update {
        set(password).equalTo(record::password)
        set(userName).equalTo(record::userName)
        set(createDate).equalTo(record::createDate)
        set(createAt).equalTo(record::createAt)
        set(updatedDate).equalTo(record::updatedDate)
        set(updatedAt).equalTo(record::updatedAt)
        set(version).equalTo(record::version)
        where(userId, isEqualTo(record::userId))
    }

fun MUserMapper.updateByPrimaryKeySelective(record: MUserRecord) =
    update {
        set(password).equalToWhenPresent(record::password)
        set(userName).equalToWhenPresent(record::userName)
        set(createDate).equalToWhenPresent(record::createDate)
        set(createAt).equalToWhenPresent(record::createAt)
        set(updatedDate).equalToWhenPresent(record::updatedDate)
        set(updatedAt).equalToWhenPresent(record::updatedAt)
        set(version).equalToWhenPresent(record::version)
        where(userId, isEqualTo(record::userId))
    }