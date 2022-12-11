package com.qucoon.myhmo.views.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.style.TypefaceSpan
import androidx.core.content.FileProvider
import com.baurine.permissionutil.PermissionUtil
import com.example.neptune.utils.Utils
import com.example.neptune.utils.gone
import com.example.neptune.utils.show
import com.github.euzee.permission.PermissionCallback
import com.qucoon.keystonemobile.utils.CheckPermissionUtil
import com.qucoon.myhmo.BuildConfig
import com.qucoon.myhmo.R
import com.qucoon.myhmo.database.PaperPrefs
import com.qucoon.myhmo.livedata.DataPasserLiveData
import com.qucoon.myhmo.utils.CommonUtilz
import com.qucoon.myhmo.views.fragment.insidefrgments.ApointmentFragment
import com.qucoon.myhmo.views.fragment.insidefrgments.SettingsFragment
import com.qucoon.myhmo.views.fragment.insidefrgments.dashoard.enrolment.UploadPictureFragment
import com.qucoon.royalexchange.ui.base.BaseActivity
import com.qucoon.royalexchange.ui.base.BaseFragment
import com.yalantis.ucrop.UCrop
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class MainActivity : BaseActivity() {

    lateinit var paperPrefs: PaperPrefs
    private val GALLARYCODE = 101
    private val CAMERACODE = 111
    private val dataPasserLiveData: DataPasserLiveData by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initBottomNavigation()
        showToolsbarAndSetTitle("Dashboard")
    }


    fun showToolsbarAndSetTitle(title: String) {

        val typefaceSpan = TypefaceSpan("poppinsregular.ttf")
        val str = SpannableString(title)
        str.setSpan(typefaceSpan, 0, str.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        supportActionBar!!.title = Html.fromHtml("<font color='#ffffff'>$title</font>")

    }

    fun setActoonBarTitle(title: String) {
        supportActionBar!!.title = Html.fromHtml("<font color='#ffffff'>$title</font>")
    }

    fun hideTablayout() {
        tabLayout.gone()
    }

    fun showTablayout() {
        tabLayout.show()
    }


    fun hideToolsBar() {
        supportActionBar!!.hide()
    }

    fun initView() {
        paperPrefs = PaperPrefs(application)
        supportActionBar!!.show()
        val fragment = listOf(UploadPictureFragment(), ApointmentFragment(), SettingsFragment())

        initFragNavController(
            this,
            fragment as List<BaseFragment>, "MAINACTIVITY", supportFragmentManager, R.id.content
        )

    }

    fun initBottomNavigation() {
        tabLayout.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_menu -> {
                    setActoonBarTitle("Dashboard")
                    switchFragment(HOME)
                }
                R.id.search_menu -> {
                    setActoonBarTitle("Your Health")
                    switchFragment(SEARCHMENU)
                }
                R.id.profile_menu -> {
                    setActoonBarTitle("Settings")
                    switchFragment(PROFILEMENU)
                }
            }
            true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == CAMERACODE) {
            val uri: Uri = data!!.data!!
            showCropperActivity(uri)
        } else if (resultCode == Activity.RESULT_OK && requestCode == GALLARYCODE) {
            val uri: Uri = data!!.data!!
            showCropperActivity(uri)

        } else if (resultCode == Activity.RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            val uri: Uri? = UCrop.getOutput(data!!)
            dataPasserLiveData.imageUri.postValue(uri)
        }
    }

    fun showCropperActivity(sourceUri: Uri) {
        val file = Utils.getImageFile()
        val destinationURI = Uri.fromFile(file)
        UCrop.of(sourceUri, destinationURI)
            .withAspectRatio(16F, 9F)
            .start(this);
    }


    fun openCamera() {
        val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val file = Utils.getImageFile()
        val uri: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            FileProvider.getUriForFile(
                applicationContext,
                BuildConfig.APPLICATION_ID + ".provider",
                file!!
            ) else Uri.fromFile(file)
        pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        startActivityForResult(pictureIntent, CAMERACODE)

    }

    fun openGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLARYCODE)
    }


    companion object {
        val HOME = 0
        val SEARCHMENU = 1
        val PROFILEMENU = 2
    }

}
