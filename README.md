Rijksmuseum collection
========
The app is created to show Rijksmuseum's artworks list and show more details about the artworks when clicking on them. It uses the [RijksData API](https://data.rijksmuseum.nl/object-metadata/api/). 

https://github.com/araratthehero/rijksmuseum-collection/assets/8832594/f371eb8d-5705-4661-9f37-7ebb689fde2a

Toolkit
========
To build this app, I used:
* Macbook Pro (M1 Pro) | 32 GB
* Android Studio Electric Eel | 2022.1.1 Patch 1
* Google Pixel 7 Pro (API 33) and Emulator arm64-v8a (API 28) for testing

Architecture
===========
As Android apps grow in size, it's important to define an architecture that allows the app to scale, increases the app's robustness, and makes the app easier to test. For that purpose I used [Google's recommended app architecture](https://developer.android.com/topic/architecture#recommended-app-arch).

![image](https://github.com/araratthehero/rijksmuseum-collection/assets/8832594/8a1dfe0f-f920-42c7-bb7f-9601e49e2f3c)

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
      <td><img width="300" src="https://github.com/araratthehero/rijksmuseum-collection/assets/8832594/a208f910-df2d-413d-9c9d-9f56b28bbef2"></td>
      <td><img width="300" src="https://github.com/araratthehero/rijksmuseum-collection/assets/8832594/8b0f1992-72f4-47dd-8583-f2018011b366"></td>
    </tr>
  </table>
</center>

### On supported devices it uses Material 3 dynamic colors

<center>
  <table>
    <tr>
      <td><img width="300" src="https://github.com/araratthehero/rijksmuseum-collection/assets/8832594/fdc6b10d-888b-4456-84d4-2730f70be715"></td>
      <td><img width="300" src="https://github.com/araratthehero/rijksmuseum-collection/assets/8832594/86bf7e4b-e17e-45e5-b730-e79b92ed2381"></td>
    </tr>
  </table>
</center>

### In case of errors, the app shows an error screen with an option to Retry loading

https://github.com/araratthehero/rijksmuseum-collection/assets/8832594/1bd825c7-a837-4289-8652-e13c8624e46e

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
