import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.factory.Nd4j
import kotlin.math.max
import kotlin.math.pow

//val sigmoid : (Double) -> Double = { 1 / (1 + Math.E.pow(-it) )  }
//val sigmoidDerivate : (Double) -> Double = { it * (1 - it)  }
val sigmoid : (Double) -> Double = { 1 / (1 + Math.E.pow(-it) )  }
private val sigmoidDerivate : (Double) -> Double = { it * (1 - it)  }


val sigmoidDerivative : (INDArray) -> (INDArray) = {
    x -> if (x.columns() == 1) {
    Nd4j.create(x.toDoubleVector().map(sigmoidDerivate).toDoubleArray()).reshape(x.rows().toLong(), x.columns().toLong())
} else {
    Nd4j.create(x.toDoubleMatrix().flatMap { it.map(sigmoidDerivate) }.toDoubleArray()).reshape(x.rows().toLong(), x.columns().toLong())
}
}