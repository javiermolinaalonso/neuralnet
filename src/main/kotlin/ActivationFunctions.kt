import kotlin.math.max
import kotlin.math.pow

val relu : (Double) -> Double = { max(0.0, it) }
val sigmoid : (Double) -> Double = { 1 / (1 + Math.E.pow(-it) )  }
val sigmoidDerivate : (Double) -> Double = { it * (1 - it)  }

val sigmoidPair : Pair<(Double) -> Double, (Double) -> Double> = Pair(sigmoid, sigmoidDerivate)