import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.factory.Nd4j

val sigmoidDerivative: (INDArray) -> (INDArray) = { x ->
    if (x.columns() == 1) {
        Nd4j.create(x.toDoubleVector().map { it * (1 - it) }.toDoubleArray()).reshape(x.rows().toLong(), x.columns().toLong())
    } else {
        Nd4j.create(x.toDoubleMatrix().flatMap { it.map { it * (1 - it) } }.toDoubleArray()).reshape(x.rows().toLong(), x.columns().toLong())
    }
}