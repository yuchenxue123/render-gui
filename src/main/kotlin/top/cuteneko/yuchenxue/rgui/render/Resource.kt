package top.cuteneko.yuchenxue.rgui.render

class Resource(private val path: String) {

    fun content() = this.javaClass.getResourceAsStream("/$path")?.use {
        it.bufferedReader().readText()
    } ?: ""

    fun path() = path

}