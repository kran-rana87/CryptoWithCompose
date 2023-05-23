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
     // TODO Remove 'domain' dependency by using UseCases in the domain Layer
    implementation(project(":domain"))
    implementation(project(":data-local"))

    kapt(libs.hilt.ext.compiler)
    implementation(libs.retrofit.client)
    implementation(libs.okhttp.interceptor)
    implementation(libs.okhttp.client)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.serialization.converter)
}