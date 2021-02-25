package other.activity

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:11
 */

fun VLibraryAdapter(
        applicationPackage: String,
        packageName:String,
        activityClass:String,
        layoutName:String,
        desc:String
)="""
package ${packageName}.adapter

${desc}

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import ${applicationPackage}.R
import ${packageName}.bean.${activityClass}Bean
import ${applicationPackage}.databinding.Activity${activityClass}ItemBinding

class ${activityClass}Adapter :
    BaseQuickAdapter<${activityClass}Bean, BaseDataBindingHolder<Activity${activityClass}ItemBinding>>(R.layout.${layoutName}_item) {

    override fun convert(holder: BaseDataBindingHolder<Activity${activityClass}ItemBinding>, item: ${activityClass}Bean) {
        holder.dataBinding?.run {
            bean = item
            executePendingBindings()
        }


    }

}

"""