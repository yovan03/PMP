package com.example.pmp_dzad1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.button.MaterialButton
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private val enMk = HashMap<String, String>()
    private val mkEn = HashMap<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editWord = findViewById<TextInputEditText>(R.id.editWord)
        val btnSearchEN = findViewById<MaterialButton>(R.id.btnSearchEN)
        val btnSearchMK = findViewById<MaterialButton>(R.id.btnSearchMK)
        val btnClear = findViewById<MaterialButton>(R.id.btnClear)
        val textResult = findViewById<TextView>(R.id.textResult)

        loadDictionary()

        btnSearchEN.setOnClickListener {

            val word = editWord.text.toString().lowercase()

            if (enMk.containsKey(word)) {
                textResult.text = enMk[word]
            } else {
                textResult.text = "Word not found"
            }
        }

        btnSearchMK.setOnClickListener {

            val word = editWord.text.toString().lowercase()

            if (mkEn.containsKey(word)) {
                textResult.text = mkEn[word]
            } else {
                textResult.text = "Word not found"
            }
        }

        btnClear.setOnClickListener {

            editWord.setText("")
            textResult.text = ""
        }
    }

    private fun loadDictionary() {

        val reader = BufferedReader(
            InputStreamReader(assets.open("en_mk_recnik.txt"))
        )

        var line: String?

        while (reader.readLine().also { line = it } != null) {

            val parts = line!!.split(",")

            val en = parts[0].trim()
            val mk = parts[1].trim()

            enMk[en] = mk
            mkEn[mk] = en
        }

        reader.close()
    }
}