package bridgePattern

import java.lang.IllegalStateException

/**
 * [Device] is a base interface of different devices
 * [Device] contains primitive operations
 */

interface Device {
    fun enable()
    fun disable()
    fun isEnabled(): Boolean

    fun setVolume(percent: Int)
    fun getVolume(): Int

    fun setChannel(channel: Int)
    fun getChannel(): Int
    fun getChannelNumber(): Int
    fun getVolumeMax(): Int
}

/**
 * [TV] is a concrete implementation of [Device]
 */

class TV(private val channelNumber: Int): Device {

    companion object {
        private const val deviceVolumeMax = 100
    }

    private var isDeviceEnabled = false
    private var volumeLevel = 0
    private var channelLevel = 1

    override fun enable() {
        isDeviceEnabled = true
    }

    override fun disable() {
        isDeviceEnabled = false
    }

    override fun setChannel(channel: Int) {
        if (isDeviceEnabled) {
            if (channel > channelNumber || channel <= 0) {
                throw IllegalStateException("Incorrect channel number")
            }
            channelLevel = channel
        }
    }

    override fun setVolume(percent: Int) {
        if (isDeviceEnabled) {
            if (percent <= 0 || percent > deviceVolumeMax) {
                throw IllegalStateException("Incorrect volume percent")
            }
            volumeLevel = percent
        }
    }

    override fun isEnabled() = isDeviceEnabled
    override fun getVolume() = volumeLevel
    override fun getChannel() = channelLevel
    override fun getChannelNumber() = channelNumber
    override fun getVolumeMax() = deviceVolumeMax
}
