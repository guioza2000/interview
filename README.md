# Coding Challenge Android

Hi,

Thank you for taking the time to review my app.

This app connects to the StackExchange API endpoints and retrieves the users for the website StackOverflow. 
It displays the user name, reputation, badges and Gravatar through a RecyclerView.
The Gravatars are loaded only once, asynchronously, and are cached in memory and on disk for further usage. A circular progress bar is displayed while they are loading.
The app is responsive. It works on both phones and tablets in portrait and landscape mode. 

To create this application I used several third-party libraries.

**Retrofit2, OkHttp and Gson:**
Retrofit is a type-safe REST client for Android developed by Square. The library provides a powerful framework for authenticating and interacting with APIs and sending network requests with OkHttp

I used it to perform the request to the StackExchange API endpoint.
I then used Gson for deserializing the response into java object.

[Retrofit2](https://square.github.io/retrofit/)

**Picasso:**
Picasso is a powerful image downloading and caching library for Android developed by Square.

I used it to handle the download and caching of the StackExchange gravatar's users image.

[Picasso](http://square.github.io/picasso/)

**Timber:**
Timber is a logger with a small, extensible API which provides utility on top of Android's normal Log class.

I used it instead of the default Android Log class.

[Timber](https://github.com/JakeWharton/timber)

## Getting Started

The app should run as is with Android Studio. It works for Android 5.1+


## Built With

* [Retrofit2](https://square.github.io/retrofit/)
* [Picasso](http://square.github.io/picasso/)
* [Timber](https://github.com/JakeWharton/timber)
