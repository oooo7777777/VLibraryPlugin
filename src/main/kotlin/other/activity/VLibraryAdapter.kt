package other.activity

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:11
 */

fun VLibraryAdapter(
        applicationPackage: String,
        packageName:String,
        className:String,
        layoutName:String,
        desc:String
)="""
package ${packageName}.adapter

${desc}

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import ${applicationPackage}.R
import ${packageName}.bean.${className}Bean
import ${applicationPackage}.databinding.Activity${className}ItemBinding

class ${className}Adapter :
    BaseQuickAdapter<${className}Bean, BaseDataBindingHolder<Activity${className}ItemBinding>>(R.layout.${layoutName}_item) {

    override fun convert(holder: BaseDataBindingHolder<Activity${className}ItemBinding>, item: ${className}Bean) {
        holder.dataBinding?.run {
            bean = item
            executePendingBindings()
        }


    }

}

"""