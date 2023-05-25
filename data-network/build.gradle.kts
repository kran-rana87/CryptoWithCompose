plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlinx-serialization")
    id("kotlin-kapt")

}

android {
    namespace = "com.karan.coingecko.demo.network"
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
    implementation(project(":common"))
    implementation(project(":data-local"))
    implementation(project(":domain"))

    kapt(libs.hilt.ext.compiler)
    implementation(libs.retrofit.client)
    implementation(libs.okhttp.interceptor)
    implementation(libs.okhttp.client)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.serialization.converter)
    implementation(libs.compose.uitestjunit)

    testImplementation(libs.mockito.core)
    testImplementation(libs.mockk.io)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.turbine)
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
}