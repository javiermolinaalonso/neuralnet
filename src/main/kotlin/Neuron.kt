import kotlin.random.Random

class Neuron(
        val weights: List<Double> = emptyList(),
        val biasWeight: Double = Random.nextDouble(-1.0, 1.0),
        val activation: (Double) -> Double = sigmoid
) {

    fun calculate(input: List<Double> = emptyList(),
                  bias: Double = 0.0)
            : Double {
        return activation(bias * biasWeight + input.zip(weights) { x, y -> x * y }.sum())
    }

    fun error() {

    }
}