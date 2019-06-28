package kravis

import kravis.*
import krangl.*
import kravis.render.Docker
import readCsv

fun main() {

    SessionPrefs.RENDER_BACKEND = Docker()
    
    val df =
            DataFrame.readDelim(DataFrame::class.java.getResourceAsStream("/circles/fullDataset.csv"))

    df
            .plot(x = "X", y = "Y", color = "result")
            .geomPoint(alpha = 0.7)
            .guides(size = LegendType.none)
            .title("Correlation between dream and total sleep time")
            .show()
}

data class Points(val x : Double, val y : Double)