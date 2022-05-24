# Flicker Task
An Android Application that fetches the image from flicker and shows them with endless scrolling 
It also provides suggestions based on the user's previous search history.
Smooth error handling while scrolling if any error occurs user can retry from that point it will fetch the next available images and load previously unloaded image

###Use : Get your own Flicker api key and put it on  build.gradle to get the result. if current key is not working

## App Features 
* __Smooth Endless Scrolling__

https://user-images.githubusercontent.com/7686968/170009449-323f999f-938c-4925-b045-eafbc46625d8.mp4

* __Refresh Image While Error Occur__

https://user-images.githubusercontent.com/7686968/170008663-a8a6aa24-1f7a-4f70-b598-d93d052beb14.mp4

* __Show Suggestions__
* __Pull To Refresh__
* __Show Errors__

![image](https://user-images.githubusercontent.com/7686968/170082489-20ea3192-2983-4848-a99d-36b22485cb78.png)


## Flickr API:
* __[flickr.photos.search](https://www.flickr.com/services/api/flickr.photos.search.html)__

## Prerequisites
* __Flicker API Key__
* __Android Studio__
* __Gradle__
* __Kotlin version__
* __Android Device or Emulator__


## Languages, libraries and tools used
* __[Pagination](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)__
* __[Retrofit](https://github.com/square/retrofit)__
* __[OkHttp](https://square.github.io/okhttp/)__
* __[fresco](https://github.com/facebook/fresco)__
* __[Kotlin](https://developer.android.com/kotlin)__
* __[Koin](https://github.com/InsertKoinIO/koin)__
* __[Android Material Design](https://material.io/components/)__
* __[Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)__
* __[Unit Testing](https://developer.android.com/training/testing/local-tests)__
* __[Espresso Testing](http://developer.android.com/training/testing/espresso)__

## Project architecture
MVVM with Clean Architecture

## Automated tests
To run the test you must connect to real device or an emulator then you can choose in one of the following ways:

1. To run a single test, open the Project window, and then right-click a test and click Run.

2. To run all tests in a directory, right-click on the directory and select Run tests








