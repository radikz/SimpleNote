/*
 * SimpleNote
 * MainActivity.kt
 * Created by Rangga Dikarinata on 2020/11/4
 * email 	    : dikarinata@gmail.com
 */

/*
 * SimpleNote
 * MainActivity.kt
 * Created by Rangga Dikarinata on 2020/11/3
 * email 	    : dikarinata@gmail.com
 */

package id.radikz.simplenote

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }


}