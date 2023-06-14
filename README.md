# CryptoWithCompose Android App  entirely with Kotlin and Jetpack Compose.

NOTE: The app is currently in development.

# Build Tools

| Tools                     |
| --------------------------|
| Kotlin                    | 
| Gradle                    | 
| Android Plugin for Gradle |


Built on Android Studio Electric eel

Run configuraiton to launch : 'app'

# Application Architecture


The application is built using the Clean Architecture approach, a software design pattern that emphasizes separation of concerns and maintainability. 
It aligns with Google's recommended architecture and adheres to their best practices for Android development.

# Lib Depdencies

The application utilizes the latest Kotlin DSL for Gradle files, offering improved readability and maintainability.
It also employs Version Catalog, using [TOML](https://developer.android.com/build/migrate-to-catalogs) for centralized dependency management, ensuring consistent and synchronized dependency versions.

# Depdendency Injection 

DI is done using Andorid's [Hilt] Lib and each module has its own `di` package for providing the depdencies.

# Modularization

It uses App Modularization strategies and following are some modules of the app.

`app` - Currently it holds the UI components but plan is to move the Features to a separate Feature module

`data-local` - Handles the Local Data using Android DataStore.

`domain` - Holds the Domain Models 

`data-network` - Holds the Network Responses DTO, Networking Service (i.e Retrofit), various respositories used in the app,
                 Mappers for converting network responses to the domain specific one

`common` - Some Common Utility functions and Constants

# UI

The application adheres to a Single Activity architectural pattern and is exclusively developed utilizing Jetpack Compose.
A notable aspect of the application is its adaptive screen behavior, wherein it seamlessly adjusts to both landscape and portrait orientations, 
ensuring a consistent user experience without any loss of data. 

# Navigation

The application implements a Single Nav Host that serves as the host for all other screens. Each screen is encapsulated within its own separate graph.
Presently, each tab is implemented as an independent graph, allowing for modularization and isolation of navigation logic. 

# Testing

Unit testing is conducted utilizing the JUnit framework in conjunction with two essential tools:
[Mockito] (https://site.mockito.org/) for creating mock objects and 
[Turbine],(https://github.com/cashapp/turbine) an open-source library , specifically designed for testing complex flows.

The focus of my testing efforts primarily centers around ViewModels, Mappers, Repositories, and DataStores. 
However, it is important to note that currently, my comprehensive unit testing is exclusively concentrated on ViewModels.

To ensure accurate and reliable test results, the ViewModel test cases incorporate internal TestRepo dependencies. 
These dependencies play a vital role in initiating a flow, facilitating the emission of specific data to the ViewModel for evaluation.

# TODO
- `Dynamic Theming` which could be toggled from the app settings
- `Ui Testing` UI Testing the compose screens using espresso
- `Favourites` Favourites Module to save any Coin to favourites
- `SavedStateHandle` Storing Viewmodel data to maintain data during Process Death 
- `Auth Feature` Login Feature is currently a fake login and will be udpated with the Firebase Auth
- `Material 3`
