import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.nd4j.linalg.factory.Nd4j
import org.nd4j.linalg.ops.transforms.Transforms
import org.nd4j.linalg.api.ndarray.INDArray
import org.apache.commons.math3.util.FastMath
import org.junit.jupiter.api.Assertions.assertTrue
import org.nd4j.linalg.api.ops.impl.transforms.strict.SigmoidDerivative


class ActivationFunctionsTest {

    companion object {
        val delta = 0.001
    }
    @Test
    fun assertValidSigmoid() {
        val input = Nd4j.create(doubleArrayOf(-0.5, -0.25, 0.0, 1.0, 2.0))

        val sigmoid = Transforms.sigmoid(input)

        assertEquals(sigmoid.getDouble(0), 0.37754, delta)
        assertEquals(sigmoid.getDouble(1), 0.43782, delta)
        assertEquals(sigmoid.getDouble(2), 0.5, delta)
        assertEquals(sigmoid.getDouble(3), 0.73106, delta)
        assertEquals(sigmoid.getDouble(4), 0.8808, delta)
    }

    @Test
    internal fun assertValidSigmoidDerivative() {
        val input = Nd4j.create(doubleArrayOf(-0.5, -0.25, 0.0, 1.0, 2.0))

        val sigmoid = sigmoidDerivative(input)

        assertEquals(sigmoid.getDouble(0), -0.75, delta)
        assertEquals(sigmoid.getDouble(1), -0.3125, delta)
        assertEquals(sigmoid.getDouble(2), 0.0, delta)
        assertEquals(sigmoid.getDouble(3), 0.0, delta)
        assertEquals(sigmoid.getDouble(4), -2.0, delta)
    }

}