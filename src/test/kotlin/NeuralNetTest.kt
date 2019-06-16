import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NeuralNetTest {

    @Test
    internal fun givenNeuralNetTopology_assertIsCorrect() {
        val neuralNet = NeuralNet(listOf(2, 4, 1))

        assertEquals(neuralNet.layers.size, 3, "Size of neural net is not correct")
        assertEquals(neuralNet.layers[0].neurons.size, 2, "Size of neural layer is not correct")
        assertEquals(neuralNet.layers[1].neurons.size, 4, "Size of neural layer is not correct")
        assertEquals(neuralNet.layers[2].neurons.size, 1, "Size of neural layer is not correct")
    }

    @Test
    internal fun name() {
        val inputs: List<List<Double>> = "src/test/resources/coordinates.csv".readCsv(1).readAll().map { it.asList().map { it.toDouble() } }
        val results: List<Double> = "src/test/resources/coordinates_result.csv".readCsv(0).readAll().map { it[0].toDouble() }

        val neuralNet = NeuralNet(listOf(2,4,1))

        neuralNet.train(inputs, results, 0.05)
    }


    @Test
    internal fun matrixMultiplicationTestSameSize() {
        val matrix1 = listOf(listOf(-1.0, 4.0), listOf(2.0, 3.0))
        val matrix2 = listOf(listOf(9.0, -3.0), listOf(6.0, 1.0))

        val result = matrix1 * matrix2

        assertEquals(result[0][0], 15.0)
        assertEquals(result[0][1], 7.0)
        assertEquals(result[1][0], 36.0)
        assertEquals(result[1][1], -3.0)
    }

    @Test
    internal fun matrixVectorMultiplication() {
//        [1 2 3] [ 7 ]   [58 ]
//        [4 5 6] | 9 | = [139]
//                [11 ]

//        [[1, 2, 3], [4, 5, 6]] * [[7,8],[9,10],[11,12]] = [[58,64], [139, 154]]
        val matrix1 = listOf(listOf(1.0, 2.0, 3.0), listOf(4.0, 5.0, 6.0))
        val vector = listOf(7.0, 9.0, 11.0)

        val result = matrix1.multiplyVector(vector)

        assertEquals(result[0], 58.0)
        assertEquals(result[1], 139.0)
    }

    @Test
    internal fun matrixMultiplicationTestDiferentSize() {
//        [1 2 3] [ 7  8]   [58 64  ]
//        [4 5 6] | 9 10| = [139 154]
//                [11 12]

//        [[1, 2, 3], [4, 5, 6]] * [[7,8],[9,10],[11,12]] = [[58,64], [139, 154]]
        val matrix1 = listOf(listOf(1.0, 2.0, 3.0), listOf(4.0, 5.0, 6.0))
        val matrix2 = listOf(listOf(7.0, 8.0), listOf(9.0, 10.0), listOf(11.0, 12.0))

        val result = matrix1 * matrix2

        assertEquals(result[0][0], 58.0)
        assertEquals(result[0][1], 64.0)
        assertEquals(result[1][0], 139.0)
        assertEquals(result[1][1], 154.0)
    }
    @Test
    internal fun matrixVectorSum() {
//        [1 2]+ [10  20]   [11 22  ]
//        [3 4]          =  [13 24]
//        [5 6]             [15 26]

//        [[1, 2, 3], [4, 5, 6]] * [[7,8],[9,10],[11,12]] = [[58,64], [139, 154]]
        val matrix1 = listOf(listOf(1.0, 2.0), listOf(3.0, 4.0), listOf(5.0, 6.0))
        val vector = listOf(10.0, 20.0)

        val result = matrix1.sumVector(vector)

        assertEquals(result[0][0], 11.0)
        assertEquals(result[0][1], 22.0)
        assertEquals(result[1][0], 13.0)
        assertEquals(result[1][1], 24.0)
        assertEquals(result[2][0], 15.0)
        assertEquals(result[2][1], 26.0)
    }
}