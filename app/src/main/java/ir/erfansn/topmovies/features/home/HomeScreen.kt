@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)

package ir.erfansn.topmovies.features.home

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ir.erfansn.topmovies.R
import ir.erfansn.topmovies.ui.theme.TopMoviesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeTopBar(
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            HomeNavigationBar()
        },
    ) {
        LazyColumn(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(42.dp),
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = 24.dp,
            )
        ) {
            items(5) {
                HomeSection()
            }
        }
    }
}

@Composable
fun HomeNavigationBar() {
    NavigationBar {
        repeat(4) {
            NavigationBarItem(
                selected = it == 0,
                onClick = { /*TODO*/ },
                alwaysShowLabel = false,
                icon = {
                    Icon(imageVector = Icons.Rounded.Home, contentDescription = null)
                },
                label = {
                    Text(text = "Home")
                },
            )
        }
    }
}

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    val heightOffsetLimit = with(LocalDensity.current) { -80.dp.toPx() }
    LaunchedEffect(Unit) {
        scrollBehavior.state.heightOffsetLimit = heightOffsetLimit
    }
    val colorTransitionFraction = scrollBehavior.state.overlappedFraction
    val appBarContainerColor by animateColorAsState(
        targetValue = with(MaterialTheme.colorScheme) {
            if (colorTransitionFraction > 0.01f) surfaceColorAtElevation(3.dp) else surface
        },
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
    )
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = appBarContainerColor,
    )

    Box(
        modifier = modifier
            .background(appBarContainerColor)
            .padding(horizontal = 16.dp)
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { delta ->
                    scrollBehavior.state.heightOffset += delta
                },
            ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp + with(LocalDensity.current) { scrollBehavior.state.heightOffset.toDp() })
                .clipToBounds(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.requiredHeight(64.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.outline,
                            shape = CircleShape,
                        )
                        .padding(1.dp)
                        .background(
                            brush = Brush.linearGradient(
                                listOf(
                                    MaterialTheme.colorScheme.background,
                                    MaterialTheme.colorScheme.secondaryContainer,
                                ),
                                end = Offset(0.0f, Float.POSITIVE_INFINITY),
                            ),
                            shape = CircleShape,
                        )
                        .padding(3.dp)
                        .clip(CircleShape),
                    onClick = { },
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.avatar),
                        contentDescription = "Avatar",
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 16.dp),
                ) {
                    Text(
                        text = "Hello!  👋",
                        color = MaterialTheme.colorScheme.tertiary,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = "Erfan Sn",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            }

            FilledTonalIconButton(
                modifier = Modifier
                    .requiredSize(64.dp)
                    .padding(4.dp),
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(16.dp),
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    painter = painterResource(id = R.drawable.ic_notification),
                    contentDescription = "Notifications",
                )
            }
        }
    }
}

@Composable
fun HomeSection() {
    Column() {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            val (indicator, title, all) = createRefs()
            SectionIndicator(
                modifier = Modifier.constrainAs(indicator) {
                    linkTo(
                        top = parent.top,
                        bottom = parent.bottom
                    )
                    start.linkTo(parent.start)
                }
            )
            Text(
                modifier = Modifier.constrainAs(title) {
                    linkTo(
                        top = parent.top,
                        bottom = parent.bottom
                    )
                    start.linkTo(indicator.end, margin = 8.dp)
                },
                text = "Top Movies",
                style = MaterialTheme.typography.titleLarge,
            )
            TextButton(
                modifier = Modifier
                    .constrainAs(all) {
                        linkTo(
                            top = parent.top,
                            bottom = parent.bottom
                        )
                        end.linkTo(parent.end)
                    },
                onClick = { /*TODO*/ },
            ) {
                Text(
                    text = "Show All >",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(10) {
                MovieItem(
                    modifier = Modifier.width(120.dp)
                )
            }
        }
    }
}

@Composable
fun SectionIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(4.dp)
            .height(21.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            )
    )
}

@Composable
fun MovieItem(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .aspectRatio(2 / 3f)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.dune),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        ) {
            val (movieTitle, productYear, rate) = createRefs()

            Text(
                modifier = Modifier.constrainAs(movieTitle) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(productYear.top, margin = 8.dp)
                },
                text = "Dune",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                modifier = Modifier.constrainAs(productYear) {
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                },
                text = "2021",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelSmall,
            )
            Text(
                modifier = Modifier.constrainAs(rate) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
                text = "⭐ 8.3",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    TopMoviesTheme {
        HomeScreen()
    }
}