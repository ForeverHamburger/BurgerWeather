plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.mvpburgerweahter"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mvpburgerweahter"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true;
    }

}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("com.google.code.gson:gson:2.11.0")
    implementation("com.amap.api:location:latest.integration")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("in.srain.cube:ultra-ptr:1.0.11")
    implementation ("me.relex:circleindicator:2.1.6")
}