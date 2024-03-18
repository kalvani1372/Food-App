package com.devamirali.foodapp.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.devamirali.foodapp.R
import com.devamirali.foodapp.data.adapter.CountryAdapter
import com.devamirali.foodapp.data.models.CountryModel
import com.devamirali.foodapp.databinding.FragmentProfileBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import org.json.JSONArray
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets


class ProfileFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var username: String
    private lateinit var from: String

    private val cameraRequest = 1888
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtBtnEdit.setOnClickListener(this)
        binding.txtBtnDone.setOnClickListener(this)
        binding.txtBtnCancel.setOnClickListener(this)
        binding.cardViewApisListWebsite.setOnClickListener(this)
        binding.cardViewChangePhoto.setOnClickListener(this)

        if (ContextCompat.checkSelfPermission(requireActivity(),
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.CAMERA), cameraRequest)


///////////////////////////////////////////////////////////
        val gson = Gson()
        val models: List<CountryModel>
        try {
            val fileName = "countries.json"
            @SuppressLint("NewApi", "LocalSuppress") val reader = JsonReader(
                InputStreamReader(
                    requireActivity().assets.open(fileName), StandardCharsets.UTF_8
                )
            )

            models = gson.fromJson<List<CountryModel>>(
                reader, object : TypeToken<List<CountryModel?>?>() {}.type
            )

            val s: String = models[0].countryName


            val jsonString = models

            // Parse JSON data
            val jsonArray = JSONArray(jsonString)
            val items = mutableListOf<String>()
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                items.add(jsonObject.getString("name"))
            }

            // Populate the spinner
            val adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, items)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.edtFrom.adapter = adapter

            // Handle spinner item selection
            binding.edtFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Toast.makeText(
                        requireActivity(),
                        "Selected: ${items[position]}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Do nothing
                }


            }

                Log.d("testApp", "onViewCreated: $s")

            } catch (e: IOException) {
                e.printStackTrace()
            }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.txt_btn_edit -> {
                binding.layProfile.visibility = View.GONE
                binding.txtBtnEdit.visibility = View.GONE
                binding.txtBtnDone.visibility = View.VISIBLE
                binding.txtBtnCancel.visibility = View.VISIBLE
                binding.layEditProfile.visibility = View.VISIBLE
            }

            R.id.txt_btn_Done -> {
                username = binding.edtUsername.text.toString()
//                from = binding.edtFrom.text.toString()
                binding.txtUsername.text = username
                binding.txtFrom.text = from

                showEditProfile()
            }

            R.id.txt_btn_Cancel -> {
                showEditProfile()
            }

            R.id.card_view_apis_list_website -> {
                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://www.themealdb.com/api.php"))
                startActivity(intent)
            }

            R.id.card_view_change_photo ->{
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, cameraRequest)
            }
        }
    }

    private fun showEditProfile() {
        binding.layProfile.visibility = View.VISIBLE
        binding.txtBtnEdit.visibility = View.VISIBLE
        binding.txtBtnDone.visibility = View.GONE
        binding.txtBtnCancel.visibility = View.GONE
        binding.layEditProfile.visibility = View.GONE
        binding.edtName.setText("")
        binding.edtFamily.setText("")
        binding.edtJob.setText("")
        binding.edtUsername.setText("")
//        binding.edtFrom.setText("")

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequest) {
            val photo: Bitmap = data?.extras?.get("data") as Bitmap
            binding.profileImage.setImageBitmap(photo)
        }
    }

}