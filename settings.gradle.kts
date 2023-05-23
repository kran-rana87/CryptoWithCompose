pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Crypto Coin Demo"
include("app")
include(":data-local")
include(":domain")
include(":data-network")
include(":common")
