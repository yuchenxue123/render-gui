pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()

        maven {
            name = "Fabric"
            url = uri("https://maven.fabricmc.net/")
        }

        maven {
            url = uri("https://gitlab.com/mite-mod/repo/raw/main/maven")
        }
    }
}