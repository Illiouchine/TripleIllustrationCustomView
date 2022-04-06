package com.illiouchine.tripleviewcustomviewpoc

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import com.illiouchine.tripleviewcustomviewpoc.databinding.ViewTripleCustomBinding

class TripleCustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var viewBinding : ViewTripleCustomBinding?

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        viewBinding = null
    }

    init {
        viewBinding = ViewTripleCustomBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setIllustration(illustrations: List<Illustration>) {
        when (illustrations.size) {
            0 -> {
                setNoIllustration()
            }
            1 -> {
                setOneIllustration(illustrations[0])
            }
            2 -> {
                setTwoIllustrations(illustrations[0], illustrations[1])
            }
            else -> {
                setThreeIllustrations(illustrations[0], illustrations[1], illustrations[2])
            }
        }
    }

    private fun setNoIllustration() {
        viewBinding?.let { binding ->
            binding.ivTripleViewBackground.isVisible = false
            binding.ivTripleViewOne.isVisible = false
            binding.ivTripleViewTwo.isVisible = false
            binding.ivTripleViewThree.isVisible = false
        }
    }

    private fun translateIllustrationOneToLeft(binding: ViewTripleCustomBinding){
        ConstraintSet().apply {
            clone(binding.clTripleViewContainer)
            setHorizontalBias(binding.ivTripleViewOne.id, 0.06f)
            setVerticalBias(binding.ivTripleViewOne.id, 0.72f)
            applyTo(binding.clTripleViewContainer)
        }
    }

    private fun translateIllustrationOneToMiddle(binding: ViewTripleCustomBinding){
        ConstraintSet().apply {
            clone(binding.clTripleViewContainer)
            setHorizontalBias(binding.ivTripleViewOne.id, 0.5f)
            setVerticalBias(binding.ivTripleViewOne.id, 0.5f)
            applyTo(binding.clTripleViewContainer)
        }
    }

    private fun setOneIllustration(illustration: Illustration) {
        viewBinding?.let { binding ->
            binding.ivTripleViewBackground.setImageResource(R.drawable.illu_background_1)
            binding.ivTripleViewBackground.isVisible = true

            binding.ivTripleViewOne.setImageResource(illustration.imgResId)
            binding.ivTripleViewOne.isVisible = true
            translateIllustrationOneToMiddle(binding)

            binding.ivTripleViewTwo.isVisible = false
            binding.ivTripleViewThree.isVisible = false
        }
    }

    private fun setTwoIllustrations(
        illustration1: Illustration,
        illustration2: Illustration
    ) {
        viewBinding?.let { binding ->
            binding.ivTripleViewBackground.setImageResource(R.drawable.illu_background_2)
            binding.ivTripleViewBackground.isVisible = true

            binding.ivTripleViewOne.setImageResource(illustration1.imgResId)
            binding.ivTripleViewOne.isVisible = true
            binding.ivTripleViewTwo.setImageResource(illustration2.imgResId)
            binding.ivTripleViewTwo.isVisible = true

            translateIllustrationOneToLeft(binding)

            binding.ivTripleViewThree.isVisible = false
        }
    }

    private fun setThreeIllustrations(
        illustration1: Illustration,
        illustration2: Illustration,
        illustration3: Illustration
    ) {
        viewBinding?.let { binding ->
            binding.ivTripleViewBackground.setImageResource(R.drawable.illu_background_3)
            binding.ivTripleViewBackground.isVisible = true

            binding.ivTripleViewOne.setImageResource(illustration1.imgResId)
            binding.ivTripleViewOne.isVisible = true
            binding.ivTripleViewTwo.setImageResource(illustration2.imgResId)
            binding.ivTripleViewTwo.isVisible = true
            binding.ivTripleViewThree.setImageResource(illustration3.imgResId)
            binding.ivTripleViewThree.isVisible = true

            translateIllustrationOneToLeft(binding)
        }
    }

    data class Illustration (val imgResId: Int)
}