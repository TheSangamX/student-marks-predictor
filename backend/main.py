from fastapi import FastAPI
from pydantic import BaseModel
import pandas as pd
import joblib


app = FastAPI(
    title="Student Final Marks Prediction API",
    version="1.0.0"
)


# Load trained ML pipeline
model = joblib.load(
    "model/final_exam_predictor_model.joblib"
)


# Input schema
class StudentInput(BaseModel):

    age: int
    gender: str
    course: str
    year: str

    study_hours_per_day: float
    attendance_percent: float
    previous_semester_marks: float
    assignment_score: float
    internal_marks: float

    sleep_hours_per_day: float
    screen_time_hours_per_day: float
    social_media_hours_per_day: float

    practice_tests_completed: int

    extracurricular_level: str
    internet_access: str
    parent_education: str
    scholarship: str
    part_time_job: str


# Home endpoint
@app.get("/")
def home():

    return {
        "message": "Student Marks Prediction API is running"
    }


# Prediction endpoint
@app.post("/predict")
def predict(student: StudentInput):

    # Convert JSON input to DataFrame
    input_data = pd.DataFrame([
        student.model_dump()
    ])

    # Prediction
    prediction = model.predict(input_data)

    # Return JSON response
    return {
        "predicted_final_marks": float(prediction[0])
    }