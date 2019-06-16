import java.util.*
import kotlin.collections.ArrayList


class NeuralNet(val topology: List<Int>, val activation: Pair<(Double) -> Double, (Double) -> Double> = sigmoidPair) {

    val layers = topology.mapIndexed { index, element ->
        NeuralLayer(if (index == 0) element else topology[index - 1], element, activation = activation.first)
    }

    fun train(X: List<List<Double>>, Y: List<Double>, learningRate: Double) {
        //forward pass
        val out = ArrayList<Pair<List<List<Double>>, List<List<Double>>>>()
        out.add(Pair(emptyList(), X)) //Inicializar
        layers.forEachIndexed { index, layer ->
            val z = (out[out.size - 1].second * layer.W).sumVector(layer.b) //Sumas ponderadas de las neuronas
            val a = z.map { it.map(layer.activation) } // Salidas de las neuronas

            out.add(Pair(z, a))
        }

        print(l2_cost(out[out.size - 1].second.flatten(), Y))

        val deltas = LinkedList<List<List<Double>>>()
        //Backward pass
        for (l in layers.size - 1 downTo 0) {
            val z = out[l + 1].first
            val a = out[l + 1].second
            if (l == layers.size - 1) {
                val derivateActivationLastLayer: List<Double> = a.flatten().map(activation.second)
                val derivateCostFunctionVersusExpectedOutput: List<Double> = l2_cost_derivate(a.flatten(), Y)
                val deltaLastLayer: List<List<Double>> = derivateActivationLastLayer.zip(derivateCostFunctionVersusExpectedOutput).map { listOf(it.first * it.second) }
                deltas.add(deltaLastLayer) //necesario pq necesitamos una matriz de x.size x 1
            } else {
                val derivateCostFunctionVersusExpectedOutput: List<Double> = l2_cost_derivate(a.flatten(), Y)
                val derivateActivationLayer = deltas[0] * layers[l+1].W.transpose()
                val deltaCurrentLayer: List<List<Double>> = derivateActivationLayer.map { derivateCostFunctionVersusExpectedOutput.zip(it).map { it.first * it.second } }
                deltas.addFirst(deltaCurrentLayer)
            }
        }

    }

    private fun predict(input: List<List<Double>>): List<List<List<Double>>> {
        return layers.mapIndexed { index, layer ->
            input.mapIndexed { xIndex, x ->
                layer.neurons.map { it.calculate(x) }
            }
        }
    }
}

