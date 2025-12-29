package top.cuteneko.yuchenxue.rgui.screen

abstract class Screen : Component {

    protected var buttons: Component = Component.empty()

    open fun shouldPause(): Boolean = true

    open fun shouldClose(): Boolean = true

}