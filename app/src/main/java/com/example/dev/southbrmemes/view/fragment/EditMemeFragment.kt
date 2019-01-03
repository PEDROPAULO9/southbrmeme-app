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
import android.widget.ImageView
import com.example.dev.southbrmemes.model.utils.*
import com.example.dev.southbrmemes.view.message.Message
import com.example.dev.southbrmemes.view.service.delete.MemeDeleteDomain
import com.example.dev.southbrmemes.view.service.update.MemeUpdateDomain
import com.example.dev.southbrmemes.databinding.FragmentEditMemeBinding
import com.example.dev.southbrmemes.viewmodel.MemeViewModel
import kotlinx.android.synthetic.main.fragment_edit_meme.view.*
import java.io.File


/**
 * A simple [Fragment] subclass.
 */
class EditMemeFragment : Fragment() {

    val meme = MemeViewModel()
    lateinit var binding: FragmentEditMemeBinding

    private val PERMISSAO_REQUEST = 2
    private val GALLERY = 1
    private val CAMERA = 3
    private val PHOTO_CAMERA = 4

    private var archive: File? = null

    private var imagOfPost: ImageView? = null

    private var acessPhoto: AccessGalleryOfPhoto? = null
    private var acessSavePhotoGallery: SavePhotoGallery? = null
    private var acessPhotoCamera: AccessPhoto? = null

    companion object {
        fun getInstance(): EditMemeFragment {
            return EditMemeFragment();
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentEditMemeBinding.inflate(layoutInflater)
        binding.meme = meme
        binding.memeUpdateDomain = MemeUpdateDomain(activity = activity!!, file = archive)
        binding.memeDeleteDomain = MemeDeleteDomain(activity!!)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setTitle("Editar Meme")

        if (arguments != null) {
            val args = arguments

            if (args != null) {
                meme.id = args.getInt("id")
                meme.commit = args.getString("commit")
                meme.url = args.getString("url")
            }
        }

        acessSavePhotoGallery = SavePhotoGallery()
        acessPhotoCamera = AccessPhoto()
        acessPhoto = AccessGalleryOfPhoto()

        acessPhoto?.permissionAccessGallery(activity, PERMISSAO_REQUEST)
        acessSavePhotoGallery?.permissionAccess(activity, PERMISSAO_REQUEST)

        view.imgGalleryEditMeme.setOnClickListener { v ->
            if (acessPhoto!!.callAccessGallery(activity)) {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, GALLERY)
            } else {
                Message.messageReturn("", activity)
            }
        }

        view.imgCameraEditMeme.setOnClickListener { v ->
            if (acessPhoto!!.callAccessGallery(activity)) {
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (takePictureIntent.resolveActivity(activity?.packageManager) != null) {
                    try {

                        archive = acessSavePhotoGallery?.createFile()

                        if (archive != null && activity?.baseContext != null) {
                            val photoURI = FileProvider.getUriForFile(activity?.baseContext!!,
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

        val filePhotoGallery: File? = acessPhoto?.accessGallery(requestCode, resultCode, data, imagOfPost, activity)

        val filePhotoCamera: File? = acessPhotoCamera?.accessPhoto(requestCode, resultCode, data, CAMERA, imagOfPost, activity)

        if (filePhotoGallery != null) {
            archive = filePhotoGallery
        } else if (filePhotoCamera != null) {
            archive = filePhotoCamera
        }

        binding.memeUpdateDomain?.file = archive

        acessSavePhotoGallery?.recordPhoto(requestCode, resultCode, PHOTO_CAMERA, activity, imagOfPost, archive)
    }

}
