import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.serialization)
    alias(libs.plugins.sqldelight)
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = false
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.sqldelight.android.driver)
            implementation(libs.koin.android)
            implementation(libs.ktor.client.android)
            implementation(libs.ktor.client.okhttp)
        }

        commonMain.dependencies {
            //Sqldelight
            implementation(libs.sqldelight.coroutines.extensions)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            //Koin
            implementation(libs.koin.core)

            //Network
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kotlinx.serialization.core)

            //Datetime
            implementation(libs.datetime)

            //Moko
            //api(libs.moko.resources)
            api(libs.moko.resources.compose)

            //Coil
            implementation(libs.coil)
            implementation(libs.coil.network)

            //TouchLab
            implementation(libs.touchLab)

            //Navigation
            implementation(libs.navigation.compose)

            // Material 3
            implementation(compose.material3)

        }

        iosMain.dependencies {
            implementation(libs.sqldelight.native.driver)
            //Network
            implementation(libs.ktor.client.ios)
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.example.kursovikcmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.kursovikcmp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}


sqldelight {
    databases {
        create("Database") {
            packageName.set("com.example.kursovikcmp")
            generateAsync.set(true)
        }
    }
    linkSqlite = true
}

multiplatformResources {
    resourcesPackage.set("com.example.kursovikcmp")
}