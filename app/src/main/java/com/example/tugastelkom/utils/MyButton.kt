package com.example.tugastelkom.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.tugastelkom.R
import com.example.tugastelkom.databinding.MyButtonViewBinding

@SuppressLint("CustomViewStyleable", "Recycle")
class MyButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: MyButtonViewBinding = MyButtonViewBinding.inflate(
        LayoutInflater.from(context), this, true)

    private var textTitle: String
        get() = binding.btnCustom.text.toString()
        set(value) {
            binding.btnCustom.text = value
        }


    fun setActions(onClick: () -> Unit) {
        binding.apply {
            btnCustom.setOnClickListener { onClick.invoke() }
        }
    }

    init {
        binding.root
        attrs?.let {
            val attr = context.obtainStyledAttributes(it, R.styleable.CustomMyButton, 0, 0)
            try {
                textTitle = attr.getString(R.styleable.CustomMyButton_textTitle).orEmpty()
            } finally {
                attr.recycle()
            }
        }
    }

}
