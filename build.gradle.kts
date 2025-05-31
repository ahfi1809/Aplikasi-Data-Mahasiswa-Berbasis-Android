buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.9.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
        classpath ("com.google.gms:google-services:4.4.2")
    }
}
plugins{
    id("com.google.gms.google-services") version "4.3.15" apply false

}


