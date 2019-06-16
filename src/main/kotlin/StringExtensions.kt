import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import java.io.BufferedReader
import java.io.FileReader

fun String.readCsv(skipLines : Int) : CSVReader = CSVReaderBuilder(BufferedReader(FileReader(this))).withSkipLines(skipLines).build()