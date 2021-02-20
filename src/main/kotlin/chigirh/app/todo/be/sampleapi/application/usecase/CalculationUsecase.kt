package chigirh.app.todo.be.sampleapi.application.usecase

import chigirh.app.todo.be.sampleapi.domain.model.CalculationEntity
import org.springframework.stereotype.Component

@Component
class CalculationUsecase {
    operator fun invoke(calculationList: List<CalculationEntity>): Int {
        var total = 0
        calculationList.forEach {
            total = it.exec(total)
        }
        return total
    }
}