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
    implementation ("com.amap.api:location:6.4.7")
    implementation ("com.squareup.okhttp3:okhttp:4.12.0")

    // 下拉刷新
    implementation ("in.srain.cube:ultra-ptr:1.0.11")
    // 圆形指示器
    implementation ("me.relex:circleindicator:2.1.6")
    // Room数据库
    implementation("androidx.room:room-runtime-android:2.7.0-alpha07")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
}