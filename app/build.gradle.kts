plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.alishatergholi"
        minSdkVersion(18)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    testOptions.unitTests.isReturnDefaultValues = true
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("Boolean","enableDebugLogging","false")
        }
        getByName("debug"){
            buildConfigField("Boolean","enableDebugLogging","true")
        }
    }
    dataBinding.isEnabled = true

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.21")
    implementation("org.koin:koin-android-architecture:0.9.1")
    implementation("androidx.core:core-ktx:1.1.0-alpha04")
    implementation("androidx.appcompat:appcompat:1.1.0-alpha02")
    implementation("androidx.recyclerview:recyclerview:1.0.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    // Room components
    implementation("android.arch.persistence.room:runtime:1.1.1")
    kapt("android.arch.persistence.room:compiler:1.1.1")
    androidTestImplementation("android.arch.persistence.room:testing:1.1.1")

    // Lifecycle components
    implementation("androidx.lifecycle:lifecycle-runtime:2.1.0-alpha02")
    implementation("androidx.lifecycle:lifecycle-extensions:2.1.0-alpha02")
    kapt("androidx.lifecycle:lifecycle-compiler:2.1.0-alpha02")

    //paging library
    implementation("androidx.paging:paging-runtime-ktx:2.1.0")

    testImplementation("junit:junit:4.12")
//    testImplementation("org.robolectric:robolectric:4.2")
    testImplementation("org.mockito:mockito-core:2.22.0")
    androidTestImplementation("org.mockito:mockito-android:2.22.0")
    androidTestImplementation("androidx.test:rules:1.1.1")
    androidTestImplementation("androidx.test:runner:1.1.1")
    androidTestImplementation("org.hamcrest:hamcrest-library:1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.1")
    androidTestImplementation("androidx.test.uiautomator:uiautomator:2.2.0")
    androidTestImplementation("androidx.arch.core:core-testing:2.0.0")
}