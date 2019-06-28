import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class NeuralNetTest {

    @Test
    internal fun givenCirclesDataset_whenTrain_expectErrorLessThan01() {
        val inputs: List<List<Double>> = "src/test/resources/coordinates.csv".readCsv(1).readAll().map { it.asList().map { it.toDouble() } }
        val results: List<Double> = "src/test/resources/coordinates_result.csv".readCsv(0).readAll().map { it[0].toDouble() }

        val neuralNet = NeuralNet(listOf(2, 4, 1))

        val loss = ArrayList<Double>()
        var train = emptyList<Double>()
        for (i in 0..10000) {
            train = neuralNet.train(inputs, results, 0.05)
                loss.add(l2_cost(train, results))
        }
        assertTrue(l2_cost(train, results) < 0.001)

        val X: List<List<Double>> = "src/test/resources/coordinates_test.csv".readCsv(1).readAll().map { it.asList().map { it.toDouble() } }
        val R: List<Double> = neuralNet.predict(X)
        assertEquals(Math.round(R[0]), 1L)
        assertEquals(Math.round(R[1]), 1L)
        assertEquals(Math.round(R[2]), 1L)
        assertEquals(Math.round(R[3]), 0L)
    }

}