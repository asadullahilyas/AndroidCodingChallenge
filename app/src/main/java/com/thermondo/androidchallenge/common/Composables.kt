package com.thermondo.androidchallenge.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.thermondo.androidchallenge.R
import com.thermondo.androidchallenge.features.home.presentation.all_launches.LaunchItemToDisplay
import com.thermondo.androidchallenge.ui.theme.ContrastingColor

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LaunchItem(
    modifier: Modifier = Modifier,
    launch: LaunchItemToDisplay,
    onBookmarkClicked: (() -> Unit)? = null,
    onClicked: (() -> Unit)? = null
) {

    Column(
        modifier = with(modifier) {
            if (onClicked != null) {
                then(
                    Modifier.clickable(onClick = onClicked)
                )
            } else {
                this
            }
        },
    ) {
        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.0F)
                .clip(shape = RoundedCornerShape(12.dp)),
            model = launch.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = "Main image of launch",
            loading = placeholder(R.drawable.ic_rocket_placeholder),
            failure = placeholder(R.drawable.ic_rocket_placeholder),
            transition = CrossFade
        ) {
            it.skipMemoryCache(false)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1.0F)) {
                Text(
                    text = launch.date,
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = launch.title,
                    color = ContrastingColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            if (onBookmarkClicked != null) {
                IconButton(onClick = onBookmarkClicked) {
                    Icon(
                        imageVector = if (launch.isBookmarked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Bookmark icon",
                        tint = Color.White
                    )
                }
            }
        }
    }
}