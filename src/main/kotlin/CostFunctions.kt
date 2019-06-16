import java.lang.Math.pow

val l2_cost: (List<Double>, List<Double>) -> Double = {
    x, y -> (1.0 / x.size) * (x.zip(y).map { pow(it.first - it.second, 2.0) }.sum())
}
val l2_cost_derivate: (List<Double>, List<Double>) -> List<Double> = { x, y -> y.zip(x).map { it.first - it.second }}
