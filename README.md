# WeatherForecast_App 🌤️

Overview
--------
WeatherForecast_App is a modern Android application that provides users with real-time weather information and a 7-day forecast for any city. Built with Kotlin, the app follows best practices in software architecture, using MVI (Model-View-Intent) for user input management and MVVM (Model-View-ViewModel) for handling weather data retrieval and display. The app is designed with scalability, maintainability, and ease of testing in mind.

Additionally, WeatherApp includes a custom library, Weather-Util, which formats various weather-related factors such as temperature, humidity, wind speed, and more. This library helps ensure consistency and reusability across the app by providing prebuilt utility methods for formatting weather data.

Features:
---------
🌍 Input City (MVI Pattern)

The input section where users can search for a city's weather is implemented using the MVI (Model-View-Intent) design pattern. This approach enables a reactive flow between user interactions and UI state, ensuring predictability and efficient state management.

Key Highlights:

City Search Management: Users can search for any city, triggering intents that interact with the ViewModel to fetch weather data. The integration of an autocomplete search API enhances this feature, providing users with a streamlined experience by suggesting locations as they type. This ensures that users select the correct city, minimizing errors and improving overall satisfaction.

Reactive UI Updates: Intent-driven UI updates ensure that the app remains in sync with user actions. As users enter their desired location, the app dynamically updates the suggestions based on the API responses, allowing for quick and efficient location selection.

☀️ Current Weather (MVVM Pattern)

The current weather display utilizes the MVVM (Model-View-ViewModel) architecture, which ensures that the business logic is cleanly separated from the UI. This allows for better testability and easier maintenance.

Key highlights:

Real-time weather information: Displays the current weather for the selected city.
ViewModel-based UI updates: The ViewModel holds the state of the current weather and provides reactive updates to the UI.

📅 7-Day Weather Forecast (MVVM Pattern)

The 7-day forecast also follows the MVVM design pattern, providing detailed weather information for each day in a modular and easily testable format.

Key highlights:

Daily forecasts: Includes detailed weather data such as temperature, weather conditions, and icons for a 7-day period.
Efficient data management: The ViewModel handles all API interactions to fetch forecast data, ensuring smooth performance.

📚 Custom Library: Weather-Util

WeatherApp includes a custom-built library, Weather-Util, which is designed to format various weather factors such as temperature, humidity, wind speed, and more. This library standardizes the formatting of weather-related information, ensuring that all weather data is consistently presented across the app.

Key features of Weather-Util:

Temperature formatting: Converts raw temperature data into user-friendly formats.
Wind speed and humidity formatting: Ensures that all weather factors are correctly displayed according to locale and preferences.
Reusable methods: Simplifies code and ensures consistency throughout the app.

Dark Theme Support: 

The app fully supports a dark theme, providing users with a visually appealing experience that reduces eye strain in low-light conditions. This feature not only enhances usability but also aligns with modern design trends, allowing users to choose their preferred interface style.

Unit Testing:

The app is designed with testability in mind, incorporating unit tests to ensure the reliability of key components. By utilizing testing frameworks, we validate the functionality of the MVI pattern, ViewModel interactions, and other critical features. This rigorous testing approach guarantees that the app performs as expected, providing users with a robust and seamless experience.


