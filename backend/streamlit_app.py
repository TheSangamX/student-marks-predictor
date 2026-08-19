import streamlit as st
import requests


st.set_page_config(
    page_title="Student Marks Predictor",
    page_icon="🎓",
    layout="centered"
)

st.title("🎓 Student Final Marks Predictor")
st.write("Enter student details to predict final exam marks.")


# -----------------------
# STUDENT INFORMATION
# -----------------------

age = st.number_input(
    "Age",
    min_value=15,
    max_value=100,
    value=22
)

gender = st.selectbox(
    "Gender",
    ["Male", "Female", "Other"]
)

course = st.selectbox(
    "Course",
    ["Data Science", "Computer Engineering", "Mechanical Engineering", "Civil Engineering", "Electronics", "Information Technology"]
)

year = st.selectbox(
    "Year",
    ["1st", "2nd", "3rd", "4th"]
)


# -----------------------
# ACADEMIC INFORMATION
# -----------------------

study_hours_per_day = st.number_input(
    "Study Hours Per Day",
    min_value=0.0,
    max_value=24.0,
    value=5.0
)

attendance_percent = st.number_input(
    "Attendance Percent",
    min_value=0.0,
    max_value=100.0,
    value=70.0
)

previous_semester_marks = st.number_input(
    "Previous Semester Marks",
    min_value=0.0,
    max_value=100.0,
    value=50.0
)

assignment_score = st.number_input(
    "Assignment Score",
    min_value=0.0,
    max_value=100.0,
    value=70.0
)

internal_marks = st.number_input(
    "Internal Marks",
    min_value=0.0,
    max_value=100.0,
    value=40.0
)


# -----------------------
# LIFESTYLE
# -----------------------

sleep_hours_per_day = st.number_input(
    "Sleep Hours Per Day",
    min_value=0.0,
    max_value=24.0,
    value=7.0
)

screen_time_hours_per_day = st.number_input(
    "Screen Time Hours Per Day",
    min_value=0.0,
    max_value=24.0,
    value=3.0
)

social_media_hours_per_day = st.number_input(
    "Social Media Hours Per Day",
    min_value=0.0,
    max_value=24.0,
    value=2.0
)

practice_tests_completed = st.number_input(
    "Practice Tests Completed",
    min_value=0,
    max_value=100,
    value=3
)


# -----------------------
# OTHER INFORMATION
# -----------------------

extracurricular_level = st.selectbox(
    "Extracurricular Level",
    ["High", "Medium", "Low"]
)

internet_access = st.selectbox(
    "Internet Access",
    ["Good", "Average", "Poor"]
)

parent_education = st.selectbox(
    "Parent Education",
    ["School", "Diploma", "Graduate", "Postgraduate"]
)

scholarship = st.selectbox(
    "Scholarship",
    ["Yes", "No"]
)

part_time_job = st.selectbox(
    "Part-Time Job",
    ["Yes", "No"]
)


# -----------------------
# PREDICTION
# -----------------------

if st.button("Predict Final Marks"):

    data = {
        "age": age,
        "gender": gender,
        "course": course,
        "year": year,
        "study_hours_per_day": study_hours_per_day,
        "attendance_percent": attendance_percent,
        "previous_semester_marks": previous_semester_marks,
        "assignment_score": assignment_score,
        "internal_marks": internal_marks,
        "sleep_hours_per_day": sleep_hours_per_day,
        "screen_time_hours_per_day": screen_time_hours_per_day,
        "social_media_hours_per_day": social_media_hours_per_day,
        "practice_tests_completed": practice_tests_completed,
        "extracurricular_level": extracurricular_level,
        "internet_access": internet_access,
        "parent_education": parent_education,
        "scholarship": scholarship,
        "part_time_job": part_time_job
    }

    try:

        response = requests.post(
            "http://127.0.0.1:8000/predict",
            json=data
        )

        if response.status_code == 200:

            result = response.json()

            predicted_marks = result[
                "predicted_final_marks"
            ]

            st.success(
                f"🎯 Predicted Final Marks: {predicted_marks:.2f}"
            )

        else:
            st.error(
                f"API Error: {response.status_code}"
            )

    except requests.exceptions.ConnectionError:

        st.error(
            "❌ Cannot connect to FastAPI. "
            "Please make sure the API is running."
        )