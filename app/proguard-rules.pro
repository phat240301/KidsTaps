# ProGuard configuration for KidsTap
# Keep all Kotlin/Java classes used by the app

# Keep all application classes
-keep class com.kidstap.app.** { *; }

# Keep Hilt-generated classes
-keep class dagger.** { *; }
-keep class hilt_aggregated_deps.** { *; }
-keep interface dagger.** { *; }

# Keep model classes
-keep class com.kidstap.app.domain.model.** { *; }

# Keep annotations
-keep interface com.google.android.material.** { *; }
-keep class androidx.** { *; }

# Keep native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Remove logging in release
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}

# Optimize aggressively
-optimizationpasses 5
-dontusemixedcaseclassnames
-verbose

# Preserve line numbers for crash reporting
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
