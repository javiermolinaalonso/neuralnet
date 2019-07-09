import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.factory.Nd4j
import org.nd4j.linalg.ops.transforms.Transforms
import kotlin.collections.ArrayList
import kotlin.random.Random


class NeuralNet(val topology: List<Int>) {

    private val layers = (0 .. topology.size - 2).map { NeuralLayer(topology[it], topology[it + 1]) }

    fun train(input: INDArray, expectedOutput: INDArray, learningRate: Double) : INDArray {
        //forward pass
        val out = predict(input)

        //Backward pass
        var delta:INDArray = Nd4j.empty()
        for (l in layers.size - 1 downTo 0) {
            val nextLayerOutput = out[l + 1]

            val deltaFromNextLayer = if (l == layers.size - 1) {
                nextLayerOutput.sub(expectedOutput)
            } else {
                delta.mmul(layers[l + 1].W.transpose())
            }
            delta = sigmoidDerivative(nextLayerOutput).mul(deltaFromNextLayer)

            //Gradient descent
            layers[l].b.subi(delta.mean(0).mul(learningRate))
            layers[l].W.subi(out[l].transpose().mmul(delta).mul(learningRate))
        }

        return out.last()
    }

    fun predict(X: INDArray): List<INDArray> {
        //forward pass
        val out = ArrayList<INDArray>()
        out.add(X) //Inicializar

        layers.forEach {
            out.add(Transforms.sigmoid((out[out.size - 1].mmul(it.W)).addRowVector(it.b)))
        }

        return out
    }
}

data class NeuralLayer(private val wSize:Int, private val numNeurons:Int) {
    var b = Nd4j.create((0 until numNeurons).map { Random.nextDouble(-1.0, 1.0) }.toDoubleArray()).reshape(1, numNeurons.toLong())
    var W = Nd4j.create((0 until wSize * numNeurons).map { Random.nextDouble(-1.0, 1.0) }.toDoubleArray()).reshape(wSize.toLong(), numNeurons.toLong())
}