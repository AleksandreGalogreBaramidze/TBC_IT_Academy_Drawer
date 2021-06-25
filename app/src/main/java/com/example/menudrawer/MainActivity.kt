package com.example.menudrawer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.menudrawer.adapter.Adapter
import com.example.menudrawer.databinding.ActivityMainBinding
import com.example.menudrawer.model.MenuItems
import com.example.menudrawer.viewmodel.DelayViewModel
import kotlinx.coroutines.InternalCoroutinesApi

class MainActivity : AppCompatActivity() {
    private val viewModel: DelayViewModel by viewModels()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter
    private var itemsList = mutableListOf<MenuItems>()

    companion object{
        const val GALLERY_FRAGMENT = 0
        const val HOME_FRAGMENT = 1
        const val SLIDESHOW_FRAGMENT = 2
    }

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    @InternalCoroutinesApi
    fun init(){
        viewModel.loopRequest()
        addMenu()
        setSupportActionBar(binding.appBarMain.toolbar)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_gallery
            ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        adapter.resetMenu()
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    private fun addMenu() {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        itemsList.add(MenuItems(R.drawable.ic_picture, "Gallery"))
        itemsList.add(MenuItems(R.drawable.ic_house, "Home"))
        itemsList.add(MenuItems(R.drawable.ic_slide_show, "SlideShow"))
        adapter = Adapter(itemsList)
        binding.recycler.layoutManager = LinearLayoutManager(this)
        adapter.onClickMenu = {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            when (it) {
                GALLERY_FRAGMENT -> navController.navigate(R.id.nav_gallery)
                HOME_FRAGMENT -> navController.navigate(R.id.nav_home)
                SLIDESHOW_FRAGMENT -> navController.navigate(R.id.nav_slideshow)
            }
        }
        binding.recycler.adapter = adapter
    }
}