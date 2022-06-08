package com.example.gradecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gradecalculator.ui.theme.GradeCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GradeCalculatorTheme {
                GradingScreen()
            }
        }
    }
}

/**
 * fun calculateGrade
 * calculate input quiz1, quiz2 & exam
 * return result as String
 * **/
private fun calculateGrade(
    quiz1: Double,
    quiz2: Double,
    exam: Double,
): String {
    val result = (((quiz1+quiz2)/2)*0.4)+(exam * 0.6)
    return result.toString()
}


/**
 * @Composable
 * NameField function
 * Holds TextField where the name input occurs
 * The keyboard explicitly invoke the keyboard type to text
 * **/
@Composable
fun NameField(
    value: String,
    onValueChanged: (String) -> Unit
){
    TextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = stringResource(id = R.string.name_label))},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier.background(color = Color.LightGray, shape = RectangleShape)
    )
}

/**
 * @Composable
 * QuizField function
 * Holds TextField where the name input Quiz
 * The keyboard explicitly invoke the keyboard type to number
 * **/
@Composable
fun QuizField(
    quizNo: String,
    value: String,
    onValueChanged: (String) -> Unit
){
    TextField(
        value = value,
        onValueChange = onValueChanged,
        label = {Text(text = stringResource(id = R.string.quiz_label, quizNo))},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.background(color = Color.LightGray, shape = RectangleShape)
    )
}

/**
 * @Composable
 * ExamField function
 * Holds TextField where the name input exam
 * The keyboard explicitly invoke the keyboard type to number
 * It's possible to create one composable for quiz and exam input
 * **/
@Composable
fun ExamField(
    value: String,
    onValueChanged: (String) -> Unit
){
    TextField(
        value = value,
        onValueChange = onValueChanged,
        label = {Text(text = stringResource(id = R.string.exam_label))},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.background(color = Color.LightGray, shape = RectangleShape)
    )
}

/**
 * @Composable
 * GradingScreen function
 * Hoisting the state
 * **/
@Composable
fun GradingScreen() {
    var nameInput by remember { mutableStateOf("") }
    var quiz1Input by remember { mutableStateOf("") }
    var quiz2Input by remember { mutableStateOf("") }
    var examInput by remember { mutableStateOf("") }

    val quiz1 = quiz1Input.toDoubleOrNull() ?: 0.0
    val quiz2 = quiz2Input.toDoubleOrNull() ?: 0.0
    val exam = examInput.toDoubleOrNull() ?: 0.0

    val result = calculateGrade(quiz1, quiz2, exam)
    Column(
        modifier = Modifier.padding((32.dp)),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Simple Grading Calculator",
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))
        NameField(value = nameInput, onValueChanged = { nameInput = it })

        Spacer(modifier = Modifier.height((5.dp)))
        QuizField(
            value = quiz1Input,
            onValueChanged = { quiz1Input = it },
            quizNo = "1"
        )

        Spacer(modifier = Modifier.height((5.dp)))
        QuizField(
            value = quiz2Input,
            onValueChanged = { quiz2Input = it },
            quizNo = "2"
        )

        Spacer(modifier = Modifier.height(5.dp))
        ExamField(value = examInput, onValueChanged = { examInput = it })

        /**
         * In this Row
         * Display the result and the nameInput
         * **/

        /**
         * In this Row
         * Display the result and the nameInput
         * **/
        Spacer(modifier = Modifier.height(5.dp))
        Row{
            Column {
                Text(text = "Name: ")
                Text(text = "Result: ")
            }
            Column {
                Text(text = nameInput)
                Text(text = result)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GradeCalculatorTheme {
        GradingScreen()
    }
}