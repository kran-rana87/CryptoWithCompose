plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
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


    implementation(libs.gson)
    implementation(libs.hilt.android.testing)
    implementation(libs.hilt.android)
    implementation(libs.dagger.compiler)
    implementation(libs.hilt.dagger)
    implementation(libs.compose.uitestjunit)
    implementation(libs.compose.tooling)
    implementation(libs.okhttp.interceptor)
    implementation(libs.okhttp.client)
    implementation(libs.retrofit.client)
    implementation(libs.retrofit.gson)
    implementation(libs.compose.meterialicons)
    implementation(libs.androidx.lifecyclecompose)
    implementation(libs.lifecycle.runtime)
    implementation(libs.hilt.fragment.navigation)
    implementation(libs.hilt.compose.navigation)
    implementation(libs.lifeccyle.viewmodel.compose)
    implementation(libs.navigation.compose)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.activity)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.activity)
    implementation(libs.constraint.layout)
    implementation(libs.android.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core)

    kapt(libs.hilt.ext.compiler)

    testImplementation(libs.junit)

    androidTestImplementation(libs.espresso)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.compose.uitestmanifest)
    debugImplementation(libs.compose.tooling)
}