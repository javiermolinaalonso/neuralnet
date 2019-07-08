import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.nd4j.linalg.factory.Nd4j
import kotlin.math.roundToInt

class BasicTest {

    @Test
    internal fun yupNd4j() {
        val inputs = Nd4j.create("src/test/resources/basic/coordinates.csv".readCsv(1).readAll().flatMap { x -> x.map { it.toDouble() } }.toDoubleArray()).reshape(10, 2)
        val results = Nd4j.create("src/test/resources/basic/coordinates_result.csv".readCsv(0).readAll().map { it[0].toDouble() }.toDoubleArray()).reshape(10, 1)

        val neuralNet = NeuralNet(listOf(2, 4, 1))

        val loss = ArrayList<Double>()
        for (i in 0..10000) {
            val train = neuralNet.train(inputs, results, 0.3)
            loss.add(l2_cost(train, results))
            println(l2_cost(train, results))
        }

        val X = Nd4j.create("src/test/resources/basic/coordinates_test.csv".readCsv(1).readAll().flatMap { it.map { it.toDouble() } }.toDoubleArray()).reshape(4, 2)
        val R = neuralNet.predict(X)

//        (-100..100 step 2).forEach {x ->
//            (-100..100 step 2).forEach { y ->
//                val value = Nd4j.create(doubleArrayOf(x.toDouble() / 100.0, y.toDouble() / 100.0)).reshape(1, 2)
//                neuralNet.predict(value).toDoubleVector().forEach { print(it.roundToInt().toString() + " ") }
//            }
//            println()
//        }
    }

}