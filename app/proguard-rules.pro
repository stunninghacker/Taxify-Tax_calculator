# Firebase Realtime Database
-keep class com.google.firebase.** { *; }
-dontwarn com.google.firebase.**

# Keep all classes with @Keep annotation
-keep @interface com.google.firebase.database.IgnoreExtraProperties

# Keep Firebase internal classes
-keep class com.google.firebase.database.** { *; }

# AndroidX Lifecycle
-keep class androidx.lifecycle.** { *; }
-dontwarn androidx.lifecycle.**

# Google Material Design Components
-keep class com.google.android.material.** { *; }
-dontwarn com.google.android.material.**

# ConstraintLayout
-keep class androidx.constraintlayout.** { *; }
-dontwarn androidx.constraintlayout.**

# Google Play Services
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

# Ensure classes with @Keep annotation are retained
-keepclassmembers class * {
    @com.google.firebase.database.IgnoreExtraProperties <methods>;
}

# Ensure Firebase SDK can serialize/deserialize objects correctly
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException;
    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException;
    private java.lang.Object readResolve() throws java.io.ObjectStreamException;
    private java.lang.Object writeReplace() throws java.io.ObjectStreamException;
}
