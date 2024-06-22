package com.chandravir.uploadimagesonfirebase

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.chandravir.uploadimagesonfirebase.Utils.USER_PROFILE_INFO
import com.chandravir.uploadimagesonfirebase.Utils.uploadImage
import com.chandravir.uploadimagesonfirebase.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var user: userData

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
        uri->
        uri?.let {
            uploadImage(uri, USER_PROFILE_INFO){
                if(it == null){

                }else{
                    user.image = it
                    binding.imgMain.setImageURI(uri)
                    binding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this, "Images Upload Successfully", Toast.LENGTH_SHORT).show()
                    binding.txtUploadImage.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        user = userData()
        
        binding.txtUploadImage.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            launcher.launch("image/*")
        }

        
    }
}