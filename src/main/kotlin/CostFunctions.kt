import org.nd4j.linalg.api.ndarray.INDArray
import java.lang.Math.pow

val l2_cost: (INDArray, INDArray) -> Double = {
    x, y -> (0 until x.size(0)).map { pow(x.getDouble(it) - y.getDouble(it), 2.0) }.average()
}

val l2_cost_derivate: (INDArray, INDArray) -> INDArray = {
    x, y -> x.sub(y)
}
