# 🎓 Student Marks Predictor

> An end-to-end machine learning application that predicts a student's final exam marks from academic, study, lifestyle, and other student information.

[![Python](https://img.shields.io/badge/Python-3.x-blue?logo=python&logoColor=white)](https://www.python.org/)
[![FastAPI](https://img.shields.io/badge/FastAPI-REST%20API-009688?logo=fastapi&logoColor=white)](https://fastapi.tiangolo.com/)
[![Streamlit](https://img.shields.io/badge/Streamlit-Web%20App-FF4B4B?logo=streamlit&logoColor=white)](https://streamlit.io/)
[![Kotlin](https://img.shields.io/badge/Kotlin-Android-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-UI-4285F4?logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)

---

## 📖 Table of Contents

- [🎓 Student Marks Predictor](#-student-marks-predictor)
  - [📖 Table of Contents](#-table-of-contents)
  - [📌 Project Overview](#-project-overview)
  - [🔗 Live Applications \& Resources](#-live-applications--resources)
  - [✨ Features](#-features)
    - [🤖 Machine Learning Prediction](#-machine-learning-prediction)
    - [⚡ FastAPI REST API](#-fastapi-rest-api)
    - [🌐 Streamlit Web Application](#-streamlit-web-application)
    - [📱 Android Application](#-android-application)
  - [🧾 Input Features](#-input-features)
    - [Student Information](#student-information)
    - [Academic Information](#academic-information)
    - [Lifestyle Information](#lifestyle-information)
    - [Other Information](#other-information)
  - [🛠️ Technology Stack](#️-technology-stack)
  - [🏗️ System Architecture](#️-system-architecture)
    - [Streamlit → FastAPI](#streamlit--fastapi)
    - [Android → FastAPI](#android--fastapi)
  - [⚙️ API Reference](#️-api-reference)
    - [`GET /`](#get-)
    - [`POST /predict`](#post-predict)
  - [📂 Repository Structure](#-repository-structure)
  - [📱 Android App Details](#-android-app-details)
    - [Architecture](#architecture)
    - [UI (Jetpack Compose)](#ui-jetpack-compose)
    - [Dark Mode \& Settings](#dark-mode--settings)
    - [Configuration](#configuration)
  - [🚀 Getting Started](#-getting-started)
    - [1. Clone the Repository](#1-clone-the-repository)
    - [2. Create a Virtual Environment](#2-create-a-virtual-environment)
    - [3. Install Backend Dependencies](#3-install-backend-dependencies)
    - [4. Start FastAPI](#4-start-fastapi)
    - [5. Run the Streamlit Application](#5-run-the-streamlit-application)
    - [6. Android Application Setup](#6-android-application-setup)
  - [☁️ Deployment](#️-deployment)
    - [Backend Service](#backend-service)
    - [API Testing](#api-testing)
  - [🔒 Current Deployment Notes](#-current-deployment-notes)
  - [📊 Project Status](#-project-status)
  - [🎯 Project Objective](#-project-objective)
  - [🔮 Future Improvements](#-future-improvements)
  - [⚠️ Disclaimer](#️-disclaimer)
  - [⭐ Conclusion](#-conclusion)
  - [👨‍💻 Developer](#-developer)

---

## 📌 Project Overview

**Student Marks Predictor** is an end-to-end machine learning deployment project built to demonstrate how a trained machine learning model can be converted into usable applications across multiple platforms.

The project consists of three major layers:

1. **Machine Learning Model** — A trained prediction pipeline saved as a `.joblib` file.
2. **FastAPI Backend** — Exposes the trained machine learning model through a REST API, providing a `/predict` endpoint for generating predictions.
3. **Client Applications** — A Streamlit web application and a native Android application built using Kotlin and Jetpack Compose.

Both client applications communicate with the same FastAPI prediction endpoint.

**High-Level Flow**

```text
User
 │
 ├───────────────┐
 │               │
 ▼               ▼
Streamlit      Android App
Web App        Kotlin + Compose
 │               │
 │               │ Retrofit / HTTP
 └───────┬───────┘
         │
         ▼
   FastAPI Backend
      /predict
         │
         ▼
   ML Model Pipeline
      .joblib
         │
         ▼
    Prediction
         │
         ▼
 Streamlit / Android
```

---

## 🔗 Live Applications & Resources

| Resource | Link |
|---|---|
| 🌐 Streamlit Web App | https://thesangamx-student-marks-predictor.streamlit.app/ |
| 📱 Google Play | https://play.google.com/store/apps/details?id=com.sangamgupta.studentmarkspredictor |
| 🧪 Google Play Testing | https://play.google.com/apps/testing/com.sangamgupta.studentmarkspredictor |
| 💻 Source Code | https://github.com/TheSangamX/student-marks-predictor |

> **Note:** The Android application is distributed through Google Play testing/release channels. Availability of the public Play listing depends on the current Google Play rollout/review status.

---

## ✨ Features

### 🤖 Machine Learning Prediction

The application uses a trained machine learning prediction pipeline to estimate final examination marks. The trained model is stored at:

```text
backend/
└── model/
    └── final_exam_predictor_model.joblib
```

The model is loaded by the FastAPI backend and used to generate predictions from the submitted student information.

### ⚡ FastAPI REST API

The machine learning model is exposed through a FastAPI backend (`backend/main.py`), which provides:

- `GET /` endpoint
- `POST /predict` endpoint
- Request validation using Pydantic
- JSON request/response handling
- Pandas DataFrame conversion before model prediction
- Serialized ML model loading using Joblib
- Uvicorn application server
- Swagger/OpenAPI documentation

### 🌐 Streamlit Web Application

A Streamlit-based web interface lets users enter:

- Student information
- Academic information
- Lifestyle information
- Other relevant student information

The entered information is sent to the FastAPI backend, which performs the prediction. Live at: https://thesangamx-student-marks-predictor.streamlit.app/

### 📱 Android Application

A native Android application built using Kotlin, Jetpack Compose, Material 3, Retrofit, Gson, and Kotlin Coroutines. The Android app communicates with the FastAPI backend rather than running the ML model locally.

**Android Features:**

- Student information form
- Academic information form
- Lifestyle information form
- Other information form
- Dropdown selection fields
- Numeric input fields
- Prediction button
- Loading indicator
- Prediction result display
- API error handling
- Settings screen
- Dark Mode toggle
- About section
- Developer information
- Contact email
- Website information
- App version information

---

## 🧾 Input Features

The application accepts **18 input features** across four categories.

### Student Information

| Field | Type | Description |
|---|---|---|
| `age` | Integer | Student age |
| `gender` | String | Student gender |
| `course` | String | Student course |
| `year` | String | Academic year |

### Academic Information

| Field | Type | Description |
|---|---|---|
| `study_hours_per_day` | Float | Study hours per day |
| `attendance_percent` | Float | Attendance percentage |
| `previous_semester_marks` | Float | Previous semester marks |
| `assignment_score` | Float | Assignment score |
| `internal_marks` | Float | Internal marks |

### Lifestyle Information

| Field | Type | Description |
|---|---|---|
| `sleep_hours_per_day` | Float | Sleep hours per day |
| `screen_time_hours_per_day` | Float | Screen time hours per day |
| `social_media_hours_per_day` | Float | Social media usage per day |
| `practice_tests_completed` | Integer | Number of completed practice tests |

### Other Information

| Field | Type | Description |
|---|---|---|
| `extracurricular_level` | String | Extracurricular activity level |
| `internet_access` | String | Internet access level |
| `parent_education` | String | Parent education level |
| `scholarship` | String | Scholarship status |
| `part_time_job` | String | Part-time job status |

> **Note on the ML layer:** The trained pipeline (`backend/model/final_exam_predictor_model.joblib`) receives this structured student information and returns an estimated final marks prediction. This repository does **not** claim a specific machine learning algorithm, accuracy, R² score, MAE, dataset size, or other training metric unless that information is actually available in the project source — the goal is to document the implemented project accurately rather than invent model-training results.

---

## 🛠️ Technology Stack

| Layer | Technology |
|---|---|
| Programming Language | Python |
| Machine Learning | Scikit-learn |
| Data Processing | Pandas |
| Numerical Computing | NumPy |
| Model Serialization | Joblib |
| Backend API | FastAPI |
| API Server | Uvicorn |
| API Validation | Pydantic |
| Web UI | Streamlit |
| Android Language | Kotlin |
| Android UI | Jetpack Compose |
| UI Components | Material 3 |
| Android Networking | Retrofit |
| JSON Conversion | Gson |
| Asynchronous Operations | Kotlin Coroutines |
| Cloud Hosting | AWS EC2 |
| Operating System | Ubuntu |
| Version Control | Git |
| Repository Hosting | GitHub |
| Mobile Distribution | Google Play |

---

## 🏗️ System Architecture

One of the main objectives of this project was to learn how a machine learning model can be deployed once and then consumed by different applications.

### Streamlit → FastAPI

```text
User
 ↓
Streamlit Web UI
 ↓
HTTP POST Request
 ↓
FastAPI /predict
 ↓
ML Model
 ↓
Prediction
 ↓
FastAPI Response
 ↓
Streamlit
```

### Android → FastAPI

```text
User
 ↓
Android Application
 ↓
StudentData
 ↓
Retrofit
 ↓
FastAPI /predict
 ↓
ML Model
 ↓
PredictionResponse
 ↓
Android UI
```

The same backend prediction service is therefore used by both applications.

---

## ⚙️ API Reference

### `GET /`

Verifies that the prediction API is running.

**Example response:**

```json
{
  "message": "Student Marks Prediction API is running"
}
```

### `POST /predict`

Accepts student information and returns predicted final marks.

**Example request:**

```json
{
  "age": 22,
  "gender": "Male",
  "course": "Data Science",
  "year": "4th",
  "study_hours_per_day": 4,
  "attendance_percent": 90,
  "previous_semester_marks": 70,
  "assignment_score": 30,
  "internal_marks": 90,
  "sleep_hours_per_day": 5,
  "screen_time_hours_per_day": 1,
  "social_media_hours_per_day": 1,
  "practice_tests_completed": 1,
  "extracurricular_level": "High",
  "internet_access": "Good",
  "parent_education": "Graduate",
  "scholarship": "Yes",
  "part_time_job": "Yes"
}
```

**Example response:**

```json
{
  "predicted_final_marks": 77.78861626341829
}
```

> The exact prediction depends on the input values submitted to the model.

Swagger/OpenAPI documentation is available at `/docs` once the backend is running.

---

## 📂 Repository Structure

```text
student-marks-predictor/
│
├── android-app/
│   ├── app/
│   │   ├── src/
│   │   │   └── main/
│   │   │       ├── AndroidManifest.xml
│   │   │       └── java/
│   │   │           └── com/
│   │   │               └── sangamgupta/
│   │   │                   └── studentmarkspredictor/
│   │   │                       ├── ApiClient.kt
│   │   │                       ├── ApiService.kt
│   │   │                       ├── MainActivity.kt
│   │   │                       ├── PredictionResponse.kt
│   │   │                       └── StudentData.kt
│   │   │
│   │   └── build.gradle.kts
│   │
│   ├── gradle.properties
│   ├── gradlew
│   ├── gradlew.bat
│   └── settings.gradle.kts
│
├── backend/
│   ├── model/
│   │   └── final_exam_predictor_model.joblib
│   │
│   ├── main.py
│   ├── streamlit_app.py
│   └── requirements.txt
│
├── .gitignore
└── README.md
```

---

## 📱 Android App Details

### Architecture

Key source files, located in `android-app/`:

```text
android-app/
└── app/
    └── src/
        └── main/
            ├── AndroidManifest.xml
            └── java/
                └── com/
                    └── sangamgupta/
                        └── studentmarkspredictor/
                            ├── ApiClient.kt
                            ├── ApiService.kt
                            ├── MainActivity.kt
                            ├── PredictionResponse.kt
                            └── StudentData.kt
```

The Android application uses Retrofit to communicate with the FastAPI backend. The API service defines:

```kotlin
@POST("predict")
suspend fun predictMarks(
    @Body studentData: StudentData
): Response<PredictionResponse>
```

The Android application creates a `StudentData` object from the user's inputs and sends it to the backend.

### UI (Jetpack Compose)

| Section | Fields |
|---|---|
| Student Information | Age, Gender, Course, Academic Year |
| Academic Information | Study Hours Per Day, Attendance Percent, Previous Semester Marks, Assignment Score, Internal Marks |
| Lifestyle Information | Sleep Hours Per Day, Screen Time Hours Per Day, Social Media Hours Per Day, Practice Tests Completed |
| Other Information | Extracurricular Level, Internet Access, Parent Education, Scholarship, Part-Time Job |

### Dark Mode & Settings

The Android application includes a Settings screen with a Dark Mode toggle, plus an About section containing:

```text
Developer:    Sangam Gupta
Email:        contact@sangamgupta.in
Website:      sangamgupta.in
App Version:  1.0.0
```

The About section also contains a project description explaining the purpose of Student Marks Predictor.

### Configuration

```text
Application ID:  com.sangamgupta.studentmarkspredictor
minSdk:          24
targetSdk:       37
compileSdk:      37
versionCode:     2
versionName:     1.0.1
```

The application includes the Internet permission because it communicates with the remotely deployed FastAPI backend.

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/TheSangamX/student-marks-predictor.git
cd student-marks-predictor
```

### 2. Create a Virtual Environment

**Windows**

```bash
python -m venv venv
venv\Scripts\activate
```

**Linux/macOS**

```bash
python3 -m venv venv
source venv/bin/activate
```

### 3. Install Backend Dependencies

```bash
pip install -r backend/requirements.txt
```

### 4. Start FastAPI

```bash
cd backend
uvicorn main:app --host 0.0.0.0 --port 8000
```

- API: http://127.0.0.1:8000
- Swagger/OpenAPI docs: http://127.0.0.1:8000/docs

### 5. Run the Streamlit Application

From the project root:

```bash
streamlit run backend/streamlit_app.py
```

The Streamlit application will open in the browser and communicates with the FastAPI prediction backend.

> If you want to run Streamlit and FastAPI completely locally, configure the Streamlit API URL to point to the locally running FastAPI server.

### 6. Android Application Setup

Open `android-app/` in Android Studio, then:

1. Allow Android Studio to sync the Gradle project.
2. Make sure the required Android SDK is installed.
3. Connect an Android device or start an emulator.
4. Build the project.
5. Run the application.

The Android application communicates with the FastAPI backend using Retrofit.

---

## ☁️ Deployment

The FastAPI backend was deployed on an **AWS EC2 Ubuntu server**. The deployment process included:

1. Creating the project environment on the EC2 instance.
2. Creating a Python virtual environment.
3. Installing the backend dependencies.
4. Running the FastAPI application using Uvicorn.
5. Testing the API through Swagger/OpenAPI.
6. Configuring the backend as a systemd service.
7. Running the API as a background service.
8. Configuring network access for the API.
9. Connecting the Android application to the deployed API.
10. Connecting the Streamlit application to the same backend.

**Deployment Architecture**

```text
GitHub Repository
       │
       ▼
AWS EC2
       │
       ▼
Ubuntu Server
       │
       ▼
Python Virtual Environment
       │
       ▼
FastAPI
       │
       ▼
Uvicorn
       │
       ▼
ML Model
       │
       ├───────────────┐
       ▼               ▼
Streamlit          Android App
```

### Backend Service

The FastAPI backend was configured as a **systemd service** on the AWS EC2 server, allowing it to run as a background service instead of requiring Uvicorn to remain attached to an interactive terminal session. The service was tested to confirm that the API remained active and handled prediction requests successfully.

### API Testing

The FastAPI backend was tested using its Swagger/OpenAPI documentation (`/docs`). The `/predict` endpoint was tested with sample student data, and successful prediction requests returned `HTTP 200 OK`. The Android application was also tested against the deployed FastAPI backend and successfully displayed prediction results. The Streamlit web application was tested using the same backend prediction service.

---

## 🔒 Current Deployment Notes

The current Android-to-backend connection uses an **HTTP** endpoint rather than HTTPS. Therefore, the Android application is configured with:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

and:

```xml
android:usesCleartextTraffic="true"
```

The current implementation was used for the deployed project and testing. For a future production-hardening phase, HTTPS with a proper domain name should be implemented.

---

## 📊 Project Status

**Completed:**

- ✅ Machine learning prediction pipeline
- ✅ Serialized `.joblib` model
- ✅ FastAPI REST API
- ✅ Pydantic request schema
- ✅ `/predict` endpoint
- ✅ Swagger/OpenAPI testing
- ✅ AWS EC2 deployment
- ✅ Uvicorn server
- ✅ systemd backend service
- ✅ Streamlit web application
- ✅ Streamlit deployment
- ✅ Native Android application
- ✅ Kotlin implementation
- ✅ Jetpack Compose UI
- ✅ Retrofit API integration
- ✅ Android prediction flow
- ✅ Loading state
- ✅ Prediction result display
- ✅ Error handling
- ✅ Dark Mode
- ✅ Settings screen
- ✅ About section
- ✅ Google Play testing/release setup
- ✅ GitHub repository

---

## 🎯 Project Objective

The primary objective of Student Marks Predictor was not only to build a machine learning model, but to understand and implement the complete process of taking a machine learning solution from development to deployment.

```text
Machine Learning
       ↓
Model Serialization
       ↓
FastAPI REST API
       ↓
Cloud Deployment
       ↓
Web Application
       ↓
Android Application
       ↓
Google Play Distribution
```

This makes the project an end-to-end demonstration of **Machine Learning + API Development + Cloud Deployment + Web Development + Android Development**.

---

## 🔮 Future Improvements

The following are possible future improvements and are **not currently claimed as implemented features**:

- HTTPS-secured API
- Custom domain for the backend
- API authentication
- Improved API security
- Production monitoring
- Automated deployment pipeline
- More comprehensive model evaluation documentation
- Model retraining workflow
- Expanded automated testing
- Improved error handling
- More detailed prediction analytics
- Improved deployment automation

---

## ⚠️ Disclaimer

Student Marks Predictor provides an **estimated final marks prediction** generated by a machine learning model. The predicted marks are not a guarantee of actual examination results. Actual academic performance can vary depending on many factors, including factors that may not be represented in the model's input data. This application is intended for **educational and demonstration purposes**.

---

## ⭐ Conclusion

Student Marks Predictor demonstrates how a trained machine learning model can be transformed into a practical application and made accessible through multiple client platforms. The same backend prediction service powers both the Streamlit web application and the Android application, providing a clear separation between the machine learning model, API layer, and user interfaces. The project represents a complete journey from a machine learning prediction pipeline to cloud deployment and real-world application integration.

---

## 👨‍💻 Developer

**Sangam Gupta**

- 📧 Email: contact@sangamgupta.in
- 🌐 Website: https://sangamgupta.in
- 💻 GitHub: https://github.com/TheSangamX

---

<div align="center">

**Built by Sangam Gupta**

*Predict. Plan. Perform.*

</div>