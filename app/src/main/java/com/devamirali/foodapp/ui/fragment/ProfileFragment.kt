package com.devamirali.foodapp.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devamirali.foodapp.R
import com.devamirali.foodapp.databinding.FragmentCategoryBinding
import com.devamirali.foodapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    private lateinit var username : String
    private lateinit var from : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        username = binding.edtUsername.text.toString()
        from = binding.edtFrom.text.toString()

        binding.txtBtnEdit.setOnClickListener {
            binding.layProfile.visibility = View.GONE
            binding.txtBtnEdit.visibility = View.GONE
            binding.txtBtnDone.visibility = View.VISIBLE
            binding.txtBtnCancel.visibility = View.VISIBLE
            binding.layEditProfile.visibility = View.VISIBLE
        }

        binding.txtBtnCancel.setOnClickListener {
            binding.layProfile.visibility = View.VISIBLE
            binding.txtBtnEdit.visibility = View.VISIBLE
            binding.txtBtnDone.visibility = View.GONE
            binding.txtBtnCancel.visibility = View.GONE
            binding.layEditProfile.visibility = View.GONE

        }

        binding.txtBtnDone.setOnClickListener {
            binding.layProfile.visibility = View.VISIBLE
            binding.txtBtnEdit.visibility = View.VISIBLE
            binding.txtBtnDone.visibility = View.GONE
            binding.txtBtnCancel.visibility = View.GONE
            binding.layEditProfile.visibility = View.GONE
        }

        binding.cardViewApisListWebsite.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.themealdb.com/api.php"))
            startActivity(intent)
        }
    }
}