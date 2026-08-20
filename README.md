# Student Marks Predictor

An end-to-end machine-learning application that estimates a student's final-exam marks from academic, lifestyle, and background information.

The repository contains a trained prediction model, a FastAPI service that exposes it, a Streamlit web interface, and an Android application built with Kotlin and Jetpack Compose.

## Components

| Location | Purpose |
| --- | --- |
| `backend/main.py` | FastAPI service that loads the trained model and provides the prediction API. |
| `backend/model/final_exam_predictor_model.joblib` | Pre-trained scikit-learn pipeline used for inference. |
| `backend/streamlit_app.py` | Browser-based form that calls the prediction API. |
| `android-app/` | Android client built with Kotlin, Jetpack Compose, and Retrofit. |

## How it works

1. A user enters student information in the Android or Streamlit interface.
2. The client sends a JSON request to `POST /predict`.
3. FastAPI converts the request into a pandas `DataFrame` and passes it to the saved model pipeline.
4. The API returns the estimated final marks as JSON.

The supplied model is used for prediction only; this repository does not include training code or the source training dataset.

## Requirements

- A Python version compatible with the pinned packages in `backend/requirements.txt`
- Android Studio (for the Android app)
- An Android device/emulator running Android 7.0 (API 24) or later

## Run the backend locally

From the repository root, create and activate a virtual environment:

```powershell
python -m venv .venv
.\.venv\Scripts\Activate.ps1
```

Install the backend dependencies:

```powershell
pip install -r backend/requirements.txt
```

Start the API **from the `backend` directory** so the model's relative path resolves correctly:

```powershell
cd backend
uvicorn main:app --reload --host 0.0.0.0 --port 8000
```

The service will be available at:

- API root: `http://127.0.0.1:8000/`
- Interactive API documentation: `http://127.0.0.1:8000/docs`

## Run the Streamlit interface

With the API running, open a second terminal, activate the same virtual environment, and run:

```powershell
cd backend
streamlit run streamlit_app.py
```

`streamlit_app.py` currently targets the deployed endpoint `http://13.235.71.113:8000/predict`. To use a local backend instead, change that URL to `http://127.0.0.1:8000/predict`.

## Run the Android app

1. Open `android-app` in Android Studio.
2. Let Gradle sync the project.
3. Set the API base URL in `app/src/main/java/com/sangamgupta/studentmarkspredictor/ApiClient.kt`.
4. Run the `app` configuration on a device or emulator.

The app currently uses the deployed backend:

```kotlin
private const val BASE_URL = "http://13.235.71.113:8000/"
```

For an Android emulator connecting to a FastAPI server running on the same computer, use:

```kotlin
private const val BASE_URL = "http://10.0.2.2:8000/"
```

For a physical device, use your computer's reachable LAN IP address (for example, `http://192.168.1.10:8000/`) and ensure the device and computer are on the same network. The Android manifest already permits internet access and cleartext HTTP traffic for this setup.

## API reference

### `GET /`

Returns a basic service-status message.

### `POST /predict`

Accepts the following JSON body:

```json
{
  "age": 22,
  "gender": "Male",
  "course": "Data Science",
  "year": "4th",
  "study_hours_per_day": 4.0,
  "attendance_percent": 75.0,
  "previous_semester_marks": 70.0,
  "assignment_score": 80.0,
  "internal_marks": 90.0,
  "sleep_hours_per_day": 7.0,
  "screen_time_hours_per_day": 3.0,
  "social_media_hours_per_day": 2.0,
  "practice_tests_completed": 5,
  "extracurricular_level": "High",
  "internet_access": "Good",
  "parent_education": "Graduate",
  "scholarship": "Yes",
  "part_time_job": "No"
}
```

Example response:

```json
{
  "predicted_final_marks": 78.42
}
```

The categorical values should use the labels presented in the clients:

| Field | Supported values |
| --- | --- |
| `gender` | `Male`, `Female`, `Other` |
| `course` | `Data Science`, `Computer Engineering`, `Mechanical Engineering`, `Civil Engineering`, `Electronics`, `Information Technology` |
| `year` | `1st`, `2nd`, `3rd`, `4th` |
| `extracurricular_level` | `High`, `Medium`, `Low` |
| `internet_access` | `Good`, `Average`, `Poor` |
| `parent_education` | `School`, `Diploma`, `Graduate`, `Postgraduate` |
| `scholarship`, `part_time_job` | `Yes`, `No` |

## Test the API

After starting the backend, submit a prediction directly from PowerShell:

```powershell
$body = @{
  age = 22; gender = 'Male'; course = 'Data Science'; year = '4th'
  study_hours_per_day = 4.0; attendance_percent = 75.0
  previous_semester_marks = 70.0; assignment_score = 80.0; internal_marks = 90.0
  sleep_hours_per_day = 7.0; screen_time_hours_per_day = 3.0; social_media_hours_per_day = 2.0
  practice_tests_completed = 5; extracurricular_level = 'High'; internet_access = 'Good'
  parent_education = 'Graduate'; scholarship = 'Yes'; part_time_job = 'No'
} | ConvertTo-Json

Invoke-RestMethod -Uri http://127.0.0.1:8000/predict -Method Post -ContentType 'application/json' -Body $body
```

## Notes

- The backend's Pydantic schema enforces field presence and basic numeric types. The interfaces provide the intended ranges (for example, percentages from 0-100), but the API itself does not currently enforce those range limits.
- Prediction quality depends on how closely submitted data resembles the dataset used to train the bundled model. Treat outputs as estimates, not final academic decisions.
- A release Android App Bundle is included at `android-app/app/release/app-release.aab`.
