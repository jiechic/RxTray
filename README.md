# RxService

[![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Jcenter](https://img.shields.io/badge/%20Jcenter%20-1.0.0-5bc0de.svg)](https://bintray.com/jiechic/android/rxtray/_latestVersion)

[^_^]:[![Methods](https://img.shields.io/badge/%20Methods%20%7C%20Size%20-%20239%20%7C%2040%20KB-d9534f.svg)](http://www.methodscount.com/?lib=com.jiechic.android.library%3Arxtray%3A1.0.0)
[^_^]:[![Maven](https://img.shields.io/badge/%20Maven%20-1.0.0-5bc0de.svg)](https://mvnrepository.com/artifact/com.akaita.java/rxtray/1.2.0)
[^_^]:[![Arsenal](https://img.shields.io/badge/%20Arsenal%20-%20RxService%20-4cae4c.svg?style=flat)](https://android-arsenal.com/details/1/6027)


A rxjava wapper for tary library

## Installation

Using *JCenter*:
```groovy
repositories {
    jcenter()
}

dependencies {
    compile 'com.jiechic.android.library:rxtray:1.0.0'
}
```

## Usage

Simple tutorial how to use Tray in your project instead of the SharedPreferences

the some as [tray](https://github.com/grandcentrix/tray) then replace AppPreferences with RxAppPreferences and replace TrayPreferences with RxTrayPreferences

for example:

```java
// create a preference accessor. This is for global app preferences.
final RxAppPreferences appPreferences = new RxAppPreferences(getContext()); // this Preference comes for free from the library
// save a key value pair
appPreferences.put("key", "lorem ipsum");
```


```java
// create a preference accessor for a module
public class MyModulePreference extends RxTrayPreferences {

    public static String KEY_IS_FIRST_LAUNCH = "first_launch";

    public MyModulePreference(final Context context) {
        super(context, "myModule", 1);
    }
}
```

now you can subscription your key of value
```java
   appPreferences.getXXXObservable(key).subscribe()
```

### Save and read preferences

Just enable RxTray as soon as possible. In Android, for example:

