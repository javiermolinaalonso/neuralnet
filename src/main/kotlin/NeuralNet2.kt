class NeuralNet2(val topology: List<Int>, val activation: Pair<(Double) -> Double, (Double) -> Double> = sigmoidPair) {

    val layers = topology.mapIndexed { index, element ->
        NeuralLayer(if (index == 0) element else topology[index - 1], element, activation = activation.first)
    }

    fun train(input: List<List<Double>>, Y: List<Double>, costFunction: (List<Double>, List<Double>) -> Double = l2_cost, learningRate: Double) {
//        forward pass
//        val out = predict(input)
//        val resultsLastLayer: List<List<Double>> = out[out.size - 1].map { it }
//
////        BackProp
//        val deltas: LinkedList<List<List<Double>>> = LinkedList()
//
//        val layersReversed = layers.reversed()
//
//        layersReversed.forEachIndexed { index, layer ->
//            if (index == 0) {
////                deltas.insert(0, l2_cost[1](a, Y) * neural_net[l].act_f[1](a))
//                deltas.addFirst(listOf(l2_cost_derivate(resultsLastLayer.flatten(), Y).map(activation.second)))
//            } else {
//                val deltasNextLayer: List<List<Double>> = deltas[0]
//                val weightsNextLayer: List<List<Double>> = layersReversed[index - 1].neurons.map { it.weights }.transpose()
//
//                val product = matrixMultiplication(deltasNextLayer, weightsNextLayer)
//
//
//                val map: List<List<Double>> = product.map { it.map(activation.second) }
//                deltas.addFirst(map)
//            }
//        }

    }


    private fun predict(input: List<List<Double>>): List<List<List<Double>>> {
        return layers.mapIndexed { index, layer ->
            input.mapIndexed { xIndex, x ->
                layer.neurons.map { it.calculate(x) } }
        }
    }
}