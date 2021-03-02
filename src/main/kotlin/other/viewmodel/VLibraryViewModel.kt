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

${desc}
 
import androidx.lifecycle.MutableLiveData
import com.v.base.BaseViewModel
import com.v.base.utils.ext.toList
import com.v.base.net.RetrofitManager
import ${applicationPackage}.bean.${className}Bean

class ${className}ViewModel : BaseViewModel() {

    var listBean = MutableLiveData<List<${className}Bean>>()

     fun getList(page: Int) {

        val map = mapOf(
            "pageNum" to page.toString()
        )

        request({
            RetrofitManager.instance.get("data/category/Girl/type/Girl/page/page/count/20",map)
        }, success = {
               listBean.value = it.toString().toList(${className}Bean::class.java)
        })

    }
    
    
}    
"""