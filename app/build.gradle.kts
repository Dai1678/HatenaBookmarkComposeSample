import java.util.*

plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-parcelize")
  id("kotlin-kapt")
  id("dagger.hilt.android.plugin")
  id("org.jmailen.kotlinter")
  id("com.cookpad.android.plugin.license-tools")
  id("com.dipien.releaseshub.gradle.plugin")
}

android {
  compileSdk = Versions.compileSdkVersion
  buildToolsVersion = Versions.buildToolsVersion

  defaultConfig {
    applicationId = "dev.dai.sample"
    minSdk = Versions.minSdkVersion
    targetSdk = Versions.targetSdkVersion
    versionCode = generateVersionCode()
    versionName = generateVersionName()

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  signingConfigs {
    getByName("debug") {
      storeFile = rootProject.file("debug.keystore")
      storePassword = "android"
      keyAlias = "androiddebugkey"
      keyPassword = "android"
    }
  }

  buildTypes {
    getByName("debug") {
      isDebuggable = true
      isMinifyEnabled = false
      isShrinkResources = false
      applicationIdSuffix = ".debug"
      signingConfig = signingConfigs.getByName("debug")
    }
    create("demo") {
      isDebuggable = true
      isMinifyEnabled = true
      isShrinkResources = true
      applicationIdSuffix = ".demo"
      signingConfig = signingConfigs.getByName("debug")
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
    getByName("release") {
      isMinifyEnabled = true
      isShrinkResources = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  buildFeatures {
    compose = true

    // Disable unused AGP features
    buildConfig = false
    aidl = false
    renderScript = false
    resValues = false
    shaders = false
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }

  composeOptions {
    kotlinCompilerExtensionVersion = "1.0.0-beta07"
  }

  lint {
    isAbortOnError = false
  }

  packagingOptions {
    exclude("META-INF/AL2.0")
    exclude("META-INF/LGPL2.1")
  }
}

kotlinter {
  ignoreFailures = true
  reporters = arrayOf("checkstyle", "plain")
}

releasesHub {
  dependenciesClassNames = listOf("Versions.kt")
  pullRequestsMax = 10
  baseBranch = "develop"
  gitHubRepositoryOwner = "Dai1678"
  gitHubRepositoryName = "android-app-template"
}

fun generateVersionCode(): Int {
  val calendar = Calendar.getInstance(Locale.JAPAN)
  val year = calendar.get(Calendar.YEAR) * 552960
  val month = (calendar.get(Calendar.MONTH) + 1) * 46080
  val day = calendar.get(Calendar.DAY_OF_MONTH) * 1440
  val hour = calendar.get(Calendar.HOUR_OF_DAY) * 60
  val minutes = calendar.get(Calendar.MINUTE)
  return year + month + day + hour + minutes
}

fun generateVersionName(): String {
  val calendar = Calendar.getInstance(Locale.JAPAN).apply {
    firstDayOfWeek = Calendar.SUNDAY
    minimalDaysInFirstWeek = 4
  }
  val year = (calendar.get(Calendar.YEAR) % 100)
  val week = calendar.get(Calendar.WEEK_OF_YEAR)

  val firstDayOfWeek = Calendar.getInstance(Locale.JAPAN).apply {
    set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
  }
  val diffTime = calendar.timeInMillis - firstDayOfWeek.timeInMillis
  val diffHourMillis = 1000 * 60 * 60
  val diffHours = (diffTime / diffHourMillis).toInt()

  return "$year.$week.$diffHours"
}

dependencies {
  implementation(Versions.Accompanist.coil)
  implementation(Versions.Accompanist.insets)
  implementation(Versions.Accompanist.pager)
  implementation(Versions.Accompanist.systemUiController)
  implementation(Versions.Accompanist.swipeRefreshLayout)
  implementation(Versions.activity)
  implementation(Versions.Compose.animation)
  implementation(Versions.Compose.compiler)
  implementation(Versions.Compose.foundation)
  implementation(Versions.Compose.layout)
  implementation(Versions.Compose.iconsExtended)
  implementation(Versions.Compose.material)
  implementation(Versions.Compose.runtime)
  implementation(Versions.Compose.ui)
  implementation(Versions.Compose.uiTest)
  implementation(Versions.Compose.uiTooling)
  implementation(Versions.Compose.uiUtil)
  implementation(Versions.constraintLayout)
  implementation(Versions.core)
  implementation(Versions.coroutine)
  implementation(Versions.gson)
  implementation(Versions.Hilt.hilt)
  implementation(Versions.Kotlin.stdlib)
  implementation(Versions.Lifecycle.liveData)
  implementation(Versions.Lifecycle.runtime)
  implementation(Versions.Lifecycle.viewModel)
  implementation(Versions.Lifecycle.viewModelCompose)
  implementation(Versions.Lifecycle.viewModelSavedState)
  implementation(Versions.Okhttp3.loggingInterceptor)
  implementation(Versions.Okhttp3.okhttp3)
  implementation(Versions.material)
  implementation(Versions.navigation)
  implementation(Versions.retrofit2)
  implementation(Versions.timber)
  kapt(Versions.Hilt.compiler)
  testImplementation(Versions.ArchCore.testing)
  testImplementation(Versions.junit)
  testImplementation(Versions.Test.core)
  testImplementation(Versions.Test.rules)
  testImplementation(Versions.Test.runner)
  androidTestImplementation(Versions.extJunit)
  androidTestImplementation(Versions.espresso)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xallow-jvm-ir-dependencies")
    // Enable experimental coroutines APIs, including Flow
    freeCompilerArgs = listOf("-Xopt-in=kotlin.Experimental")

    // Set JVM target to 1.8
    jvmTarget = "1.8"
  }
}
