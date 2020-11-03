/*
 * SimpleNote
 * MainActivity.kt
 * Created by Rangga Dikarinata on 2020/11/3
 * email 	    : dikarinata@gmail.com
 * Copyright © 2020 Rangga Dikarinata. All rights reserved.
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