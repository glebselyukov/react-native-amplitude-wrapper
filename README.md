# react-native-amplitude-wrapper

React Native wrapper for [Amplitude](https://amplitude.com).

Thanks:

[react-native-amplitude-android](https://github.com/Around25/react-native-amplitude-android) ([biancaFarcas](https://github.com/biancaFarcas)),
 
[react-native-amplitude-sdk](https://github.com/negativetwelve/react-native-amplitude-sdk) ([Mark Miyashita](https://github.com/negativetwelve)),
  
[react-native-amplitude](https://github.com/euwyn/react-native-amplitude) ([Euwyn Poon](https://github.com/euwyn)),

[medium blog](https://medium.com/@eileenzhong/adding-amplitude-analytics-to-your-react-native-app-5e0715d258f8) ([Eileen Zhong](https://medium.com/@eileenzhong))

## Setup

```bash
# Yarn
yarn add react-native-amplitude-wrapper

# NPM
npm install --save react-native-amplitude-wrapper
```

### iOS with Cocoapods

Add the following to your Podfile:

```ruby
pod "react-native-amplitude-wrapper", path: "../node_modules/react-native-amplitude-wrapper"
```

Then run:

```bash
cd ios && pod install
```

You're done! :tada:

**NOTE** Your relative path to `node_modules` may differ.

### Android

```bash
# react-native link
react-native link react-native-amplitude-wrapper

or

# rnpm link (better!)
rnpm link react-native-amplitude-wrapper
```

**On newer versions of React Native register module (MainApplication.java):**

```java
import com.around25.RNAmplitudeAndroid.*;;  // <--- import

public class MainApplication extends Application implements ReactApplication {
  ......

  /**
   * A list of packages used by the app. If the app uses additional views
   * or modules besides the default ones, add more packages here.
   */
    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
        new RNAmplitudeAndroidPackage(), // <------ add here
        new MainReactPackage());
    }
}
```

**On older versions of React Native register module (in MainActivity.java):**

```java
import com.around25.RNAmplitudeAndroid.*;;  // <--- import

public class MainActivity extends ReactActivity {
  ......

  /**
   * A list of packages used by the app. If the app uses additional views
   * or modules besides the default ones, add more packages here.
   */
    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
        new RNAmplitudeAndroidPackage(), // <------ add here
        new MainReactPackage());
    }
}
```
