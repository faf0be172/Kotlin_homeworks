package bridgePattern

const val plasmaTVChannelNumber = 30
const val simpleTVChannelNumber = 100

fun main() {

    val simpleTV = TV(plasmaTVChannelNumber)
    val plasmaTV = TV(simpleTVChannelNumber)

    val remoteControl = Remote(simpleTV)
    remoteControl.togglePower()

    remoteControl.volumeUp()
    remoteControl.volumeDown()
    remoteControl.channelDown()
    remoteControl.togglePower()

    val advancedRemoteControl = AdvancedRemote(plasmaTV)
    advancedRemoteControl.togglePower()

    advancedRemoteControl.channelUp()
    advancedRemoteControl.volumeUp()
    advancedRemoteControl.muteVolume()
    advancedRemoteControl.togglePower()
}
