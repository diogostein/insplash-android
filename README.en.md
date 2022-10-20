*Languages: [PT-BR](README.md)*

# Insplash

<div align="center">
  <img width="25%" src="https://diogostein.dev/assets/codelabs/insplash_1-49753a2ce55aaaf7be040f15de4da966fe978cbc0fa4607df6135ecd7e26e26f.png" />
  <img width="25%" src="https://diogostein.dev/assets/codelabs/insplash_3-0d25df65fdd96b9149b79ede64401fa6481f433381a678a3b930fe34c20ac1d4.png" />
  <img width="25%" src="https://diogostein.dev/assets/codelabs/insplash_2-4def3745102c1970f5389b93b2543bd04044b4a4885d53b055b9a7a6571257ce.png" />
</div>

<hr/>

Insplash is an application developed in Kotlin language for Android using the Jetpack Compose UI Toolkit to build the interface and consumes the API of [Unsplash](https://unsplash.com/) which is a repository of free images.

The app has a photo listing screen with search option, a screen to display the photo + info, and a screen to list favorite photos. It has a feature to bookmark photos that will only be saved in the local database for simplicity.

The defined architecture follows the recommendations and principles of the official Android documentation and is based on the structure as shown in the image below.

![image](https://user-images.githubusercontent.com/2924219/132413978-d6026326-ed73-4956-9e47-0515938a8f96.png)

## Code features and libraries used

* UI building using Jetpack Compose.
* MVVM architecture with ViewModel for repository and UI interaction
* Data layer using repository + data sources
* Coroutines for asynchronous programming and long task management
* HTTP client and API connection setup with Retrofit
* Dependency Injection with Dagger Hilt
* Image loading and caching with Glide
* Storage of API results in local database with Room
* Manually implemented results paging engine
* Creation of auxiliary generic classes in order to avoid code repetitions (DRY code principles)

## How to configure

To run the project in Android Studio, you first need to create an API access key via the [Unsplash](https://unsplash.com/developers) developer portal. After registration, an access key and a secret key will be generated, which should be added to the `local.properties` file, as in the example:

`unsplashApi.accessKey="your_access_key"`<br/>
`unsplashApi.secretKey="your_secret_key"`

**The project is ready to run! :)**
