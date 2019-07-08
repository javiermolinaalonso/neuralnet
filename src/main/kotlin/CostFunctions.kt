import org.nd4j.linalg.api.ndarray.INDArray
import java.lang.Math.pow

val l2_cost: (INDArray, INDArray) -> Double = {
//    x, y -> (1.0 / x.size) * (x.zip(y).map { pow(it.first - it.second, 2.0) }.sum())
//    x, y -> (0 until x.size).map { pow(x[it] - y[it], 2.0) }.average()
    x, y -> (0 until x.size(0)).map { pow(x.getDouble(it) - y.getDouble(it), 2.0) }.average()
}

val l2_cost_derivate: (INDArray, INDArray) -> INDArray = {
    x, y -> x.sub(y)
}
