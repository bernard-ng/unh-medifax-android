package tech.devscast.medifax.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.devscast.medifax.R
import tech.devscast.medifax.ui.theme.poppinsFontFamily


class ListDoctor : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShimmerAnimationTheme(darkTheme = false) {
                Surface(color = MaterialTheme.colorScheme.background) {
                    LazyColumn {
                        repeat(5) {
                            item {
                                ShimmerAnimation()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShimmerAnimation() {
    val transition = rememberInfiniteTransition(
        label = "transition"
    )
    val translateAnim by transition.animateFloat(
        label = "transition",
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )


    val shimmerColorShades = listOf(
        Color(0xFFFFEB3B),
        Color(0xFFFFC107),
        Color(0xFFFF9800),
        Color(0xFFFF5722),
        Color(0xFFF44336),
        Color(0xFFE91E63),
        Color(0xFF9C27B0),
        Color(0xFF673AB7),
        Color(0xFF3F51B5),
        Color(0xFF2196F3),
        Color(0xFF03A9F4),
        Color(0xFF00BCD4),
        Color(0xFF009688),
        Color(0xFF4CAF50),
        Color(0xFF8BC34A),
        Color(0xFFCDDC39),
        Color(0xFFCCCCCC)
    )

    val brush = Brush.linearGradient(
        colors = shimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    ShimmerItem(brush = brush)
}

@Composable
fun ShimmerItem(brush: Brush) {

    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clickable {  }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_black),
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Docteurs",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))


        DoctorItem("Dr. CANSA", "Chirurgien", 5f, R.drawable.profil, 4.7, brush)
        DoctorItem("Dr. BUNDA", "Cardiologue", 10f, R.drawable.doctor_svgrepo_com, 4.7, brush)
        DoctorItem("Dr. NGANDU", "Dentiste", 8f, R.drawable.doctor_svgrepo_com, 4.7, brush)
        DoctorItem("Dr. KYANGWA", "Ophtalmologue", 12f, R.drawable.doctor_svgrepo_com, 4.7, brush)
        DoctorItem("Dr. KAKANGA", "Gynécolgue", 15f, R.drawable.doctor_svgrepo_com, 4.7, brush)
        DoctorItem("Dr. KINYANTA", "Pédiatre", 20f, R.drawable.doctor_svgrepo_com, 4.7, brush)

    }
}


@Composable
fun DoctorItem(
    name: String,
    specialty: String,
    distance: Float,
    imageResourceId: Int,
    rating: Double,
    brush: Brush,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(width = 1.dp, color = Color.LightGray)
            //.clickable {
            //navController.navigate("appointmentScreen/$name")
           // }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = "Photo du Médecin",
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Black,
                    fontFamily = poppinsFontFamily,
                    fontSize = 27.sp,
                    color = Color(0xFF223A6A)
                )
                Text(
                    text = specialty,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppinsFontFamily,
                    fontSize = 20.sp,
                    color = Color(0xFFCCCCCC)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Distance: $distance km",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                RatingBar(rating = rating, Color(0xFFFFC107))
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .padding(vertical = 8.dp)
                .background(brush = brush)
        )
    }
}

@Composable
fun RatingBar(rating: Double, starColor: Color) {
    Row {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "Rating",
            tint = starColor,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = rating.toString(),
            style = TextStyle(
                color = Color.Gray,
                fontSize = 16.sp
            ),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}





@Composable
fun ShimmerAnimationTheme(darkTheme: Boolean, function: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        MaterialTheme.colorScheme.copy(
            background = Color.Black
        )
    } else {
        MaterialTheme.colorScheme.copy(
            background = Color.White
        )
    }
    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        content = function
    )
}

@Preview(showBackground = true, device = "id:pixel_8_pro", showSystemUi = true)
@Composable
fun PreviewShimmerAnimation() {
    ShimmerAnimation()
}