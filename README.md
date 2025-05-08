# Agregore Hyper Daemon - Android

Library for starting a [bare-kit](https://github.com/holepunchto/bare-kit) based daemon on Android.

## Using

Build the AAR and place it in your Android project in a new folder called `hyperdaemon`.

Add a `build.gradle` with the following content:

```groovy
configurations.maybeCreate("default")
artifacts.add("default", file('hyperdaemon.aar'))
```

In your `settings.gradle.kts` add this before `include(:app)`.

```kotlin
include(":hyperdaemon")
```

In your `app/build.gradle.kts` add the following line to include the library in the `dependencies` section:

```kotlin
dependencies {
    api(project(":hyperdaemon"))
}
```

Then in your `MainActivity` you can import the library and start the daemon. Make sure to pass in a proper folder for storage and pass in an InputStream for the `app.bundle` asset.

```kotlin
import moe.mauve.agregore.mobile.lite.HyperDaemon

class MainActivity : ComponentActivity() {
    private var daemon: HyperDaemon? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        daemon = HyperDaemon()
        daemon!!.start(
            applicationContext.filesDir.toString() + "/hyper",
            assets.open("app.bundle")
        )
    }
}
```


## Building

To keep the build process fast and efficient, the project relies on a Bare Kit prebuild being available in the [`app/libs/`](app/libs) directory. Prior to building the project, you must therefore either clone and compile Bare Kit from source, or download the latest prebuild from GitHub. The latter is easily accomplished using the [GitHub CLI](https://cli.github.com):

```console
gh release download --repo holepunchto/bare-kit <version>
```

Unpack the resulting `prebuilds.zip` archive and move `android/bare-kit` into [`app/libs/`](app/libs). When finished, either open the project in Android Studio or build it from the commandline:

```console
gradle build
```

### Addons

Native addons will be linked into [`app/src/main/addons/`](app/src/main/addons) as part of the build process and will be automatically included in the final APK bundle by Gradle.

## Running

> [!IMPORTANT]
> You may experience problems running the app on an emulated Android device due to https://github.com/holepunchto/libjs/issues/4. If you encounter crashes, try running the app on a real Android device instead.

## License

Apache-2.0
