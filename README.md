<<<<<<< HEAD
# NewsApp

This is a comprehensive news application developed using **Kotlin** and **Android Studio**. The app allows users to view news headlines, search for articles, and manage their favorite articles. It utilizes modern best practices in Android development, including the **MVVM architecture**, **Room Database** for local data storage, **Retrofit** for API interactions, and **Dagger Hilt** for dependency injection.

## Table of Contents

- [Features](#features)
- [Technical Stack](#technical-stack)
- [Architecture](#architecture)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)

## Features

- **View Headlines**: Users can fetch and display news headlines categorized by country and topic (e.g., business, entertainment, sports).
- **Search Functionality**: Allows users to search for news articles by entering keywords.
- **Favorites Management**: Users can save articles to a favorites list for later viewing.
- **Offline Storage**: Articles marked as favorites are cached locally using Room Database, ensuring access even without an internet connection.
- **Error Handling**: The app gracefully handles errors and provides feedback to users.

## Technical Stack

- **Kotlin**: The primary programming language for Android development.
- **Android Studio**: The IDE used for building and maintaining the application.
- **XML Layouts**: Traditional XML layouts are used to create the user interface components.
- **Retrofit**: A type-safe HTTP client for Android to handle network requests to the news API.
- **Room**: A persistence library that simplifies database management with an SQLite backend.
- **Dagger Hilt**: A dependency injection framework that helps in managing dependencies efficiently.
- **RecyclerView**: For displaying tasks.
- **LiveData**: For observing and updating the UI.
- **Coroutines**: For asynchronous operations.

## Architecture

The application follows the **MVVM (Model-View-ViewModel)** architecture, which promotes separation of concerns, making the codebase more manageable and testable.

### Components

- **Model**: Represents the data layer of the application, including network requests and database operations.
- **View**: Composed of XML layouts that render the user interface.
- **ViewModel**: Acts as a bridge between the View and the Model. It holds UI-related data and handles user interactions.

### Steps to Run
1. Clone this repository:
   ```bash
   git clone https://github.com/muameh/NewsApp.git
   
======
![screenShots1](images/Screenshot_1729492624.png)
![screenShots2](images/Screenshot_1729492658.png)
![screenShots3](images/Screenshot_1729492716.png)
![screenShots4](images/Screenshot_1729492651.png)
![screenShots5](images/Screenshot_1729492627.png)
![screenShots6](images/Screenshot_1729492707.png)
![screenShots7](images/Screenshot_1729492750.png)
![screenShots8](images/Screenshot_1729493509.png)




