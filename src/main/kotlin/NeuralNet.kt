import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.factory.Nd4j
import org.nd4j.linalg.ops.transforms.Transforms
import java.lang.Math.random
import java.util.*
import kotlin.collections.ArrayList


class NeuralNet(val topology: List<Int>) {

    private val layers = (0 .. topology.size - 2).map { NeuralLayer(topology[it], topology[it + 1]) }

    fun train(X: INDArray, Y: INDArray, learningRate: Double) : INDArray {
        //forward pass
        val out = ArrayList<Pair<INDArray, INDArray>>()
        out.add(Pair(Nd4j.empty(), X)) //Inicializar

        layers.forEachIndexed { index, layer ->
            val z: INDArray = (out[out.size - 1].second.mmul(layer.W)).addRowVector(layer.b) //Sumas ponderadas de las neuronas
            val a: INDArray = Transforms.sigmoid(z)
            out.add(Pair(z, a))
        }

        val deltas = LinkedList<INDArray>()
        //Backward pass
        for (l in layers.size - 1 downTo 0) {
            val a = out[l + 1].second
            if (l == layers.size - 1) {
                deltas.add(a.sub(Y).mul(Transforms.sigmoid(a.sub(Y))))
            } else {

                val derivateLayer = Transforms.sigmoidDerivative(a)
                val matrixMult = deltas[0].mmul(layers[l+1].W.transpose())

                val r = derivateLayer.mul(matrixMult)
                deltas.addFirst(r)
            }

            //Gradient descent
            val mean = deltas[0].transpose().mul(learningRate)//Calculamos la media de los valores de entrada (La traspuesta es pq queremos la primera columna)
            layers[l].b = layers[l].b.sub(mean)

            val matrix = out[l].second.transpose().mmul(deltas[0])
            val matrixLr = matrix.mul(learningRate)

            layers[l].W = layers[l].W.sub(matrixLr)
        }

        return out[out.size - 1].second
    }

    fun predict(X: INDArray): INDArray {
        //forward pass
        val out = ArrayList<Pair<INDArray, INDArray>>()
        out.add(Pair(Nd4j.empty(), X)) //Inicializar

        layers.forEachIndexed { index, layer ->
            val z: INDArray = (out[out.size - 1].second.mmul(layer.W)).sum(layer.b) //Sumas ponderadas de las neuronas
            val a: INDArray = Transforms.sigmoid(z)
            out.add(Pair(z, a))
        }
        return out[out.size - 1].second
    }
}

data class NeuralLayer(private val wSize:Int, private val numNeurons:Int) {
    var b = Nd4j.create((0 until numNeurons).map { random() }.toDoubleArray()).reshape(1, numNeurons.toLong())
    var W = Nd4j.create((0 until wSize * numNeurons).map { random() }.toDoubleArray()).reshape(wSize.toLong(), numNeurons.toLong())
}
data class Neuron(var weights : List<Double>)