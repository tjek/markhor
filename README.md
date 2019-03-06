# markhor

utilities for android projects.

## Dependency

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
        maven { url "https://jitpack.io" }
    }
}
```

Then, add the library to your module `build.gradle`
```gradle
dependencies {
    compile 'com.shopgun.android:utils:1.1.0' // 2.0.0 for AndroidX
}
```
