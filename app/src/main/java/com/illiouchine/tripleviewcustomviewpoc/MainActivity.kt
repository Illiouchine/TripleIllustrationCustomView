package com.illiouchine.tripleviewcustomviewpoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.illiouchine.tripleviewcustomviewpoc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var itemNumber = 1
    private val listOfProduct = listOf(
        TripleCustomView.Illustration(R.drawable.illu_batman),
        TripleCustomView.Illustration(R.drawable.illu_spiderman),
        TripleCustomView.Illustration(R.drawable.illu_superman),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setupView(binding)
        setContentView(binding.root)
    }

    private fun setupView(binding: ActivityMainBinding) {
        binding.bpvTripleView.setIllustration(listOf(listOfProduct[0]))
        binding.bpvTripleView.setOnClickListener {
            binding.bpvTripleView.setIllustration(
                getRandomProduct()
            )
        }
    }

    private fun getRandomProduct(): List<TripleCustomView.Illustration> {
        return when(itemNumber){
            0 -> {
                itemNumber++
                emptyList()
            }
            1 -> {
                itemNumber++
                listOf(listOfProduct[0])
            }
            2 -> {
                itemNumber++
                listOf(listOfProduct[0], listOfProduct[1])
            }
            3 -> {
                itemNumber = 0
                listOf(listOfProduct[0], listOfProduct[1], listOfProduct[2])
            }
            else -> {
                itemNumber = 0
                listOf(listOfProduct[0])
            }
        }
    }
}