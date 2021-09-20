package bridgePattern

/**
 * [Remote] is a base class of abstraction part of a system
 * [Remote] contains major part of logic
 * [togglePower], [volumeDown], [volumeUp], [channelUp], [channelDown] work with primitive [device] operations
 */

open class Remote(protected var device: Device) {
    fun togglePower() {
        if (device.isEnabled()) {
            device.disable()
        } else {
            device.enable()
        }
    }

    fun volumeDown() {
        if (device.getVolume() - 1 < 0) {
            device.setVolume(0)
        } else {
            device.setVolume(device.getVolume() - 1)
        }
    }

    fun volumeUp() {
        if (device.getVolume() + 1 > device.getVolumeMax()) {
            device.setVolume(device.getVolumeMax())
        } else {
            device.setVolume(device.getVolume() + 1)
        }

    }

    fun channelDown() {
        if (device.getChannel() == 1) {
            device.setChannel(device.getChannelNumber())
        } else {
            device.setChannel(device.getChannel() - 1)
        }

    }

    fun channelUp() {
        if (device.getChannel() == device.getChannelNumber()) {
            device.setChannel(1)
        } else {
            device.setChannel(device.getChannel() + 1)
        }

    }
}

/**
 * [AdvancedRemote] is an extended [Remote] which can work with [device] same as [Remote]
 */

class AdvancedRemote(device: Device) : Remote(device) {
    fun muteVolume() {
        device.setVolume(0)
    }
}
