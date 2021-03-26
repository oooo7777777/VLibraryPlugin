package other.activity

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:10
 */

fun VLibraryAcitivityKt(
        applicationPackage: String,
        className: String,
        layoutName: String,
        packageName: String,
        isModeView: Boolean,
        resourcePrefixClass: String,
        resourcePrefixXml: String,
        desc:String
) = if (isModeView)
{
    """
package ${packageName}



import com.v.base.BaseActivity
import android.view.View
import androidx.lifecycle.Observer
import com.v.base.utils.divider
import com.v.base.utils.linear
import com.v.base.utils.loadData
import ${applicationPackage}.adapter.${className}ActivityAdapter
import ${applicationPackage}.model.${className}ViewModel
import ${applicationPackage}.databinding.${resourcePrefixClass}Activity${className}Binding
import ${applicationPackage}.R

${desc}
class ${className}Activity : BaseActivity<${resourcePrefixClass}Activity${className}Binding,${className}ViewModel>(), View.OnClickListener {
   
  private var page =1
  
  private val mAdapter by lazy {
  
        mViewBinding.recyclerView.divider{
                setColor(Color.parseColor("#ff0000"))
                setDivider(10)
            }
            .linear(${className}ActivityAdapter()) as ${className}ActivityAdapter
    }
    
  override fun toolBarTitle(title: String, titleColor: Int) {
        super.toolBarTitle(this.getString(R.string.${resourcePrefixXml}string_${layoutName}_title), titleColor)
    }
    
    override fun initData() {
        mViewBinding.v = this
        mViewBinding.refreshLayout.autoRefresh()
        mViewModel.getData(page)
    }

    override fun createObserver() {
        mViewModel.bean.observe(this, Observer {
            it.data?.run {
                mAdapter.loadData(mViewBinding.refreshLayout,
                    this,
                    page,
                    onRefresh = {
                        page = 1
                        mViewModel.getData(page)
                    },
                    onLoadMore = {
                        page = it
                        mViewModel.getData(page)
                    },
                    onItemClick = { view: View, i: Int ->
                    })
            }

        })
    }

    override fun onClick(v: View) {
        when (v.id) {
           
        }
    }
    
    } 
"""
} else
{
    """
package ${packageName}



import com.v.base.BaseActivity
import android.view.View
import com.v.base.BlankViewModel
import ${applicationPackage}.databinding.${resourcePrefixClass}Activity${className}Binding
import ${applicationPackage}.R

${desc}
class ${className}Activity : BaseActivity<${resourcePrefixClass}Activity${className}Binding,BlankViewModel>(), View.OnClickListener {
   
  override fun toolBarTitle(title: String, titleColor: Int) {
      super.toolBarTitle(this.getString(R.string.${resourcePrefixXml}string_${layoutName}_title), titleColor)
    }
    
    override fun initData() {
        mViewBinding.v = this
    }

    override fun createObserver() {

    }

    override fun onClick(v: View) {
        when (v.id) {
           
        }
    }
    
    } 
"""
}

