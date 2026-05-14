# Fit Tracker

Fit Tracker is an Android application designed to help users track and create outfits.

## Features

* Log and track outfits/clothes
* Store outfit data locally using Room Database
* Capture and attach photos using the device camera
* View outfit history
* Simple and user-friendly interface

## Tech Stack

* **Language:** Kotlin/Java
* **Framework:** Android SDK
* **Database:** Room (SQLite)
* **Build System:** Gradle (Kotlin DSL)
* **IDE:** Android Studio

## Getting Started

### Prerequisites

* Android Studio (latest version recommended)
* Android SDK installed
* A physical device or emulator

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/anthonyduenez/Fit-Tracker.git
   ```

2. Open the project in Android Studio

3. Sync Gradle and build the project

4. Run the app on an emulator or Android device

## Permissions

The app requires the following permissions:

* **Camera**

  * Used to capture photos for workout tracking

Make sure to allow permissions when prompted on your device.

## Project Structure

```text
Fit-Tracker/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/        # Kotlin source files
│   │   │   ├── res/         # UI layouts and resources
│   │   │   └── AndroidManifest.xml
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
```

## Notes

* This project uses local storage (Room), so no internet connection is required.
* Make sure your device/emulator has camera support for full functionality.

## Future Improvements

* User Sign up
* Improved UI/UX design

## Contributors

* Anthony Duenez Ramirez
* Tyler Fleming
* William Langley
* Vrunda Patel
