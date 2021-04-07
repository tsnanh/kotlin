import scala.collection.immutable.List.empty as emptyScalaList
import scala.collection.JavaConverters.asScalaBuffer
fun main() {
    // from KT-38225
    for (element in emptyScalaList<String>()) {
        println(element)
    }
    // from KT-39799
    val kotlinList: List<String> = listOf("abc", "def", "ghi")
    val scalaList = asScalaBuffer(kotlinList).toList()
    println(scalaList)
}
