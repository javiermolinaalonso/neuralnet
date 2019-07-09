import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.nd4j.evaluation.regression.RegressionEvaluation
import org.nd4j.linalg.factory.Nd4j

class CostFunctionsTest {

    @Test
    internal fun assertAvgMeanSqError() {
        val x = Nd4j.create(listOf(43.0, 44.0, 45.0, 46.0, 47.0)).reshape(5, 1)
        val y = Nd4j.create(listOf(41.0, 45.0, 49.0, 47.0, 44.0)).reshape(5, 1)
        val re = RegressionEvaluation()
        re.eval(x, y)
        assertEquals(re.averageMeanSquaredError(), 6.2)
    }
}