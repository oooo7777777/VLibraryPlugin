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
        isModeView: Boolean,
        desc:String
) = if (isModeView)
{
    """
package ${packageName}

${desc}

import com.v.base.BaseFragment
import android.view.View
import androidx.lifecycle.Observer
import com.v.base.utils.ext.linear
import com.v.base.utils.ext.loadData
import ${packageName}.adapter.${className}FragmentAdapter
import ${applicationPackage}.databinding.Fragment${className}Binding
import ${applicationPackage}.model.${className}ViewModel


class ${className}Fragment : BaseFragment<Fragment${className}Binding,${className}ViewModel>(), View.OnClickListener {
   
  var page =1
  
  private val mAdapter by lazy {
        mViewBinding.recyclerView.linear(${className}FragmentAdapter()) as ${className}FragmentAdapter
    }

    override fun initData() {
        mViewBinding.v = this
        mViewBinding.refreshLayout.autoRefresh()
        mViewModel.getList(page)
    }

    override fun createObserver() {
        mViewModel.listBean.observe(this, Observer {
            mAdapter.loadData(mViewBinding.refreshLayout,
                it,
                page,
                onRefresh = {
                    page = 1
                    mViewModel.getList(page)
                },
                onLoadMore = {
                    page = it
                    mViewModel.getList(page)
                },
                onItemClick = { view: View, i: Int ->
                })
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

${desc}

import com.v.base.BaseFragment
import android.view.View
import com.v.base.BlankViewModel
import ${applicationPackage}.databinding.Fragment${className}Binding

class ${className}Fragment : BaseFragment<Fragment${className}Binding,BlankViewModel>(), View.OnClickListener {
   
 
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

