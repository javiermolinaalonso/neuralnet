import kotlin.random.Random

class NeuralLayer(val wSize:Int, val numNeurons:Int, val activation:(Double) -> Double) {
//    fun activation(z: List<List<Double>>): Double {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

//    val bias = List(neurons.size) { Random.nextDouble(-1.0, 1.0)}

    val neurons : List<Neuron> = List(numNeurons) { Neuron(List(wSize) {Random.nextDouble(-1.0, 1.0)})}
    val b = this.neurons.map { it.biasWeight}
    val W = this.neurons.map { it.weights }.transpose()

}