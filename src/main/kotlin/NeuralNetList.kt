import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random


class NeuralNetList(val topology: List<Int>) {
//
//    private val layers = (0 .. topology.size - 2).map { NeuralLayerList(topology[it], topology[it + 1]) }
//
//    fun train(X: List<List<Double>>, Y: List<Double>, learningRate: Double) : List<Double> {
//        //forward pass
//        val out = ArrayList<Pair<List<List<Double>>, List<List<Double>>>>()
//        out.add(Pair(emptyList(), X)) //Inicializar
//
//        layers.forEachIndexed { index, layer ->
//            val z = (out[out.size - 1].second * layer.W).sumVector(layer.b) //Sumas ponderadas de las neuronas
//            val a = z.map { it.map( sigmoid ) } // Salidas de las neuronas
//
//            out.add(Pair(z, a))
//        }
//
//        val deltas = LinkedList<List<List<Double>>>()
//        //Backward pass
//        for (l in layers.size - 1 downTo 0) {
//            val a: List<List<Double>> = out[l + 1].second
//            if (l == layers.size - 1) {
//                val derivateCostFunctionVersusExpectedOutput: List<Double> = l2_cost_l_derivate(a.flatten(), Y)
//                val derivateActivationLastLayer: List<Double> = a.flatten().map(sigmoidDerivate)
//                val r: List<List<Double>> = derivateActivationLastLayer.zip(derivateCostFunctionVersusExpectedOutput).map { listOf(it.first * it.second) }
//                deltas.add(r) //necesario pq necesitamos una matriz de x.size x 1
//            } else {
//                val derivateLayer: List<List<Double>> = a.map { it.map(sigmoidDerivate)}
//                val matrixMult: List<List<Double>> = deltas[0] * layers[l+1].W.transpose()
//
//                val r: List<List<Double>> = multiplyMatrices(derivateLayer, matrixMult)
//                deltas.addFirst(r)
//            }
//
//            //Gradient descent
//            val mean: List<Double> = deltas[0].transpose().map { it.average() * learningRate } //Calculamos la media de los valores de entrada (La traspuesta es pq queremos la primera columna)
//            layers[l].b = layers[l].b.mapIndexed { index, d -> d - mean[index]}
//
//            val matrix: List<List<Double>> = out[l].second.transpose() * deltas[0]
//            val matrixLr: List<List<Double>> = matrix.map { it.map { it * learningRate } }
//
//            if (l == 0) {
////                println(matrixLr)
////                println("-------------")
//                println(layers[l].b)
////                println("*************")
//            }
//            layers[l].W = subtract(layers[l].W, matrixLr)
//        }
//
////        printSteps(out, Y)
//        return out[out.size - 1].second.flatten()
//    }
//
//    private fun multiplyMatrices(matrixA: List<List<Double>>, matrixB: List<List<Double>>): List<List<Double>> {
//        val deltaCurrentLayer: List<List<Double>> = matrixA.mapIndexed { i, list ->
//            list.mapIndexed { j, _ -> matrixA[i][j] * matrixB[i][j] }
//        }
//        return deltaCurrentLayer
//    }
//    private fun subtract(matrixA: List<List<Double>>, matrixB: List<List<Double>>): List<List<Double>> {
//        val deltaCurrentLayer: List<List<Double>> = matrixA.mapIndexed { i, list ->
//            list.mapIndexed { j, _ -> matrixA[i][j] - matrixB[i][j] }
//        }
//        return deltaCurrentLayer
//    }
//
//    fun predict(X: List<List<Double>>): List<Double> {
//        val out = ArrayList<Pair<List<List<Double>>, List<List<Double>>>>()
//        out.add(Pair(emptyList(), X)) //Inicializar
//        layers.forEachIndexed { index, layer ->
//            val z = (out[out.size - 1].second * layer.W).sumVector(layer.b) //Sumas ponderadas de las neuronas
//            val a = z.map { it.map( sigmoid ) } // Salidas de las neuronas
//
//            out.add(Pair(z, a))
//        }
//        return out[out.size - 1].second.flatten()
//    }
}

class NeuralLayerList(private val wSize:Int, private val numNeurons:Int) {
    var b = List(numNeurons) { Random.nextDouble(-1.0, 1.0)}
    var W = List(wSize) { List(numNeurons) { Random.nextDouble(-1.0, 1.0)} }
}