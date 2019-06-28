import kotlin.math.round

public fun <E> List<List<E>>.transpose(): List<List<E>> {
    val ret = ArrayList<List<E>>()
    val N = this[0].size
    for (i in 0 until N) {
        val col = ArrayList<E>()
        for (row in this) {
            col.add(row[i])
        }
        ret.add(col)
    }
    return ret
}

public operator fun List<List<Double>>.times(matrix: List<List<Double>>): List<List<Double>> {
    val r1 = this.size
    val c1 = this[0].size
    val c2 = matrix[0].size

    val product = Array(r1) { DoubleArray(c2) }
    for (i in 0..r1 - 1) {
        for (j in 0..c2 - 1) {
            for (k in 0..c1 - 1) {
                product[i][j] += this[i][k] * matrix[k][j]
            }
        }
    }
    return product.map { it.toList() }
}

public fun List<List<Double>>.multiplyVector(vector: List<Double>): List<Double> {
    val r1 = this.size
    val c1 = this[0].size
    val c2 = vector.size

    val product = DoubleArray(c2)
    for (i in 0..r1 - 1) {
        for (k in 0..c1 - 1) {
            product[i] += this[i][k] * vector[k]
        }
    }
    return product.toList()
}

public fun List<List<Double>>.sumVector(vector: List<Double>): List<List<Double>> {
    val r1 = this.size
    val c1 = this[0].size
    val c2 = vector.size

    val sum = Array(r1) { DoubleArray(c2) }
    for (i in 0..r1 - 1) {
        for (k in 0..c1 - 1) {
            sum[i][k] += this[i][k] + vector[k]
        }
    }
    return sum.map { it.toList() }
}


public fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}