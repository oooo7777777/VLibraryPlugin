package other.viewmodel

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:11
 */
fun VLibraryViewModel(
        applicationPackage:String,
        className:String,
        desc:String
)="""
package ${applicationPackage}.model

import androidx.lifecycle.MutableLiveData
import com.v.base.BaseApplication.Companion.apiBase
import com.v.base.BaseViewModel
import ${applicationPackage}.bean.${className}Bean

${desc}
class ${className}ViewModel : BaseViewModel() {

    var bean = MutableLiveData<${className}Bean>()

     fun getData(page: Int) {

        val map = mapOf(
            "pageNum" to page.toString()
        )

        request({
            apiBase.get("https://gank.io/api/v2/data/category/Girl/type/Girl/page/"+page+"/count/20")
        },
            bean
        )

    }
    
    
}    
"""