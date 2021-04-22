package homework6
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.ui.ApplicationFrame
import org.jfree.data.xy.XYDataset
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import org.jfree.ui.RefineryUtilities
import java.awt.Dimension
import kotlin.random.Random

class ChartCreator(numberOfAcceptableLevels: Int, cases: Int) {
    companion object {
        const val WIDTH = 1200
        const val HEIGHT = 600
    }
    private val chart = ChartFactory.createXYLineChart(
        "MultiThreads sort",
        "number of elements",
        "processing time, ms",
        createDataSet(numberOfAcceptableLevels, cases))

    fun displayChart() {
        val frame = ApplicationFrame("Chart")
        frame.contentPane = ChartPanel(this.chart).apply {
            fillZoomRectangle = true
            isMouseWheelEnabled = true
            preferredSize = Dimension(WIDTH, HEIGHT)
        }
        frame.pack()
        RefineryUtilities.centerFrameOnScreen(frame)
        frame.isVisible = true
    }

    private fun createDataSet(maxAcceptableLevel: Int, cases: Int): XYDataset {
        val xySeriesCollection = XYSeriesCollection()
        val sortingProcessor = SortingProcessor()
        val sortingData = sortingProcessor.getProcessingTime(maxAcceptableLevel, cases, logs = true)

        var newXYSeries = XYSeries("single thread")
        sortingData[0].forEach {
            newXYSeries.add(it.key, it.value)
        }

        xySeriesCollection.addSeries(newXYSeries)
        for (level in 1..maxAcceptableLevel) {
            newXYSeries = XYSeries("~ ${1 shl (level + 1)} thread(-s)")
            sortingData[level].forEach {
                newXYSeries.add(it.key, it.value)
            }
            xySeriesCollection.addSeries(newXYSeries)
        }
        return xySeriesCollection
    }
}

fun main() {
    val randomList = List(20) { Random.nextInt(0, 30)}
    println(randomList)
    val chartCreator = ChartCreator(numberOfAcceptableLevels = 6, cases = 1)
    chartCreator.displayChart()
}
