package com.sangamgupta.studentmarkspredictor

data class StudentData(
    val age: Int,
    val gender: String,
    val course: String,
    val year: String,

    val study_hours_per_day: Double,
    val attendance_percent: Double,
    val previous_semester_marks: Double,
    val assignment_score: Double,
    val internal_marks: Double,

    val sleep_hours_per_day: Double,
    val screen_time_hours_per_day: Double,
    val social_media_hours_per_day: Double,
    val practice_tests_completed: Int,

    val extracurricular_level: String,
    val internet_access: String,
    val parent_education: String,
    val scholarship: String,
    val part_time_job: String
)