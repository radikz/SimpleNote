# SimpleNote
Simple Note using Kotlin with sqlite and jetpack-navigation
<br>If you like this Repo, Please click the :star:

## Feature
* SQLite
  * :heavy_check_mark: Create notes
  * :heavy_check_mark: List all notes
  * :heavy_check_mark: Delete notes
  * :heavy_check_mark: Update notes
* Android Jetpack Navigation
* Constraints Layout
* Recycler View

## Usage
How to implement android jetpack navigation 
Add dependencies in the **module build.gradle**
```
implementation 'androidx.navigation:navigation-fragment-ktx:2.3.1'
implementation 'androidx.navigation:navigation-ui-ktx:2.3.1'
implementation "androidx.navigation:navigation-dynamic-features-fragment:2.3.1"
```
In the **module build.gradle** replace with this. I am using gradle 4.1.0 for my project and android studio 4.1. A bit different but okay.
```build.gradle
plugins {
    id 'com.android.application'
    id 'kotlin-android'
    id 'kotlin-android-extensions'
    id 'androidx.navigation.safeargs'
}
```
Previous version usually like this:
```build.gradle
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'
apply plugin: "androidx.navigation.safeargs"
```
Then add dependencies in your **project build.gradle**
```
classpath "androidx.navigation:navigation-safe-args-gradle-plugin:2.3.1"
```
Add this in your **module build.gradle** inside your android
```
android{
/*******************************************************/
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
}
```
You can check the connection between layout in the **res/navigation/nav_graph.xml**
<img src="/Screenshots/navigation.png">

## Screenshots
<img src="/Screenshots/note1.png" width="30%">    <img src="/Screenshots/note2.png" width="30%">   <img src="/Screenshots/note3.png" width="30%"> <img src="/Screenshots/note4.png" width="30%">

## License
Distributed under the [MIT](LICENSE) License.

