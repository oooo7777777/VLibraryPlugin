package other

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:11
 */
fun VLibraryViewModel(
        packageName:String,
        activityClass:String
)="""
package ${packageName}
import androidx.lifecycle.MutableLiveData
import com.v.base.BaseViewModel
import com.v.base.utils.ext.toList
import com.ww.appmodule.net.RetrofitManager
import ${packageName}.bean.${activityClass}Bean

class ${activityClass}ViewModel : BaseViewModel() {

    var listBean = MutableLiveData<List<${activityClass}Bean>>()

     fun getList(page: Int) {

        val map = mapOf(
            "pageNum" to page.toString()
        )

        request({
            RetrofitManager.instance.get("data/category/Girl/type/Girl/page/page/count/20",map)
        }, success = {
               listBean.value = it.toString().toList(${activityClass}Bean::class.java)
        })

    }
    
    
}    
"""