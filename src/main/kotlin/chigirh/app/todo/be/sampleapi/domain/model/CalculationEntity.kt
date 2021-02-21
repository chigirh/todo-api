package chigirh.app.todo.be.sampleapi.domain.model

import org.slf4j.LoggerFactory

class CalculationEntity(
    private val num: Int,
    private val operation: Operation
) {
    fun exec(total: Int) = when (operation) {
        Operation.ADD -> {
            Logger.info("{} + {}", total, num)
            total + num
        }
        Operation.SUB -> {
            Logger.info("{} - {}", total, num)
            total - num
        }
        Operation.MUL -> {
            Logger.info("{} * {}", total, num)
            total * num
        }
        Operation.DIV -> {
            Logger.info("{} / {}", total, num)
            total / num
        }
    }

    companion object {
        private val Logger = LoggerFactory.getLogger(CalculationEntity::class.java)
    }
}