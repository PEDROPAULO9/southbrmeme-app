package com.example.dev.southbrmemes.view.fragment


import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dev.southbrmemes.model.utils.*
import com.example.dev.southbrmemes.view.message.Message
import com.example.dev.southbrmemes.view.service.insert.MemeInsertDomain
import com.example.dev.southbrmemes.databinding.FragmentRegisterMemeBinding
import com.example.dev.southbrmemes.viewmodel.MemeViewModel
import kotlinx.android.synthetic.main.fragment_register_meme.*
import kotlinx.android.synthetic.main.fragment_register_meme.view.*
import java.io.File


/**
 * A simple [Fragment] subclass.
 */
class RegisterMemeFragment : Fragment() {

    companion object {
        fun getInstance(): RegisterMemeFragment {
            return RegisterMemeFragment();
        }
    }

    val meme = MemeViewModel()
    lateinit var binding: FragmentRegisterMemeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentRegisterMemeBinding.inflate(layoutInflater)
        binding.meme = meme
        binding.memeInsertDomain = MemeInsertDomain(activity = activity!!, file = archive)
        return binding.root
    }

    private val PERMISSAO_REQUEST = 2
    private val GALLERY = 1
    private val CAMERA = 3
    private val PHOTO_CAMERA = 4

    private var archive: File? = null

    private var acessPhoto: AccessGalleryOfPhoto? = null
    private var acessSavePhotoGallery: SavePhotoGallery? = null
    private var acessPhotoCamera: AccessPhoto? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setTitle("Novo Meme")
        (activity as AppCompatActivity).supportActionBar?.setIcon(null)

        acessSavePhotoGallery = SavePhotoGallery()
        acessPhotoCamera = AccessPhoto()
        acessPhoto = AccessGalleryOfPhoto()

        acessPhoto?.permissionAccessGallery(activity, PERMISSAO_REQUEST)
        acessSavePhotoGallery?.permissionAccess(activity, PERMISSAO_REQUEST)

        view.imgGalleryRegisterMeme.setOnClickListener { v ->
            if (acessPhoto!!.callAccessGallery(activity)) {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, GALLERY)
            } else {
                Message.messageReturn("", activity)
            }
        }

        view.imgCameraRegisterMeme.setOnClickListener { v ->
            if (acessPhoto!!.callAccessGallery(activity)) {
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (takePictureIntent.resolveActivity(activity?.packageManager) != null) {
                    try {

                        archive = acessSavePhotoGallery?.createFile()

                        if (archive != null && activity != null) {
                            val photoURI = FileProvider.getUriForFile(activity!!.baseContext,
                                    activity?.baseContext?.applicationContext?.packageName + ".provider", archive!!)
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                            startActivityForResult(takePictureIntent, PHOTO_CAMERA)
                        }
                    } catch (t: Throwable) {
                        t.stackTrace
                    }

                }
            } else {
                Message.messageReturn("", activity)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        acessPhoto?.permissionGallery(requestCode, grantResults, PERMISSAO_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val filePhotoGallery: File? = acessPhoto?.accessGallery(requestCode, resultCode, data, imgRegisterMeme, activity)

        val filePhotoCamera: File? = acessPhotoCamera?.accessPhoto(requestCode, resultCode, data, CAMERA, imgRegisterMeme, activity)

        if (filePhotoGallery != null) {
            archive = filePhotoGallery
        } else if (filePhotoCamera != null) {
            archive = filePhotoCamera
        }

        binding.memeInsertDomain?.file = archive

        acessSavePhotoGallery?.recordPhoto(requestCode, resultCode, PHOTO_CAMERA, activity, imgRegisterMeme, archive)
    }

}// Required empty public constructor
