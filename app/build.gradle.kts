plugins {
    alias(libs.plugins.android.application)
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.themovieapp2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.themovieapp2"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
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

    // retrofit
    //noinspection UseTomlInstead
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    // retrofit gson converter
    //noinspection UseTomlInstead
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    // RxJava3 with retrofit
    //noinspection UseTomlInstead
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.11.0")

    // paging library
    val pagingVersion = "3.3.0"
    //noinspection UseTomlInstead
    implementation("androidx.paging:paging-runtime:$pagingVersion")
    // optional - RxJava3 support
    //noinspection UseTomlInstead
    implementation("androidx.paging:paging-rxjava3:$pagingVersion")

    // hilt dagger
    //noinspection UseTomlInstead
    implementation ("com.google.dagger:hilt-android:2.51.1")
    //noinspection UseTomlInstead
    annotationProcessor ("com.google.dagger:hilt-compiler:2.51.1")

    // glide
    //noinspection UseTomlInstead
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    //noinspection UseTomlInstead
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")

    // lifecycle
    val lifecycleVersion = "2.8.0"
    // ViewModel
    //noinspection UseTomlInstead
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    // LiveData
    //noinspection UseTomlInstead
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
}