package com.devamirali.foodapp.ui.fragment.profile

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devamirali.foodapp.R
import com.devamirali.foodapp.data.adapter.CountryAdapter
import com.devamirali.foodapp.data.models.CountryModel
import com.devamirali.foodapp.databinding.FragmentProfileBinding
import com.devamirali.foodapp.databinding.RowShowDialogCountryBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import java.io.InputStreamReader


class ProfileFragment : Fragment(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var bindingCountry : RowShowDialogCountryBinding
    private lateinit var username: String
    private lateinit var from: String

    private lateinit var dialog : AlertDialog
    private lateinit var inflater : LayoutInflater

    private val cameraRequest = 1888
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("ObsoleteSdkInt")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtBtnEdit.setOnClickListener(this)
        binding.txtBtnDone.setOnClickListener(this)
        binding.txtBtnCancel.setOnClickListener(this)
        binding.cardViewApisListWebsite.setOnClickListener(this)
        binding.cardViewChangePhoto.setOnClickListener(this)

        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_DENIED
        )
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA), cameraRequest
            )


///////////////////////////////////////////////////////////

//        var list_of_items = arrayOf("Afghanistan", "Åland Islands", "Albania", "Algeria", "American Samoa", "AndorrA","Angola","Anguilla","Antarctica","Antigua and Barbuda")
//
//        binding.edtFrom.onItemSelectedListener = this
//        val aa =
//            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, list_of_items)
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.edtFrom.adapter = aa
        showDialogCountry()


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

            R.id.card_view_change_photo -> {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, cameraRequest)
            }
        }
    }

    private fun showDialogCountry(){

        var list_of_items = arrayOf("Afghanistan", "Åland Islands", "Albania", "Algeria", "American Samoa", "AndorrA","Angola","Anguilla","Antarctica","Antigua and Barbuda")

        val gson = Gson()
        val reader =
            JsonReader(InputStreamReader(requireActivity().assets.open("countries.json"), "UTF-8"))
        val country = gson.fromJson<List<CountryModel>>(reader, object : TypeToken<List<CountryModel>>() {}.type)

        dialog = AlertDialog.Builder(requireActivity()).create()
        var inflater = LayoutInflater.from(requireActivity()).inflate(R.layout.row_show_dialog_country,null)
//        binding = RowShowDialogCountryBinding.inflate(LayoutInflater.from())


        val txt = inflater.findViewById<RecyclerView>(R.id.rec_show_country)

        txt.adapter = CountryAdapter(country)
        txt.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)

        dialog.setView(view)
        dialog.show()


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

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

}