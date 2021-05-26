package other.adapter

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:11
 */

fun VLibraryAdapter(
        applicationPackage: String,
        className:String,
        classType:String,
        layoutName:String,
        resourcePrefixClass:String,
        resourcePrefixXml:String,
        desc:String
)="""
package ${applicationPackage}.adapter



import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import ${applicationPackage}.R
import ${applicationPackage}.bean.${className}Data
import ${applicationPackage}.databinding.${resourcePrefixClass}Item${classType}${className}Binding

${desc}
class ${className}${classType}Adapter :
    BaseQuickAdapter<${className}Data, BaseDataBindingHolder<${resourcePrefixClass}Item${classType}${className}Binding>>(R.layout.${resourcePrefixXml}${layoutName}) {

    override fun convert(holder: BaseDataBindingHolder<${resourcePrefixClass}Item${classType}${className}Binding>, item: ${className}Data) {
        holder.dataBinding?.run {
            bean = item
            executePendingBindings()
        }


    }

}

"""