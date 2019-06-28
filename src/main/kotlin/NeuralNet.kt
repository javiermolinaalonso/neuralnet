import java.util.*
import kotlin.collections.ArrayList


class NeuralNet(val topology: List<Int>) {

    private val layers = (0 .. topology.size - 2).map { NeuralLayer(topology[it], topology[it + 1]) }

    fun train(X: List<List<Double>>, Y: List<Double>, learningRate: Double) : List<Double> {
        //forward pass
        val out = ArrayList<Pair<List<List<Double>>, List<List<Double>>>>()
        out.add(Pair(emptyList(), X)) //Inicializar

        layers.forEachIndexed { index, layer ->
            val z = (out[out.size - 1].second * layer.W).sumVector(layer.b) //Sumas ponderadas de las neuronas
            val a = z.map { it.map( sigmoid ) } // Salidas de las neuronas

            out.add(Pair(z, a))
        }

        val deltas = LinkedList<List<List<Double>>>()
        //Backward pass
        for (l in layers.size - 1 downTo 0) {
            val a: List<List<Double>> = out[l + 1].second
            if (l == layers.size - 1) {
                val derivateCostFunctionVersusExpectedOutput: List<Double> = l2_cost_derivate(a.flatten(), Y)
                val derivateActivationLastLayer: List<Double> = a.flatten().map(sigmoidDerivate)
                val r: List<List<Double>> = derivateActivationLastLayer.zip(derivateCostFunctionVersusExpectedOutput).map { listOf(it.first * it.second) }
                deltas.add(r) //necesario pq necesitamos una matriz de x.size x 1
            } else {
                val derivateLayer: List<List<Double>> = a.map { it.map(sigmoidDerivate)}
                val matrixMult: List<List<Double>> = deltas[0] * layers[l+1].W.transpose()

                val r: List<List<Double>> = multiplyMatrices(derivateLayer, matrixMult)
                deltas.addFirst(r)
            }

            //Gradient descent
            val mean: List<Double> = deltas[0].transpose().map { it.average() * learningRate } //Calculamos la media de los valores de entrada (La traspuesta es pq queremos la primera columna)
            layers[l].b = layers[l].b.mapIndexed { index, d -> d - mean[index]}

            val matrix: List<List<Double>> = out[l].second.transpose() * deltas[0]
            val matrixLr: List<List<Double>> = matrix.map { it.map { it * learningRate } }

            layers[l].W = subtract(layers[l].W, matrixLr)
        }

        return out[out.size - 1].second.flatten()
    }

    fun predict(X: List<List<Double>>): List<Double> {
        val out = ArrayList<Pair<List<List<Double>>, List<List<Double>>>>()
        out.add(Pair(emptyList(), X)) //Inicializar
        layers.forEachIndexed { index, layer ->
            val z = (out[out.size - 1].second * layer.W).sumVector(layer.b) //Sumas ponderadas de las neuronas
            val a = z.map { it.map( sigmoid ) } // Salidas de las neuronas

            out.add(Pair(z, a))
        }
        return out[out.size - 1].second.flatten()
    }
}
