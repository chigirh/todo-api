package chigirh.app.todo.be.todoapi.infra.repository.common

import chigirh.app.todo.be.todoapi.domain.exception.ConflictException
import chigirh.app.todo.be.todoapi.domain.exception.NotFoundException
import chigirh.app.todo.be.todoapi.domain.model.common.VersionableEntityBase
import chigirh.app.todo.be.todoapi.domain.model.vo.Version
import org.slf4j.LoggerFactory

abstract class VersionableRepositoryBase<KEY> {
    /**
     * バージョンチェック.
     * レコードが取得できない、バージョンチェックNGの場合は例外をスロー.
     */
    fun versionCheck(comparation: Version, key: KEY) {
        findByKey(key)?.run {
            LOGGER.info("最新バージョン:{},比較バージョン{}", this.version.v, comparation.v)
            if (!versionCheck(comparation)) throw ConflictException(key.toString(), "")
        } ?: throw NotFoundException(key.toString(), "")
    }

    /**
     * レコードのキー取得.
     */
    abstract fun findByKey(key: KEY): VersionableEntityBase?

    companion object {
        /** ロガー */
        val LOGGER = LoggerFactory.getLogger(VersionableRepositoryBase::class.java)!!
    }

}