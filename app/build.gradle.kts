import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.compose.compiler)
    //type safe compose navigation
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.get_ready.weather_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.get_ready.weather_app"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        // Load the API token from local.properties
        val apiPropertiesFile = rootProject.file("local.properties")
        val apiProperties = Properties().apply {
            load(apiPropertiesFile.inputStream())
        }
        val apiKey = apiProperties["OpenWeatherMap_API_KEY"]

        buildConfigField("String", "SERVICE_API_KEY", "$apiKey")
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
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
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
    //Dagger Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    // OK HTTP
    implementation (libs.okhttp)
    // Logging Interceptor
    implementation (libs.logging.interceptor)
    //type safe compose navigation
    implementation (libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    // hiltViewModel  for Compose
    implementation (libs.androidx.hilt.navigation.compose)
    //Data Store Preference
    implementation(libs.androidx.datastore.preferences)
    // For Kotlin Coroutines test
    testImplementation(libs.kotlinx.coroutines.test)
    // For Mockito
    testImplementation(libs.mockito.core)
    // Mockito-Kotlin
    testImplementation(libs.mockito.kotlin)
    testImplementation (libs.kotlin.test)
    testImplementation (libs.androidx.core.testing)


    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":features"))
    implementation(project(":features:currentWeather"))
    implementation(project(":features:cityInput"))
    implementation(project(":features:dailyForecasts"))
    implementation(project(":core"))
}