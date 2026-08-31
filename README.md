# Y-Care: Medical Gate Pass & Appointment System

A specialized Android application designed for tablets to streamline the medical token raising and gate pass process. The app is specifically optimized for 8-inch tablet screens in portrait orientation, providing a high-visibility, easy-to-use interface for medical staff and employees.

## 🚀 Key Features

*   **Employee ID Entry**: High-visibility text input for entering employee ID card numbers.
*   **Priority Selection (Urgency Type)**: Integrated dropdown to set the urgency of the request.
    *   **High**: Priority level 1 (Default)
    *   **Medium**: Priority level 2
    *   **Low**: Priority level 3
*   **Grid Selection System**: Optimized 5-item-per-row grid for selecting Sections and Lines.
*   **Tablet Optimization**: 
    *   Fixed **Portrait** orientation for layout stability.
    *   Large touch targets (64dp height for selection buttons).
    *   Responsive heights that fill the screen effectively.
*   **Real-time API Integration**: Fetches section and line data dynamically.
*   **Today's Requests View**: Dedicated screen to monitor all raised medical tokens for the current day.
*   **Visual Feedback**:
    *   Loading indicators on the SUBMIT button during network calls.
    *   Snackbar notifications for success and error states.
    *   Automatic form reset upon successful submission.

## 🛠 Tech Stack

*   **Language**: [Kotlin](https://kotlinlang.org/)
*   **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material 3)
*   **Networking**: [Retrofit 2](https://square.github.io/retrofit/) & [OkHttp 3](https://square.github.io/okhttp/)
*   **Navigation**: [Jetpack Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
*   **Architecture**: Repository Pattern
*   **JSON Parsing**: [GSON](https://github.com/google/gson)

## 📁 Project Structure

```text
app/src/main/java/com/example/medical_tab/
├── api/                # Retrofit interface and API client
├── model/              # Data classes for API requests/responses
├── repository/         # Data handling logic and network result management
├── ui/                 # Main screens (Home, Request List, Navigation)
│   ├── theme/          # Color, Type, and Theme definitions
├── SectionSelectionComp.kt  # Custom grid component for section selection
├── LineSelectionComp.kt     # Custom grid component for line selection
└── MainActivity.kt          # Entry point and shared UI components
```

## ⚙️ Setup & Installation

1.  **Clone the Repository**:
    ```bash
    git clone https://github.com/your-repo/Medical_Tab.git
    ```
2.  **Open in Android Studio**:
    *   Recommended version: Ladybug (2024.2.1) or newer.
3.  **Sync Gradle**:
    *   Wait for the project to download all dependencies.
4.  **Configure API**:
    *   Base URL is configured in `api/RetrofitClient.kt`. Ensure the tablet has network access to the server.
5.  **Run**:
    *   Connect your 8-inch tablet and click **Run**.
    *   Note: The app is locked to portrait for the best user experience.

## 📝 API Endpoints used:

*   `GET api/Support/GetLines`: Fetches all available Section and Line mapping.
*   `POST api/Support/TokenRaise`: Submits the ID Card, Line ID, and Urgency Type.
*   `GET api/Support/GetDailyMedicalRequests`: Fetches the history of today's requests.
