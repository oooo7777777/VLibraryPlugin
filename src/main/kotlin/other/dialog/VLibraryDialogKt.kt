package other.dialog

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:10
 */

fun VLibraryDialogKt(
        applicationPackage: String,
        className: String,
        layoutName: String,
        packageName: String,
        resourcePrefixClass: String,
        desc: String
) = """
package ${applicationPackage}.dialog



import android.view.View
import com.v.base.BlankViewModel
import com.v.base.dialog.BaseDialogFragment
import ${applicationPackage}.databinding.${resourcePrefixClass}Dialog${className}Binding

${desc}
class ${className}Dialog : BaseDialogFragment<${resourcePrefixClass}Dialog${className}Binding,BlankViewModel>(), View.OnClickListener {
   
   private var listener: ${className}Listener? = null

    fun set${className}Listener(listener: ${className}Listener): ${className}Dialog {
        this.listener = listener
        return this
    }

    override fun initData() {
        mViewBinding.v = this
        
        
//            ${className}Dialog()
//                .set${className}Listener(object :${className}Dialog.${className}Listener
//                {
//                    override fun onLeftClick() {
//                    }
//
//                    override fun onRightClick() {
//                    }
//
//                }).show(mContext)
    }

    override fun createObserver() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            mViewBinding.tvLeft.id -> {
                dismiss()
                listener?.onLeftClick()
            }
            mViewBinding.tvRight.id -> {
                dismiss()
                listener?.onRightClick()
            }
        }
    }


    interface ${className}Listener {
        fun onLeftClick()

        fun onRightClick()

    }
}
"""


