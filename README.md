Rijksmuseum collection
========
The app is created to show Rijksmuseum's artworks list and show more details about the artworks when clicking on them. It uses the [RijksData API](https://data.rijksmuseum.nl/object-metadata/api/). 

https://github.com/araratthehero/rijksmuseum-collection/assets/8832594/6ee9ab14-c29b-40d5-b768-55ee54d96409

Toolkit
========
To build this app, I used:
* Macbook Pro (M1 Pro) | 32 GB
* Android Studio Electric Eel | 2022.1.1 Patch 1
* Google Pixel 7 Pro (API 33) and Emulator arm64-v8a (API 28) for testing

Architecture
===========
As Android apps grow in size, it's important to define an architecture that allows the app to scale, increases the app's robustness, and makes the app easier to test. For that purpose I used [Google's recommended app architecture](https://developer.android.com/topic/architecture#recommended-app-arch).

![image](https://github.com/araratthehero/rijksmuseum-collection/assets/8832594/c6b18a1f-e5ea-4914-81c6-2fe945c81c3a)

In Rijsmuseum collection app, I used all three layers mentioned above:  
* UI (:app) - Responsible to display application data on the screen  
* Domain (:domain) - Contains the business logic of the app  
* Data (:data) - Responsible for making changes to the data

In a small app like this, the Domain layer could easily be removed, but I have it implemented as part of this assignment to showcase that it can help us split responsibilities and scale the codebase.

UI
========
### The app supports Dark and Light modes

<center>
  <table>
    <tr>
      <td><img width="300" src="https://github.com/araratthehero/rijksmuseum-collection/assets/8832594/a2ba48ce-3cb3-4518-b924-658f461de5dd"></td>
      <td><img width="300" src="https://github.com/araratthehero/rijksmuseum-collection/assets/8832594/b86501e0-1537-4ece-a91e-1464bdc17d49"></td>
    </tr>
  </table>
</center>

### On supported devices it uses Material 3 dynamic colors

<center>
  <table>
    <tr>
      <td><img width="300" src="https://github.com/araratthehero/rijksmuseum-collection/assets/8832594/e9aab070-2ef3-4041-9041-5e3d34be47a0"></td>
      <td><img width="300" src="https://github.com/araratthehero/rijksmuseum-collection/assets/8832594/47c4ef8c-a90b-4a00-a5e9-421a6c2e6282"></td>
    </tr>
  </table>
</center>

### In case of errors, the app shows an error screen with an option to Retry loading

https://github.com/araratthehero/rijksmuseum-collection/assets/8832594/d5f447d0-d20f-4a4d-8f7e-4d2112cf92b7

Libraries
============
Here is the list of the libraries I used in this project:
* App
  * Hilt - Dependency injection
  * Coil - for loading images asynchronously
  * Paging
  * Navigation
  * Compose
* Network
  * Retrofit 2
  * Okhttp3
  * Gson
* Testing
  * Junit
