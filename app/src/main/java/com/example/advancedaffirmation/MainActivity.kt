 package com.example.advancedaffirmation

 import android.os.Bundle
                        import androidx.activity.ComponentActivity
                        import androidx.activity.compose.setContent
                        import androidx.compose.animation.animateContentSize
                        import androidx.compose.animation.core.Spring
                        import androidx.compose.animation.core.animateDpAsState
                        import androidx.compose.animation.core.spring
                        import androidx.compose.foundation.background
                        import androidx.compose.foundation.layout.*
                        import androidx.compose.foundation.rememberScrollState
                        import androidx.compose.foundation.shape.RoundedCornerShape
                        import androidx.compose.foundation.verticalScroll
                        import androidx.compose.material.*
                        import androidx.compose.material.icons.Icons
 import androidx.compose.material.icons.filled.ExpandLess
 import androidx.compose.material.icons.filled.ExpandMore
 import androidx.compose.runtime.Composable
                        import androidx.compose.runtime.mutableStateOf
                        import androidx.compose.runtime.setValue
                        import androidx.compose.runtime.getValue
                        import androidx.compose.runtime.remember
                        import androidx.compose.runtime.saveable.rememberSaveable
                        import androidx.compose.ui.Alignment
                        import androidx.compose.ui.Modifier
                        import androidx.compose.ui.graphics.Color
                        import androidx.compose.ui.layout.VerticalAlignmentLine
                        import androidx.compose.ui.res.stringResource
                        import androidx.compose.ui.text.font.Font
                        import androidx.compose.ui.text.font.FontFamily
                        import androidx.compose.ui.text.font.FontWeight
                        import androidx.compose.ui.unit.dp
                        import androidx.compose.ui.unit.sp
 import com.example.advancedaffirmation.ui.theme.AdvancedAffirmationTheme
 import com.example.advancedaffirmation.ui.theme.beautyPink
 import com.example.advancedaffirmation.ui.theme.darkBlue


 class MainActivity : ComponentActivity() {
                    override fun onCreate(savedInstanceState: Bundle?) {
                        super.onCreate(savedInstanceState)
                        setContent {
                            AdvancedAffirmationTheme {
                                top()
                            }
                        }
                    }
                }



                @Composable
                fun OnboardingScreen( onContinueClicked: () -> Unit) {
                    Surface(
                        color = beautyPink
                    ) {

                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        )
                        {
                            val fontFamily = FontFamily(
                                Font(R.font.brittanysignature),
                            )

                            Text(
                                "Just a constant reminder",
                                fontSize = 30.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom
                        ) {

                            Button(
                                modifier = Modifier.padding(vertical = 20.dp),
                                onClick = onContinueClicked,
                                shape = RoundedCornerShape(15.dp),
                            )
                            {
                                Text("Get Started")
                            }

                        }

                    }

                }
                @Composable
                fun top () {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text("Affirmation")
                                },
                                modifier = Modifier.background(darkBlue)

                            )

                        }
                    )
                    {
                        var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
                        if (shouldShowOnboarding) {
                            OnboardingScreen( onContinueClicked = {shouldShowOnboarding = false} )
                        } else {
                            Affirmation()
                        }
                    }
                }
                val ff = FontFamily(Font(R.font.dk_lemon_yellow_sun))

                @Composable
                fun Affirmation(
                    names: List<String> = listOf(
                        "An Inspiration", "Loved", "Treasured", "Smart", "A VIP",
                        "The best", "Strong", "Special", "Good looking", "Amazing"
                    ),
                ) {

                    Column(modifier = Modifier.verticalScroll(rememberScrollState()))
                    {
                        for (name in names) {
                            Greeting(name = name)
                        }
                    }
                }

                @Composable
                fun Greeting(name: String) {
                    var expanded by remember { mutableStateOf(false) }
                    val extraPadding by animateDpAsState (if (expanded) 30.dp else 0.dp)

                    Surface(
                        color = beautyPink,
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 20.dp),
                        shape = RoundedCornerShape(20.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .padding(20.dp)
                                .animateContentSize(
                                    animationSpec = spring(
                                        dampingRatio = Spring.DampingRatioMediumBouncy,
                                        stiffness = Spring.StiffnessLow
                                    )
                                )
                        )
                        {
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(bottom = extraPadding)
                            )
                            {
                                Text(text = "You are,")
                                Text(text = name, fontFamily = ff, fontSize = 30.sp)
                            }
                            IconButton(onClick = { expanded = !expanded })
                            {
                                Icon(
                                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                                    contentDescription = if (expanded) {
                                        stringResource(id = R.string.show_less)
                                    } else {
                                        stringResource(id = R.string.show_more)
                                    }
                                )

                            }

                            // OutlinedButton(
                            //   onClick = { expanded = !expanded },
                            //  shape = RoundedCornerShape(10.dp) )
                            //{ Text(if (expanded) "Show more" else "Show less") }
                        }
                    }

                }
