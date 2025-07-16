# Add project-specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\sdk\tools\proguard\proguard-android-optimize.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.

# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Keep classes and members that are used via reflection (Room, etc.)
-keep class com.example.patiobalmax.database.** { *; }

# Room generated code
-keep class **$$RoomGenerated** { *; }
