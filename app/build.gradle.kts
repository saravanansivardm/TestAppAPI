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

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation(platform("androidx.compose:compose-bom:2024.09.02"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation(libs.androidx.junit.ktx)
    implementation(libs.androidx.ui.test.junit4.android)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    debugImplementation("androidx.compose.ui:ui-tooling")

    //Retrofit
    implementation(libs.androidx.retrofit)
    implementation(libs.androidx.retrofit.convertor.gson)
    //Client
    implementation(libs.androidx.okHttp3)
    //OkHttp Interceptor
    implementation(libs.androidx.logging.interceptor)
    //Gson
    implementation(libs.androidx.gson)

    // Hilt
    implementation(libs.androidx.dagger.hilt)
    kapt(libs.androidx.dagger.hilt.compiler)
    kapt(libs.androidx.hilt.compiler.kapt)
    implementation(libs.androidx.hilt.navigation.compose.v120)

    implementation(libs.androidx.lifecycle.viewmodel.compose.v286)
    implementation(libs.androidx.runtime.livedata.v172)
    implementation(libs.accompanist.systemuicontroller)

    testImplementation("org.mockito:mockito-core:5.0.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")

    testImplementation("androidx.test:core:1.5.0")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

    testImplementation(libs.truth)
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")

    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.7.2")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.7.2")

    testImplementation(libs.hilt.android.testing)
    kaptTest(libs.androidx.dagger.hilt.compiler)

    androidTestImplementation(libs.dagger.hilt.android.testing)
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.51.1")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}