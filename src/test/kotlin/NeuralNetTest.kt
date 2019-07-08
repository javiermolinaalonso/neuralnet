import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.nd4j.linalg.factory.Nd4j
import kotlin.math.roundToInt

class NeuralNetTest {

    @Test
    internal fun givenCirclesDataset_whenTrain_expectErrorLessThan01() {
        val inputs = Nd4j.create("src/test/resources/coordinates.csv".readCsv(1).readAll().flatMap { x -> x.map { it.toDouble() } }.toDoubleArray()).reshape(500, 2)
        val results = Nd4j.create("src/test/resources/coordinates_result.csv".readCsv(0).readAll().map { it[0].toDouble() }.toDoubleArray()).reshape(500, 1)

        val neuralNet = NeuralNet(listOf(2, 4, 1))

        for (i in 0..3000) {
            val train = neuralNet.train(inputs, results, 0.05)
            println(l2_cost(train, results))
        }
        (-100..100 step 2).forEach {x ->
            (-100..100 step 2).forEach { y ->
                val value = Nd4j.create(doubleArrayOf(x.toDouble() / 100.0, y.toDouble() / 100.0)).reshape(1, 2)
                neuralNet.predict(value).last().toDoubleVector().forEach { print(it.roundToInt().toString() + " ") }
            }
            println()
        }

        val X = Nd4j.create("src/test/resources/coordinates_test.csv".readCsv(1).readAll().flatMap { it.map { it.toDouble() } }.toDoubleArray()).reshape(4, 2)
        val R = neuralNet.predict(X).last()
        assertEquals(Math.round(R.getDouble(0)), 1L)
        assertEquals(Math.round(R.getDouble(1)), 1L)
        assertEquals(Math.round(R.getDouble(2)), 1L)
        assertEquals(Math.round(R.getDouble(3)), 0L)

    }

}