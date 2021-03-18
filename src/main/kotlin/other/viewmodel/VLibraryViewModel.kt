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
import com.v.base.BaseViewModel
import com.v.base.utils.toList
import com.v.common.net.RetrofitManager
import ${applicationPackage}.bean.${className}Bean

${desc}
class ${className}ViewModel : BaseViewModel() {

    var listBean = MutableLiveData<List<${className}Bean>>()

     fun getList(page: Int) {

        val map = mapOf(
            "pageNum" to page.toString()
        )

        request({
            RetrofitManager.instance.get("https://gank.io/api/v2/data/category/Girl/type/Girl/page/"+page+"/count/20")
        }, success = {
               listBean.value = it.toString().toList(${className}Bean::class.java)
        })

    }
    
    
}    
"""