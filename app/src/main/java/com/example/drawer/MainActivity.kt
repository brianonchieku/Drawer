package com.example.drawer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.drawer.ui.theme.DrawerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Drawer()
        }
    }
}

@Composable
fun Drawer() {
    val item = listOf(
        Items({ Icon(painterResource(id = R.drawable.baseline_warehouse_24) , contentDescription =null ) }, "stores"),
        Items({ Icon(painterResource(id = R.drawable.baseline_people_24) , contentDescription =null ) }, "users"),
        Items({ Icon(painterResource(id = R.drawable.baseline_shopping_cart_24) , contentDescription =null ) }, "products"),
        Items({ Icon(painterResource(id = R.drawable.baseline_arrow_back_ios_24) , contentDescription =null ) }, "logout"),
    )

    var selectedItem by remember { mutableStateOf(item[0]) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(drawerContent = {
        ModalDrawerSheet(modifier = Modifier
            .fillMaxWidth(0.75f)
            .padding(top = 90.dp)) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                item.forEach {
                    NavigationDrawerItem(label = { Text(text = it.text)},
                        selected = it==selectedItem,
                        onClick = {
                            selectedItem=it
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = it.icon)
                }

            }

        }
    }) {

    }


    

}

data class Items(
    val icon: @Composable () -> Unit,
    val text: String
)




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Drawer()

}