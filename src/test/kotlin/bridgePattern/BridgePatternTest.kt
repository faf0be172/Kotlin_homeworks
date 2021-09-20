package bridgePattern

import org.junit.jupiter.api.Test

class BridgePatternTest {
    @Test
    fun testSimpleTV() {
        val simpleTV = TV(30)
        val remoteControl = Remote(simpleTV)

        remoteControl.togglePower()
        remoteControl.volumeUp()
        remoteControl.volumeDown()
        remoteControl.volumeUp()
        remoteControl.channelDown()
        remoteControl.togglePower()
    }

    @Test
    fun testPlasmaTV() {
        val plasmaTV = TV(100)
        val advancedRemoteControl = AdvancedRemote(plasmaTV)

        advancedRemoteControl.togglePower()
        advancedRemoteControl.channelUp()
        advancedRemoteControl.volumeUp()
        advancedRemoteControl.muteVolume()
        advancedRemoteControl.togglePower()
    }
}