
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    id("kotlin-parcelize")
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.example.store"

    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.store"
        versionCode = 1
        versionName = "1.0"
        minSdk = 28
        targetSdk = 35
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    flavorDimensions += "environment"
    productFlavors {
        create("mock") {
            dimension = "environment"
        }
        create("prod") {
            dimension = "environment"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")

        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

}

dependencies {

    implementation(libs.work.manager)
    implementation(libs.hilt.work)
    implementation(libs.datastore.preference)
    implementation(libs.androidx.splashscreen)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(platform(libs.firebase.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material3.adaptive.navigation.suite)
    implementation(libs.androidx.material3.adaptive.layout)
    implementation(libs.androidx.material3.adaptive.navigation)
    implementation(libs.androidx.material3.windowSizeClass)




    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp.logging.interceptor)


    // analytics
    implementation(libs.firebase.analytics)

    // firebase cloud messaging
   // implementation(libs.firebase.cloud.messaging)

    // Serialization
    implementation(libs.kotlinx.serialization.json)

    // M3 icons
    implementation(libs.androidx.material3.icons)

    //Room database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // google maps
    implementation(libs.google.maps.compose)
    implementation(libs.play.services.location)

    // Navigation
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.compose.navigation.ui)

    // Coil
    implementation(libs.coil)
    implementation(libs.lottie.compose)

    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    testImplementation(libs.junit)
    testImplementation(kotlin("test"))
    testImplementation(libs.mockk)
    testImplementation(libs.androidx.core.test)
    testImplementation(libs.androidx.room.test)
    testImplementation(libs.kotlin.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}