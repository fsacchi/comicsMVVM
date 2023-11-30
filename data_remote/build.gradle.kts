import com.android.build.api.dsl.LibraryBuildType
import java.util.Properties

plugins {
    id ("com.android.library")
    id("org.jetbrains.kotlin.android")
}

val properties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

val apiKey = properties.getProperty("api_key")
val apiTs = properties.getProperty("api_ts")
val apiHash = properties.getProperty("api_hash")
val apiUrl = "https://gateway.marvel.com/v1/public/"

android {
    namespace = "com.zig.data_remote"
    compileSdk = 33

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            defineBuildConfigTypes(this)
        }

        release {
            defineBuildConfigTypes(this)

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":data"))

    implementation ("com.jakewharton.timber:timber:5.0.1")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation ("io.insert-koin:koin-android:3.1.2")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

fun defineBuildConfigTypes(library: LibraryBuildType) {
    library.apply {
        buildConfigField("String", "API_KEY", apiKey)
        buildConfigField("String", "API_TS", apiTs)
        buildConfigField("String", "API_HASH", apiHash)
        buildConfigField("String", "API_URL", "\"${apiUrl}\"")
    }
}
