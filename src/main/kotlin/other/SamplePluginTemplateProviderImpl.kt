package other

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import other.activity.VLibraryActivityTemplate
import other.adapter.VLibraryAdapterTemplate
import other.dialog.VLibraryDialogTemplate
import other.fragment.VLibraryFragmentTemplate
import other.net.VLibraryNetTemplate
import other.viewmodel.VLibraryViewModelTemplate

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:08
 */
class SamplePluginTemplateProviderImpl : WizardTemplateProvider()
{

    override fun getTemplates(): List<Template> = listOf(
            VLibraryActivityTemplate,
            VLibraryFragmentTemplate,
            VLibraryDialogTemplate,
            VLibraryViewModelTemplate,
            VLibraryAdapterTemplate,
            VLibraryNetTemplate
    )
}