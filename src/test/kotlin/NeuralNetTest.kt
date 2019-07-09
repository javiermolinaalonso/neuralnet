import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test
import org.nd4j.evaluation.regression.RegressionEvaluation
import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.factory.Nd4j
import kotlin.math.roundToInt

class NeuralNetTest {

    @Test
    internal fun givenCirclesDataset_whenTrain_expectErrorLessThan01() {
        val inputs = Nd4j.create("src/test/resources/coordinates.csv".readCsv(1).readAll().flatMap { x -> x.map { it.toDouble() } }.toDoubleArray()).reshape(500, 2)
        val results = Nd4j.create("src/test/resources/coordinates_result.csv".readCsv(0).readAll().map { it[0].toDouble() }.toDoubleArray()).reshape(500, 1)

        val neuralNet = NeuralNet(listOf(2, 4, 1))

        var train: INDArray
        var i = 0
        do {
            train = neuralNet.train(inputs, results, 0.05)

            val re = RegressionEvaluation()
            re.eval(train, results)
            i++
            println(re.averageMeanSquaredError())
        } while (re.averageMeanSquaredError() > 0.01 || i < 10000)

        if (i >= 10000) {
            fail<String>("Unable to find the solution")
        }

        val X = Nd4j.create("src/test/resources/coordinates_test.csv".readCsv(1).readAll().flatMap { it.map { it.toDouble() } }.toDoubleArray()).reshape(4, 2)
        val R = neuralNet.predict(X).last()
        assertEquals(R.getDouble(0).roundToInt(), 1)
        assertEquals(R.getDouble(1).roundToInt(), 1)
        assertEquals(R.getDouble(2).roundToInt(), 1)
        assertEquals(R.getDouble(3).roundToInt(), 0)

    }

}