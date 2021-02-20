package other

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:11
 */

fun VLibraryAdapter(
        applicationPackage: String,
        packageName:String,
        activityClass:String,
        layoutName:String
)="""
package ${packageName}.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import ${applicationPackage}.R
import ${packageName}.bean.${activityClass}Bean
import com.v.demo.databinding.DmFragmentOneItemBinding

class ${activityClass}Adapter :
    BaseQuickAdapter<${activityClass}Bean, BaseDataBindingHolder<${activityClass}ItemBinding>>(R.layout.${layoutName}_item) {

    override fun convert(holder: BaseDataBindingHolder<DmFragmentOneItemBinding>, item: ${activityClass}Bean) {
        holder.dataBinding?.run {
            bean = item
            executePendingBindings()
        }


    }

}

"""