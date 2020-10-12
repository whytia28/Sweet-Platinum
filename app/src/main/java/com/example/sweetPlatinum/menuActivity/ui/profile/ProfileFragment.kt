package com.example.sweetPlatinum.menuActivity.ui.profile

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.sweetPlatinum.R
import com.example.sweetPlatinum.menuActivity.MenuActivity
import com.example.sweetPlatinum.sharedPreference.MySharedPreferences
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment() {


    private var bitmapResult: Bitmap? = null
    private lateinit var token: String
    private val profileViewModel: ProfileViewModel by inject()

    companion object {
        const val REQUEST_CODE = 201
        const val CAMERA_REQUEST = 101
        const val GALLERY_REQUEST = 102
        val arrayListPermission = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = view.context as MenuActivity
        context.supportActionBar?.title = getString(R.string.title_profile)
        token = context.let { MySharedPreferences(it).getData("token") }.toString()

        btn_edit.setOnClickListener {
            showEditUi()
        }

        iv_set_profile.setOnClickListener {
            if (checkPermission()) {
                val pictureDialog = AlertDialog.Builder(context)
                pictureDialog.setTitle(R.string.choose_from)
                val option =
                    arrayOf(getString(R.string.from_gallery), getString(R.string.take_picture))
                pictureDialog.setItems(option) { _, which ->
                    when (which) {
                        0 -> {
                            chooseFromGallery()
                        }
                        1 -> {
                            takePictureCamera()
                        }
                    }
                }
                pictureDialog.show()
            } else {
                requestPermission()
            }
        }

        btn_save.setOnClickListener {
            showProgressBar()
            bitmapResult?.let { it1 ->
                profileViewModel.updateUser(
                    token,
                    it1,
                    et_username.text.toString(),
                    et_email.text.toString()
                )

                profileViewModel.dataProfileUpdate.observe(viewLifecycleOwner, {
                    onUpdateSuccess()
                    showProfile(it.data.username, it.data.email, it.data.photo)
                    hiddenProgressBar()
                })

                profileViewModel.dataProfileError.observe(viewLifecycleOwner, {
                    onUpdateFailed(it.getString("errors"))
                    hiddenProgressBar()
                })
            }
        }

        btn_cancel.setOnClickListener {
            showSetupUi()
            getProfileUser()
        }

        getProfileUser()
    }

    private fun getProfileUser() {
        showProgressBar()
        profileViewModel.getProfileUser(token)
        profileViewModel.dataProfile.observe(viewLifecycleOwner, {
            if (it.data.photo != null) {
                showProfile(it.data.username, it.data.email, it.data.photo!!)
            } else {
                showProfile(it.data.username, it.data.email, "")
            }
            hiddenProgressBar()
        })
        profileViewModel.dataProfileError.observe(viewLifecycleOwner, {
            showProfileFailed(it.getString("errors"))
            hiddenProgressBar()
        })
    }

    private fun checkPermission(): Boolean {
        return ((context?.let {
            checkSelfPermission(
                it,
                Manifest.permission.CAMERA
            )
        } == PackageManager.PERMISSION_GRANTED) &&
                (context?.let {
                    ContextCompat.checkSelfPermission(
                        it,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                } == PackageManager.PERMISSION_GRANTED) &&
                (context?.let {
                    ContextCompat.checkSelfPermission(
                        it,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                } == PackageManager.PERMISSION_GRANTED))
    }

    private fun requestPermission() {
        requestPermissions(arrayListPermission, REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_REQUEST) {
            if (data != null) {
                val contentUri = data.data
                val bitmap =
                    MediaStore.Images.Media.getBitmap(activity?.contentResolver, contentUri)
                context?.let { Glide.with(it).load(bitmap).circleCrop().into(iv_profile) }

                bitmapResult = bitmap
            }
        } else if (requestCode == CAMERA_REQUEST) {
            val thumbnail = data?.extras?.get("data") as Bitmap
            context?.let { Glide.with(it).load(thumbnail).circleCrop().into(iv_profile) }

            bitmapResult = thumbnail
        }
    }

    private fun chooseFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY_REQUEST)
    }

    private fun takePictureCamera() {
        val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intentCamera, CAMERA_REQUEST)
    }

    private fun showEditUi() {
        iv_set_profile.visibility = View.VISIBLE
        btn_save.visibility = View.VISIBLE
        et_email.isEnabled = true
        et_username.isEnabled = true
        btn_cancel.visibility = View.VISIBLE
        btn_edit.visibility = View.GONE
    }

    private fun showSetupUi() {
        iv_set_profile.visibility = View.GONE
        btn_save.visibility = View.GONE
        et_email.isEnabled = false
        et_username.isEnabled = false
        btn_cancel.visibility = View.GONE
        btn_edit.visibility = View.VISIBLE

    }

    private fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hiddenProgressBar() {
        progress_bar.visibility = View.GONE
    }

    private fun onUpdateSuccess() {
        Toast.makeText(context, getString(R.string.update_success), Toast.LENGTH_SHORT).show()
    }

    private fun showProfile(username: String, email: String, photo: String) {
        et_username.setText(username)
        et_email.setText(email)
        activity?.let {
            Glide.with(it).load(photo).circleCrop().into(iv_profile)
        }
    }

    private fun showProfileFailed(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun onUpdateFailed(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

}