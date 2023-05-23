plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.karan.coingecko.demo.data.local"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.data.store)
    api(libs.hilt.android.testing)
    api(libs.hilt.android)
    api(libs.hilt.dagger)
    api(libs.hilt.fragment.navigation)
    api(libs.hilt.compose.navigation)
    api(libs.dagger.compiler)
    kapt(libs.hilt.ext.compiler)

}