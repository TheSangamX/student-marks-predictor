package com.sangamgupta.studentmarkspredictor

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sangamgupta.studentmarkspredictor.ui.theme.StudentMarksPredictorTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val systemTheme = isSystemInDarkTheme()
            var darkTheme by remember { mutableStateOf(systemTheme) }
            var showSettings by remember { mutableStateOf(false) }

            StudentMarksPredictorTheme(darkTheme = darkTheme) {
                if (showSettings) {
                    SettingsScreen(
                        isDarkTheme = darkTheme,
                        onToggleTheme = { darkTheme = !darkTheme },
                        onBack = { showSettings = false }
                    )
                } else {
                    StudentMarksApp(onOpenSettings = { showSettings = true })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SettingsCard(title = "Appearance") {
                ListItem(
                    headlineContent = { Text("Dark Mode") },
                    supportingContent = { Text(if (isDarkTheme) "Enabled" else "Disabled") },
                    leadingContent = {
                        Icon(
                            imageVector = if (isDarkTheme) Icons.Default.Nightlight else Icons.Default.WbSunny,
                            contentDescription = null
                        )
                    },
                    trailingContent = {
                        Switch(
                            checked = isDarkTheme,
                            onCheckedChange = { onToggleTheme() }
                        )
                    }
                )
            }

            SettingsCard(title = "About") {
                AboutRow(Icons.Default.Person, "Developer", "Sangam Gupta")
                AboutRow(Icons.Default.Email, "Email", "contact@sangamgupta.in")
                AboutRow(Icons.Default.Language, "Website", "sangamgupta.in")
                AboutRow(Icons.Default.Info, "App Version", "1.0.0")
            }

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "🎓 Student Marks Predictor",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "An AI-powered machine learning application that predicts student final exam marks based on academic performance, study habits, lifestyle factors, and other relevant information.",
                        modifier = Modifier.padding(top = 12.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsCard(title: String, content: @Composable () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            Text(
                text = title,
                modifier = Modifier.padding(start = 24.dp, top = 12.dp, bottom = 4.dp),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
            content()
        }
    }
}

@Composable
private fun AboutRow(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, value: String) {
    ListItem(
        headlineContent = { Text(title) },
        supportingContent = { Text(value) },
        leadingContent = { Icon(icon, contentDescription = null) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun NumberInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.isEmpty() || it.matches(Regex("^\\d*\\.?\\d*$"))) {
                onValueChange(it)
            }
        },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentMarksApp(onOpenSettings: () -> Unit) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // -------------------------
    // INPUT VARIABLES
    // -------------------------
    var age by remember { mutableStateOf("22") }
    var gender by remember { mutableStateOf("Male") }
    var course by remember { mutableStateOf("Data Science") }
    var year by remember { mutableStateOf("4th") }
    
    var studyHours by remember { mutableStateOf("4.0") }
    var attendance by remember { mutableStateOf("75.0") }
    var previousMarks by remember { mutableStateOf("70.0") }
    var assignmentScore by remember { mutableStateOf("80.0") }
    var internalMarks by remember { mutableStateOf("90.0") }
    
    var sleepHours by remember { mutableStateOf("7.0") }
    var screenTime by remember { mutableStateOf("3.0") }
    var socialMediaHours by remember { mutableStateOf("2.0") }
    var practiceTests by remember { mutableStateOf("5") }
    
    var extracurricularLevel by remember { mutableStateOf("High") }
    var internetAccess by remember { mutableStateOf("Good") }
    var parentEducation by remember { mutableStateOf("Graduate") }
    var scholarship by remember { mutableStateOf("Yes") }
    var partTimeJob by remember { mutableStateOf("No") }

    // Result State
    var predictionResult by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "🎓 Student Marks Predictor") },
                actions = {
                    IconButton(onClick = onOpenSettings) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Open settings"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(text = "Student Information", style = MaterialTheme.typography.titleLarge)

            NumberInputField(value = age, onValueChange = { age = it }, label = "Age (15-100)")
            
            DropdownField(
                label = "Gender",
                options = listOf("Male", "Female", "Other"),
                selectedOption = gender,
                onOptionSelected = { gender = it }
            )

            DropdownField(
                label = "Course",
                options = listOf("Data Science", "Computer Engineering", "Mechanical Engineering", "Civil Engineering", "Electronics", "Information Technology"),
                selectedOption = course,
                onOptionSelected = { course = it }
            )

            DropdownField(
                label = "Year",
                options = listOf("1st", "2nd", "3rd", "4th"),
                selectedOption = year,
                onOptionSelected = { year = it }
            )

            Text(text = "Academic Information", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 16.dp))

            NumberInputField(value = studyHours, onValueChange = { studyHours = it }, label = "Study Hours Per Day (0-24)")
            NumberInputField(value = attendance, onValueChange = { attendance = it }, label = "Attendance Percent (0-100)")
            NumberInputField(value = previousMarks, onValueChange = { previousMarks = it }, label = "Previous Semester Marks (0-100)")
            NumberInputField(value = assignmentScore, onValueChange = { assignmentScore = it }, label = "Assignment Score (0-100)")
            NumberInputField(value = internalMarks, onValueChange = { internalMarks = it }, label = "Internal Marks (0-100)")

            Text(text = "Lifestyle Information", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 16.dp))

            NumberInputField(value = sleepHours, onValueChange = { sleepHours = it }, label = "Sleep Hours Per Day (0-24)")
            NumberInputField(value = screenTime, onValueChange = { screenTime = it }, label = "Screen Time Hours Per Day (0-24)")
            NumberInputField(value = socialMediaHours, onValueChange = { socialMediaHours = it }, label = "Social Media Hours Per Day (0-24)")
            NumberInputField(value = practiceTests, onValueChange = { practiceTests = it }, label = "Practice Tests Completed")

            Text(text = "Other Information", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 16.dp))

            DropdownField(
                label = "Extracurricular Level",
                options = listOf("High", "Medium", "Low"),
                selectedOption = extracurricularLevel,
                onOptionSelected = { extracurricularLevel = it }
            )

            DropdownField(
                label = "Internet Access",
                options = listOf("Good", "Average", "Poor"),
                selectedOption = internetAccess,
                onOptionSelected = { internetAccess = it }
            )

            DropdownField(
                label = "Parent Education",
                options = listOf("School", "Diploma", "Graduate", "Postgraduate"),
                selectedOption = parentEducation,
                onOptionSelected = { parentEducation = it }
            )

            DropdownField(
                label = "Scholarship",
                options = listOf("Yes", "No"),
                selectedOption = scholarship,
                onOptionSelected = { scholarship = it }
            )

            DropdownField(
                label = "Part-Time Job",
                options = listOf("Yes", "No"),
                selectedOption = partTimeJob,
                onOptionSelected = { partTimeJob = it }
            )

            // -------------------------
            // RESULT DISPLAY
            // -------------------------
            predictionResult?.let {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(8.dp))
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🎯 Predicted Final Marks", 
                        fontSize = 18.sp, 
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                    )
                    Text(
                        text = it, 
                        fontSize = 32.sp, 
                        fontWeight = FontWeight.Bold, 
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // -------------------------
            // PREDICT BUTTON
            // -------------------------
            Button(
                onClick = {
                    isLoading = true
                    predictionResult = null
                    
                    val studentData = StudentData(
                        age = age.toIntOrNull() ?: 0,
                        gender = gender,
                        course = course,
                        year = year,
                        study_hours_per_day = studyHours.toDoubleOrNull() ?: 0.0,
                        attendance_percent = attendance.toDoubleOrNull() ?: 0.0,
                        previous_semester_marks = previousMarks.toDoubleOrNull() ?: 0.0,
                        assignment_score = assignmentScore.toDoubleOrNull() ?: 0.0,
                        internal_marks = internalMarks.toDoubleOrNull() ?: 0.0,
                        sleep_hours_per_day = sleepHours.toDoubleOrNull() ?: 0.0,
                        screen_time_hours_per_day = screenTime.toDoubleOrNull() ?: 0.0,
                        social_media_hours_per_day = socialMediaHours.toDoubleOrNull() ?: 0.0,
                        practice_tests_completed = practiceTests.toIntOrNull() ?: 0,
                        extracurricular_level = extracurricularLevel,
                        internet_access = internetAccess,
                        parent_education = parentEducation,
                        scholarship = scholarship,
                        part_time_job = partTimeJob
                    )

                    coroutineScope.launch {
                        try {
                            val response = ApiClient.apiService.predictMarks(studentData)
                            if (response.isSuccessful && response.body() != null) {
                                val result = response.body()?.predicted_final_marks
                                predictionResult = String.format("%.2f", result)
                            } else {
                                Toast.makeText(context, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                            Toast.makeText(context, "Failed: ${e.message}", Toast.LENGTH_LONG).show()
                        } finally {
                            isLoading = false
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                enabled = !isLoading,
                contentPadding = PaddingValues(16.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Text(text = "Predict Final Marks")
                }
            }
        }
    }
}
