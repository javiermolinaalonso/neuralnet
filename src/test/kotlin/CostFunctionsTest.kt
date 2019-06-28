import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CostFunctionsTest {

    @Test
    internal fun test() {
        val x = listOf(43.0, 44.0, 45.0, 46.0, 47.0)
        val y = listOf(41.0, 45.0, 49.0, 47.0, 44.0)
        val cost = l2_cost(x, y)
        x.zip(y).map { Math.pow(it.first - it.second, 2.0) }.forEach { println(it) }
        assertEquals(cost, 7.84)
    }
}