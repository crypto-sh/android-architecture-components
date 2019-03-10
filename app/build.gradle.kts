plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")

}
android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.testmycujoo"
        minSdkVersion(15)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    testOptions.unitTests.isIncludeAndroidResources = true
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
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    // Room components
    implementation("androidx.room:room-runtime:2.1.0-alpha04")
    kapt("androidx.room:room-compiler:2.1.0-alpha04")
    androidTestImplementation("androidx.room:room-testing:2.1.0-alpha04")

    // Lifecycle components
    implementation("android.arch.lifecycle:extensions:1.1.1")
    annotationProcessor("android.arch.lifecycle:compiler:1.1.1")

    implementation("androidx.paging:paging-runtime-ktx:2.1.0")
    implementation("androidx.lifecycle:lifecycle-runtime:2.1.0-alpha02")
    implementation("androidx.lifecycle:lifecycle-extensions:2.1.0-alpha02")

    testImplementation("junit:junit:4.12")
    testImplementation("org.robolectric:robolectric:4.2")

    androidTestImplementation("androidx.test:runner:1.1.2-alpha02")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0-alpha02")
}
