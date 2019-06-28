import kotlin.math.max
import kotlin.math.pow

//val sigmoid : (Double) -> Double = { 1 / (1 + Math.E.pow(-it) )  }
//val sigmoidDerivate : (Double) -> Double = { it * (1 - it)  }
val sigmoid : (Double) -> Double = { 1 / (1 + Math.E.pow(-it) )  }
val sigmoidDerivate : (Double) -> Double = { it * (1 - it)  }