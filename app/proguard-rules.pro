# Keep model classes (Gson serialization)
-keep class com.nammakathe.core.domain.model.** { *; }
-keep class com.nammakathe.core.data.repository.**Dto { *; }
-keep class com.nammakathe.core.data.repository.HeroDataRoot { *; }

# Keep Hilt generated classes
-keep class dagger.hilt.** { *; }
-keepclassmembers class * {
    @dagger.hilt.android.lifecycle.HiltViewModel <init>(...);
}

# Keep Gson
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.**
-keep class com.google.gson.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Lottie
-dontwarn com.airbnb.lottie.**
-keep class com.airbnb.lottie.** { *; }

# Room
-keep class * extends androidx.room.RoomDatabase
-dontwarn androidx.room.**

# Navigation
-keep class androidx.navigation.** { *; }

# Coroutines
-keepclassmembernames class kotlinx.** { volatile <fields>; }
