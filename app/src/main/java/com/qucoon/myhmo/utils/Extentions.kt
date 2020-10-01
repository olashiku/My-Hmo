package com.example.neptune.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.thetechnocafe.gurleensethi.liteutils.RecyclerAdapterUtil
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import java.io.Serializable


fun delayFor(millseconds:Long,action:() -> Unit ){
    Handler().postDelayed({
        action()
    }, millseconds)
}



fun <T:Any> RecyclerView.updateRecyclerNew(itemDecoration: GridSpacingItemDecoration, context: Context, listOfItems:List<T>, layout:Int, listOfLayout:List<Int>, binder: (Map<Int, View>, Int) -> Unit, onClickPosition:(Int) -> Unit): RecyclerView.Adapter<*>? {
    this.layoutManager = GridLayoutManager(context,3)
    this.addItemDecoration(itemDecoration)
    val reyclerAdaptor = RecyclerAdapterUtil<T>(context,listOfItems,layout)
    reyclerAdaptor.addViewsList(listOfLayout)
    reyclerAdaptor.addOnDataBindListener{itemView, item, position, innerViews ->
        binder(innerViews,position)
    }
    reyclerAdaptor.addOnClickListener { item, position -> onClickPosition(position) }
    this.adapter = reyclerAdaptor
    return adapter
}

fun formatDouble(string:String):Pair<Boolean,Any>{
    return string.toDoublee()
}



fun String.toDoublee():Pair<Boolean,Any>{
    return if(this.trim().toDoubleOrNull() == null){
        Pair(false,this)
    }else{
        Pair(true,this.trim().toDouble())
    }
}

fun ImageView.loadImage(fullImageUrl: String, defaultImage:Int, view: Fragment) {
    Glide.with(view)
        .load(fullImageUrl)
        .placeholder(defaultImage)
        .error(defaultImage)
        .into(this)
}

fun View.setMarginTop(marginTop: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(0, marginTop, 0, 0)
    this.layoutParams = menuLayoutParams
}

fun EditText.getString():String{
    return this.text.toString()
}

fun EditText.stripAmount():String{
    return this.text.toString().replace("[$,£N₦]".toRegex(), "")
}

fun String.normalCase():String{
    return this.toLowerCase().capitalize()
}

fun requestIdGenerator(): String {
    return (Math.floor(Math.random() * 9_000_000_000L).toLong() + 1_000_000_000L).toString()
}

fun View.hide(){
    this.visibility = GONE
}



fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.toLowerCase().capitalize() }
fun String.formatNumber():String{
    return try {
        "${Constants.NAIRA}${DecimalFormat("#,##0.00").format(java.lang.Double.parseDouble(this))}"
    } catch (ex:Exception){
        return "${Constants.NAIRA}0"
    }
}

fun String.stripAmount():String{
    return this.toString().replace("[$,£N₦]".toRegex(), "")
}

fun TextInputEditText.stripAmount():String{
    return this.text.toString().replace("[$,£N₦]".toRegex(), "")
}

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column
        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount
            outRect.right = (column + 1) * spacing / spanCount
            if (position < spanCount) { // top edge
                outRect.top = spacing
            }
            outRect.bottom = spacing
        } else {
            outRect.left = column * spacing / spanCount
            outRect.right = spacing - (column + 1) * spacing / spanCount
            if (position >= spanCount) {
                outRect.top = spacing
            }
        }
    }
}



fun TextInputEditText.getString():String{
    return this.text.toString()
}

fun String.isValidEmail(): Boolean
        = this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun Int.isPhoneNumber():Boolean{
    return this.toString().length>10
}

fun String.isEmpty():Boolean{
    return this.toString().isNullOrBlank()
}


fun String.toDate(orginalFormat:String): Date {
    val originalFormat = SimpleDateFormat(orginalFormat)
    val date = originalFormat.parse(this)
    // val targetFormat = SimpleDateFormat("dd MMM yy")
    return  originalFormat.parse(this)
}


fun Float.formatNumber():String{
    return try {
        "₦${DecimalFormat("#,##0.00").format(this.toDouble())}"
    }catch (ex:java.lang.Exception){
        "₦0.0"
    }
}

fun TextInputEditText.validate(message: String, validator: (String) -> Boolean) {
    this.afterTextChanged {
        this.error = if (validator(it)) null else message
    }
    this.error = if (validator(this.text.toString())) null else message
}


fun Activity.makeStatusBarTransparent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            statusBarColor = Color.TRANSPARENT
        }
    }
}


fun TextInputEditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
    })
}

fun EditText.validate(message: String, validator: (String) -> Boolean) {
    this.afterTextChanged {
        this.error = if (validator(it)) null else message
    }
    this.error = if (validator(this.text.toString())) null else message
}


fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
    })
}


fun Fragment.withArguments(vararg arguments: Pair<String, Serializable>): Fragment {
    val bundle = Bundle()
    arguments.forEach { bundle.putSerializable(it.first, it.second) }
    this.arguments = bundle
    return this
}


fun DialogFragment.withArguments(vararg arguments: Pair<String, Serializable>): DialogFragment {
    val bundle = Bundle()
    arguments.forEach { bundle.putSerializable(it.first, it.second) }
    this.arguments = bundle
    return this
}


fun BottomSheetDialogFragment.withArguments(vararg arguments: Pair<String, Serializable>): BottomSheetDialogFragment {
    val bundle = Bundle()
    arguments.forEach { bundle.putSerializable(it.first, it.second) }
    this.arguments = bundle
    return this
}

fun View.gone(){
    this.visibility = View.GONE
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun View.show(){
    this.visibility = View.VISIBLE
}







/**
 * Retrieve property from intent
 */
fun <T : Any> FragmentActivity.argument(key: String) = lazy { intent.extras?.get(key) as T }

/**
 * Retrieve property with default value from intent
 */
fun <T : Any> FragmentActivity.argument(key: String, defaultValue: T? = null) = lazy {
    intent.extras?.get(key) as? T ?: defaultValue
}

/**
 * Retrieve property from intent
 */
fun <T : Any> Fragment.argument(key: String) = lazy { arguments?.get(key) as T }
fun <T : Any> DialogFragment.argument(key: String) = lazy { arguments?.get(key) as T }

/**
 * Retrieve property with default value from intent
 */
fun <T : Any> Fragment.argument(key: String, defaultValue: T? = null) = lazy {
    arguments?.get(key)  as? T ?: defaultValue
}

fun <T : Any> DialogFragment.argument(key: String, defaultValue: T? = null) = lazy {
    arguments?.get(key)  as? T ?: defaultValue
}


fun <T:Any> RecyclerView.updateRecycler(context: Context, listOfItems:List<T>, layout:Int, listOfLayout:List<Int>, binder: (Map<Int, View>, Int) -> Unit, onClickPosition:(Int) -> Unit): RecyclerView.Adapter<*>? {
    this.layoutManager = LinearLayoutManager(context)
    val reyclerAdaptor = RecyclerAdapterUtil<T>(context,listOfItems,layout)
    reyclerAdaptor.addViewsList(listOfLayout)
    reyclerAdaptor.addOnDataBindListener{itemView, item, position, innerViews ->
        binder(innerViews,position)
    }
    reyclerAdaptor.addOnClickListener { item, position -> onClickPosition(position) }
    this.adapter = reyclerAdaptor
    return adapter
}


fun Bitmap.resizeByWidth(width:Int):Bitmap{
    val ratio:Float = this.width.toFloat() / this.height.toFloat()
    val height:Int = Math.round(width / ratio)

    return Bitmap.createScaledBitmap(
        this,
        width,
        height,
        false
    )
}

fun Bitmap.resizeByHeight(height:Int):Bitmap{
    val ratio:Float = this.height.toFloat() / this.width.toFloat()
    val width:Int = Math.round(height / ratio)

    return Bitmap.createScaledBitmap(
        this,
        width,
        height,
        false
    )
}