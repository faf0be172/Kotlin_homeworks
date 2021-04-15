package homework6
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.ui.ApplicationFrame
import org.jfree.data.xy.XYDataset
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import org.jfree.ui.RefineryUtilities
import java.awt.Dimension

class ChartCreator(numberOfAcceptableLevels: Int, cases: Int) {
    companion object {
        const val width = 1200
        const val height = 600
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
            preferredSize = Dimension(ChartCreator.width, ChartCreator.height)
        }
        frame.pack()
        RefineryUtilities.centerFrameOnScreen(frame)
        frame.isVisible = true
    }

    private fun createDataSet(maxAcceptableLevel: Int, cases: Int): XYDataset {
        val xySeriesCollection = XYSeriesCollection()
        val sortingData = getProcessingTime(maxAcceptableLevel, cases, logs = true)

        repeat(maxAcceptableLevel + 1) {
            val newXYSeries = XYSeries("${(1 shl it)} thread(-s)")
            for (entry in sortingData[it]) {
                newXYSeries.add(entry.key, entry.value)
            }
            xySeriesCollection.addSeries(newXYSeries)
        }
        return xySeriesCollection
    }
}

fun main() {
    val chartCreator = ChartCreator(numberOfAcceptableLevels = 5, cases = 1)
    chartCreator.displayChart()
}
