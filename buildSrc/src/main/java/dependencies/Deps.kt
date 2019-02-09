package dependencies

/**
 * Created by Sonphil on 08-02-19.
 */

object Deps {
    private object Versions {
        const val astl = "1.0.1"
        const val buglife = "1.4.0"
        const val chuck = "1.1.0"
        const val constraint_layout = "2.0.0-alpha3"
        const val dagger = "2.16"
        const val dimens = "1.4"
        const val espresso = "3.1.2-alpha01"
        const val firebase = "16.0.7"
        const val firebase_crashlytics = "2.9.8"
        const val flexbox = "1.0.0"
        const val glide = "4.8.0"
        const val gradle = "3.3.0"
        const val groupie = "2.1.0"
        const val junit = "4.12"
        const val kotlin = "1.3.21"
        const val leakcanary = "1.6.3"
        const val lifecycle = "2.0.0"
        const val materialprogressview = "1.0.6"
        const val mockito = "2.23.4"
        const val mockito_kotlin = "1.6.0"
        const val moshi = "1.8.0"
        const val navigation = "1.0.0-beta01"
        const val okhttp = "3.8.0"
        const val recyclerviewanimators = "2.3.0"
        const val retrofit = "2.5.0"
        const val room = "2.1.0-alpha04"
        const val test = "1.1.2-alpha01"
        const val support = "1.0.0"
    }

    object GradlePlugin {
        val android = "com.android.tools.build:gradle:${Versions.gradle}"
    }
}