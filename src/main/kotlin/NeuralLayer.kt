import kotlin.random.Random

class NeuralLayer(private val wSize:Int, private val numNeurons:Int) {
    var b = List(numNeurons) { Random.nextDouble(-1.0, 1.0)}
    var W = List(wSize) { List(numNeurons) {Random.nextDouble(-1.0, 1.0)} }
}