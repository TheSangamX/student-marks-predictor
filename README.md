# 🎓 Student Marks Predictor

> An end-to-end machine learning application that predicts a student's final examination marks from academic, study, lifestyle, and other student-related information.

![Python](https://img.shields.io/badge/Python-3.x-blue?logo=python)
![FastAPI](https://img.shields.io/badge/FastAPI-REST%20API-009688?logo=fastapi)
![Streamlit](https://img.shields.io/badge/Streamlit-Web%20App-FF4B4B?logo=streamlit)
![Kotlin](https://img.shields.io/badge/Kotlin-Android-7F52FF?logo=kotlin)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-UI-4285F4)
![scikit-learn](https://img.shields.io/badge/scikit--learn-ML-F7931E?logo=scikitlearn)
![AWS EC2](https://img.shields.io/badge/AWS-EC2-FF9900?logo=amazonaws)
![GitHub](https://img.shields.io/badge/GitHub-Repository-181717?logo=github)

## 📌 Overview

**Student Marks Predictor** is an end-to-end machine learning project built to understand the complete journey of taking a machine learning model from **data analysis and model development to API serving, cloud deployment, web integration, Android integration, and Google Play distribution**.

The project is organized around one central prediction service:

- 🤖 **Machine Learning Pipeline** — preprocesses input data and predicts final examination marks.
- ⚡ **FastAPI Backend** — exposes the trained model through REST API endpoints.
- 🌐 **Streamlit Web Application** — provides a browser-based interface for predictions.
- 📱 **Native Android Application** — built with Kotlin and Jetpack Compose.
- ☁️ **AWS EC2 Deployment** — hosts the FastAPI backend on an Ubuntu server.
- 📦 **Google Play Distribution** — Android application prepared and submitted for testing/release distribution.

Both the Streamlit application and Android application communicate with the **same FastAPI prediction backend**.

---

## 🚀 Live Applications & Resources

| Resource | Link |
|---|---|
| 🌐 Streamlit Web Application | https://thesangamx-student-marks-predictor.streamlit.app/ |
| 📱 Google Play Listing | https://play.google.com/store/apps/details?id=com.sangamgupta.studentmarkspredictor |
| 🧪 Google Play Testing | https://play.google.com/apps/testing/com.sangamgupta.studentmarkspredictor |
| 💻 Source Code | https://github.com/TheSangamX/student-marks-predictor |

> **Note:** Google Play availability depends on the current testing, review, and rollout status.

---

# 🧭 Table of Contents

- [Project Overview](#-overview)
- [Project Objective](#-project-objective)
- [End-to-End Workflow](#-end-to-end-workflow)
- [Key Features](#-key-features)
- [Machine Learning Workflow](#-machine-learning-workflow)
- [Input Features](#-input-features)
- [Technology Stack](#-technology-stack)
- [System Architecture](#-system-architecture)
- [API Reference](#-api-reference)
- [Repository Structure](#-repository-structure)
- [Android Application](#-android-application)
- [Getting Started](#-getting-started)
- [Deployment](#-deployment)
- [Project Status](#-project-status)
- [Future Improvements](#-future-improvements)
- [Disclaimer](#️-disclaimer)
- [Developer](#-developer)

---

# 🎯 Project Objective

The main objective of this project was not only to train a machine learning model, but to understand the complete workflow required to convert an ML solution into a usable application.

```text
Data Collection
      ↓
Exploratory Data Analysis
      ↓
Preprocessing
      ↓
Feature Preparation
      ↓
Train/Test Split
      ↓
ML Model Training
      ↓
Model Evaluation
      ↓
Pipeline Creation
      ↓
Model Serialization (.joblib)
      ↓
FastAPI REST API
      ↓
AWS EC2 Deployment
      ↓
Streamlit Web Application
      ↓
Native Android Application
      ↓
Google Play Distribution
```

This project therefore combines:

> **Machine Learning + Data Processing + API Development + Cloud Deployment + Web Development + Android Development**

---

# 🔄 End-to-End Workflow

```text
                         ┌─────────────────────┐
                         │   Student Data      │
                         │  CSV Dataset        │
                         └──────────┬──────────┘
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │ EDA & Preprocessing │
                         └──────────┬──────────┘
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │ Feature Processing  │
                         │ Scaling + Encoding  │
                         └──────────┬──────────┘
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │ Linear Regression   │
                         └──────────┬──────────┘
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │ Scikit-learn        │
                         │ Pipeline            │
                         └──────────┬──────────┘
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │ Joblib Model File   │
                         └──────────┬──────────┘
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │ FastAPI REST API    │
                         │ /predict            │
                         └──────────┬──────────┘
                                    │
                    ┌───────────────┼───────────────┐
                    ▼               ▼               ▼
            Streamlit Web      Swagger Docs     Android App
                    │                               │
                    └───────────────┬───────────────┘
                                    ▼
                              Final Prediction
```

---

# ✨ Key Features

## 🤖 Machine Learning

- Linear Regression model for predicting final examination marks.
- 80:20 train-test split.
- Numerical and categorical features handled through a single preprocessing pipeline.
- `StandardScaler` applied to selected numerical features.
- `OneHotEncoder` applied to categorical features.
- `ColumnTransformer` used to combine preprocessing steps.
- Complete preprocessing + model workflow stored together using a scikit-learn `Pipeline`.
- Trained pipeline serialized using Joblib.

### Why the Pipeline Matters

The pipeline ensures that the same preprocessing performed during training is automatically applied when making predictions in FastAPI, Streamlit, or Android.

```text
Raw User Input
      ↓
ColumnTransformer
      ├── Numerical Features → StandardScaler
      └── Categorical Features → OneHotEncoder
      ↓
Linear Regression
      ↓
Predicted Final Marks
```

This avoids manually encoding or scaling inputs separately in every application.

---

## ⚡ FastAPI REST API

The backend exposes the trained ML pipeline through a REST API.

### Features

- `GET /` health/status endpoint
- `POST /predict` prediction endpoint
- Pydantic request validation
- JSON request and response handling
- Pandas DataFrame creation before prediction
- Joblib model loading
- Uvicorn ASGI server
- Swagger/OpenAPI documentation

---

## 🌐 Streamlit Web Application

The Streamlit application provides a simple browser interface where users can enter student information and request a prediction.

The application:

1. Collects user input.
2. Sends an HTTP request to FastAPI.
3. FastAPI loads and uses the ML pipeline.
4. The prediction is returned to Streamlit.
5. The result is displayed to the user.

---

## 📱 Native Android Application

The Android application is built using:

- Kotlin
- Jetpack Compose
- Material 3
- Retrofit
- Gson
- Kotlin Coroutines

### Android Features

- Student information form
- Academic information form
- Lifestyle information form
- Other information form
- Dropdown selection fields
- Numeric input fields
- Prediction button
- Loading state
- Prediction result display
- API error handling
- Settings screen
- Dark Mode toggle
- About section
- Developer information
- Contact information
- Website information
- App version information

The Android application does **not** run the machine learning model locally. Instead, it sends data to the deployed FastAPI backend.

---

# 🧾 Input Features

The model uses **18 input features**, grouped into four categories.

## 👤 Student Information

| Feature | Type | Description |
|---|---|---|
| `age` | Integer | Student age |
| `gender` | Categorical | Student gender |
| `course` | Categorical | Student course |
| `year` | Categorical | Academic year |

## 📚 Academic Information

| Feature | Type | Description |
|---|---|---|
| `study_hours_per_day` | Numeric | Study hours per day |
| `attendance_percent` | Numeric | Attendance percentage |
| `previous_semester_marks` | Numeric | Previous semester marks |
| `assignment_score` | Numeric | Assignment score |
| `internal_marks` | Numeric | Internal examination marks |

## 🌙 Lifestyle Information

| Feature | Type | Description |
|---|---|---|
| `sleep_hours_per_day` | Numeric | Sleep hours per day |
| `screen_time_hours_per_day` | Numeric | Daily screen time |
| `social_media_hours_per_day` | Numeric | Daily social media usage |
| `practice_tests_completed` | Numeric | Number of completed practice tests |

## 🧩 Other Information

| Feature | Type | Description |
|---|---|---|
| `extracurricular_level` | Categorical | Extracurricular activity level |
| `internet_access` | Categorical | Internet access quality |
| `parent_education` | Categorical | Parent education level |
| `scholarship` | Categorical | Scholarship status |
| `part_time_job` | Categorical | Part-time job status |

> The target variable is the student's final examination marks.

---

# 🧠 Machine Learning Workflow

## 1. Data Collection

The project uses a student dataset stored in:

```text
data/
└── students_data.csv
```

## 2. Exploratory Data Analysis

The dataset was explored to understand:

- Data structure
- Column types
- Numerical distributions
- Categorical values
- Summary statistics
- Feature relationships
- Missing values and data quality
- The relevance of columns before training

## 3. Feature Selection

The serial number / identifier column was removed because it does not represent a meaningful predictive feature.

The remaining input columns were used as features, while final examination marks were used as the target.

```text
X = Input Features
y = Final Marks
```

## 4. Train-Test Split

The dataset was divided into:

```text
80% → Training Data
20% → Testing Data
```

The training data is used to fit the preprocessing steps and model, while the testing data is reserved for evaluating predictions.

## 5. Preprocessing

The project uses a `ColumnTransformer` inside a scikit-learn pipeline.

### Numerical Features

Selected numerical features are processed using:

```python
StandardScaler()
```

### Categorical Features

Categorical features are processed using:

```python
OneHotEncoder()
```

## 6. Model Training

The model used for this project is:

```text
Linear Regression
```

The model is trained through the pipeline, meaning preprocessing and prediction are connected together.

Conceptually:

```python
pipeline.fit(X_train, y_train)
```

## 7. Prediction

Predictions are generated using:

```python
pipeline.predict(X_test)
```

For real-world applications, raw user input is passed directly to the pipeline:

```text
Raw Input
    ↓
Pipeline
    ├── Scaling
    ├── Encoding
    └── Linear Regression
    ↓
Prediction
```

## 8. Model Serialization

The complete trained pipeline is saved using Joblib:

```text
backend/model/
└── final_exam_predictor_model.joblib
```

Because the preprocessing and regression model are stored together, the backend can directly load the complete prediction workflow.

---

# 🛠️ Technology Stack

| Layer | Technology |
|---|---|
| Programming Language | Python |
| Data Analysis | Pandas |
| Numerical Computing | NumPy |
| Machine Learning | scikit-learn |
| Model | Linear Regression |
| Numerical Scaling | StandardScaler |
| Categorical Encoding | OneHotEncoder |
| Feature Transformation | ColumnTransformer |
| ML Workflow | scikit-learn Pipeline |
| Model Serialization | Joblib |
| Backend API | FastAPI |
| API Server | Uvicorn |
| Request Validation | Pydantic |
| Web Application | Streamlit |
| HTTP Client | Requests |
| Data Visualization | Matplotlib |
| Android Language | Kotlin |
| Android UI | Jetpack Compose |
| UI Components | Material 3 |
| Android Networking | Retrofit |
| JSON Conversion | Gson |
| Async Operations | Kotlin Coroutines |
| Cloud Hosting | AWS EC2 |
| Server OS | Ubuntu |
| Service Management | systemd |
| Version Control | Git |
| Repository Hosting | GitHub |
| Mobile Distribution | Google Play |

---

# 🏗️ System Architecture

```text
                              ┌────────────────────┐
                              │       User         │
                              └─────────┬──────────┘
                                        │
                      ┌─────────────────┴─────────────────┐
                      ▼                                   ▼
          ┌─────────────────────┐             ┌─────────────────────┐
          │ Streamlit Web App   │             │ Native Android App  │
          └──────────┬──────────┘             └──────────┬──────────┘
                     │ HTTP Request                        │ Retrofit / HTTP
                     └────────────────┬────────────────────┘
                                      ▼
                          ┌───────────────────────┐
                          │    FastAPI Backend    │
                          │     POST /predict     │
                          └───────────┬───────────┘
                                      ▼
                          ┌───────────────────────┐
                          │ ML Pipeline (.joblib) │
                          │ Scaling + Encoding +  │
                          │ Linear Regression     │
                          └───────────┬───────────┘
                                      ▼
                          ┌───────────────────────┐
                          │ Predicted Final Marks │
                          └───────────┬───────────┘
                                      │
                      ┌───────────────┴────────────────┐
                      ▼                                ▼
             Streamlit Result                    Android Result
```

---

# ⚙️ API Reference

## `GET /`

Checks whether the prediction API is running.

### Example Response

```json
{
  "message": "Student Marks Prediction API is running"
}
```

---

## `POST /predict`

Accepts student information and returns predicted final examination marks.

### Example Request

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

### Example Response

```json
{
  "predicted_final_marks": 77.78861626341829
}
```

> The exact prediction depends on the submitted input values and the trained model.

When running locally, interactive Swagger/OpenAPI documentation is available at:

```text
http://127.0.0.1:8000/docs
```

---

# 📂 Repository Structure

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
│   ├── main.py
│   ├── requirements.txt
│   └── streamlit_app.py
│
├── data/
│   └── students_data.csv
│
├── notebook/
│   └── Student_Marks_Prediction.ipynb
│
├── .gitignore
└── README.md
```

---

# 📱 Android Application

## Architecture

```text
User Input
    ↓
Jetpack Compose UI
    ↓
StudentData
    ↓
Retrofit
    ↓
FastAPI /predict
    ↓
PredictionResponse
    ↓
Result UI
```

## Settings and About

The application includes a Settings screen with:

- Dark Mode toggle
- Project information
- Developer information
- Contact email
- Website information
- Application version

### Current Configuration

```text
Application ID: com.sangamgupta.studentmarkspredictor

minSdk:         24
targetSdk:      37
compileSdk:     37

versionCode:    2
versionName:    1.0.1
```

The application uses the Internet permission because it communicates with the remotely deployed FastAPI backend.

---

# 🚀 Getting Started

## 1. Clone the Repository

```bash
git clone https://github.com/TheSangamX/student-marks-predictor.git
cd student-marks-predictor
```

## 2. Create a Virtual Environment

### Windows

```bash
python -m venv venv
venv\Scripts\activate
```

### Linux / macOS

```bash
python3 -m venv venv
source venv/bin/activate
```

## 3. Install Dependencies

```bash
pip install -r backend/requirements.txt
```

## 4. Start FastAPI

```bash
cd backend
uvicorn main:app --host 0.0.0.0 --port 8000
```

The API will run locally at:

```text
http://127.0.0.1:8000
```

Swagger documentation:

```text
http://127.0.0.1:8000/docs
```

## 5. Run the Streamlit Application

From the project root:

```bash
streamlit run backend/streamlit_app.py
```

If running both services locally, make sure the Streamlit application's API URL points to the locally running FastAPI backend.

## 6. Run the Android Application

1. Open `android-app/` in Android Studio.
2. Allow Gradle synchronization to complete.
3. Ensure the required Android SDK is installed.
4. Connect an Android device or start an emulator.
5. Build the project.
6. Run the application.

---

# ☁️ Deployment

The FastAPI backend was deployed on an **AWS EC2 Ubuntu server**.

The deployment workflow included:

1. Preparing the EC2 instance.
2. Setting up the project environment.
3. Creating a Python virtual environment.
4. Installing backend dependencies.
5. Running the FastAPI application with Uvicorn.
6. Testing the API using Swagger/OpenAPI.
7. Configuring the backend as a `systemd` service.
8. Running the API as a background service.
9. Configuring network access.
10. Connecting the Streamlit application to the backend.
11. Connecting the Android application to the same backend.

## Deployment Architecture

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
FastAPI + Uvicorn
        │
        ▼
ML Pipeline (.joblib)
        │
   ┌────┴────┐
   ▼         ▼
Streamlit  Android
```

## Backend Service

The FastAPI application is configured as a `systemd` service so that the backend can run as a background service rather than depending on an active terminal session.

---

# 🔒 Current Deployment Notes

The current Android-to-backend implementation uses an HTTP endpoint.

For this reason, the Android application currently includes:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

and cleartext traffic support:

```xml
android:usesCleartextTraffic="true"
```

This reflects the current implementation used for deployment and testing.

For a production-hardening phase, HTTPS and a properly configured domain should be considered.

---

# 📊 Project Status

## Completed

- [x] Dataset added to the repository
- [x] Jupyter Notebook added to the repository
- [x] Exploratory Data Analysis
- [x] Data preprocessing
- [x] Feature preparation
- [x] Train/test split
- [x] Linear Regression model
- [x] Numerical feature scaling
- [x] Categorical feature encoding
- [x] ColumnTransformer
- [x] Scikit-learn Pipeline
- [x] Model evaluation
- [x] Serialized Joblib model
- [x] FastAPI REST API
- [x] Pydantic request schema
- [x] `/predict` endpoint
- [x] Swagger/OpenAPI testing
- [x] AWS EC2 deployment
- [x] Uvicorn server
- [x] systemd backend service
- [x] Streamlit web application
- [x] Streamlit deployment
- [x] Native Android application
- [x] Kotlin implementation
- [x] Jetpack Compose UI
- [x] Retrofit API integration
- [x] Android prediction flow
- [x] Loading state
- [x] Prediction result display
- [x] API error handling
- [x] Dark Mode
- [x] Settings screen
- [x] About section
- [x] Google Play testing/release setup
- [x] GitHub repository

---

# 🔮 Future Improvements

The following are **possible future improvements** and are not claimed as currently implemented features:

- HTTPS-secured backend
- Custom backend domain
- API authentication
- Improved API security
- Production monitoring and logging
- Automated CI/CD deployment
- Expanded automated testing
- More comprehensive model evaluation documentation
- Model retraining workflow
- Model versioning
- Input validation improvements
- Prediction history and analytics
- Deployment automation

---

# ⚠️ Disclaimer

This project generates an **estimated prediction of final examination marks** based on the input features and trained machine learning model.

The predicted result is **not a guarantee of actual examination performance**. Real academic outcomes can be affected by factors that may not be represented in the dataset or model.

This project is intended for:

- Educational purposes
- Machine learning practice
- API development practice
- Deployment learning
- Full-stack ML application development

---

# ⭐ What This Project Demonstrates

This project demonstrates how a machine learning model can move beyond a Jupyter Notebook and become part of a complete application ecosystem.

```text
CSV Dataset
    ↓
EDA
    ↓
Preprocessing
    ↓
Machine Learning
    ↓
Pipeline
    ↓
Joblib Model
    ↓
FastAPI
    ↓
AWS EC2
    ↓
Streamlit Web App
    +
Native Android App
    ↓
Google Play Distribution
```

The same trained model is served once through FastAPI and then consumed by multiple client applications.

---

# 👨‍💻 Developer

**Sangam Gupta**

- 🌐 Website: https://sangamgupta.in
- 📧 Email: contact@sangamgupta.in
- 💻 GitHub: https://github.com/TheSangamX

---

## ⭐ If you found this project interesting

Consider giving the repository a star. It helps support the project and documents the complete journey from **machine learning development to real-world application deployment**.
