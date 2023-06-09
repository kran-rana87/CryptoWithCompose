plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
}

android {
    namespace = "com.karan.flow.demo"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.karan.coingecko.demo"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":data-local"))
    implementation(project(":domain"))
    implementation(project(":data-network"))
    implementation(project(":common"))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.lifecyclecompose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.serialization.converter)

    implementation(libs.lifecycle.runtime)
    implementation(libs.lifeccyle.viewmodel.compose)

    implementation(libs.android.material)
    implementation(libs.constraint.layout)

    implementation(libs.hilt.android.testing)
    implementation(libs.hilt.android)
    implementation(libs.hilt.dagger)
    implementation(libs.hilt.fragment.navigation)
    implementation(libs.hilt.compose.navigation)
    implementation(libs.dagger.compiler)

    implementation(libs.compose.uitestjunit)
    implementation(libs.compose.tooling)

    implementation(libs.retrofit.client)
    implementation(libs.okhttp.interceptor)
    implementation(libs.okhttp.client)

    implementation(libs.navigation.compose)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    implementation(libs.coil.compose)


    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.activity)
    implementation(libs.compose.meterialicons)
    implementation(libs.compose.ui.toolings)


    kapt(libs.hilt.ext.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.compose.uitestmanifest)
    debugImplementation(libs.compose.tooling)

    testImplementation(libs.mockito.core)
    testImplementation(libs.mockk.io)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.turbine)

}