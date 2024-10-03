plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.testappapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.testappapi"
        minSdk = 24
        targetSdk = 34
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
        compose = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.androidx.ui.test.junit4.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.ui.tooling)

    implementation(libs.androidx.retrofit)
    implementation(libs.androidx.retrofit.convertor.gson)
    implementation(libs.androidx.okHttp3)
    implementation(libs.androidx.logging.interceptor)
    implementation(libs.androidx.gson)

    implementation(libs.androidx.dagger.hilt)
    kapt(libs.androidx.dagger.hilt.compiler)
    kapt(libs.androidx.hilt.compiler.kapt)
    implementation(libs.androidx.hilt.navigation.compose.v120)

    implementation(libs.androidx.lifecycle.viewmodel.compose.v286)
    implementation(libs.androidx.runtime.livedata.v172)
    implementation(libs.accompanist.systemuicontroller)

    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)

    testImplementation(libs.androidx.core)

    testImplementation(libs.kotlinx.coroutines.test)

    testImplementation(libs.truth)
    androidTestImplementation(libs.androidx.core.testing)

    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.test.manifest)

    testImplementation(libs.hilt.android.testing)
    kaptTest(libs.androidx.dagger.hilt.compiler)

    androidTestImplementation(libs.dagger.hilt.android.testing)
    kaptAndroidTest(libs.androidx.dagger.hilt.compiler)
}

kapt {
    correctErrorTypes = true
}