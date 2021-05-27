package other.fragment

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:10
 */

fun VLibraryFragmentKt(
        applicationPackage: String,
        className: String,
        layoutName: String,
        packageName: String,
        isViewMode: Boolean,
        resourcePrefixClass: String,
        desc:String
) = if (isViewMode)
{
    """
package ${packageName}



import com.v.base.BaseFragment
import android.view.View
import androidx.lifecycle.Observer
import android.graphics.Color
import com.v.base.utils.divider
import com.v.base.utils.linear
import com.v.base.utils.loadData
import ${applicationPackage}.adapter.${className}FragmentAdapter
import ${applicationPackage}.model.${className}ViewModel
import ${applicationPackage}.databinding.${resourcePrefixClass}Fragment${className}Binding

${desc}
class ${className}Fragment : BaseFragment<${resourcePrefixClass}Fragment${className}Binding,${className}ViewModel>(), View.OnClickListener {
   
  private var page =1
  
  private val mAdapter by lazy {
        mViewBinding.recyclerView.divider{
                setColor(Color.parseColor("#ff0000"))
                setDivider(10)
            }
            .linear(${className}FragmentAdapter()) as ${className}FragmentAdapter
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



import com.v.base.BaseFragment
import android.view.View
import com.v.base.BlankViewModel
import ${applicationPackage}.databinding.${resourcePrefixClass}Fragment${className}Binding

${desc}
class ${className}Fragment : BaseFragment<${resourcePrefixClass}Fragment${className}Binding,BlankViewModel>(), View.OnClickListener {
   
 
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

