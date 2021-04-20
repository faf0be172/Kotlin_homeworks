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

        for (level in 0..maxAcceptableLevel) {
            val newXYSeries = XYSeries("${(1 shl level)} thread(-s)")
            sortingData[level].forEach {
                newXYSeries.add(it.key, it.value)
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
