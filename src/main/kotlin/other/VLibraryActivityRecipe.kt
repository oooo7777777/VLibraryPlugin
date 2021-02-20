package other

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:10
 */

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest


fun RecipeExecutor.VLibraryActivityRecipe(
        moduleData: ModuleTemplateData,
        activityClass: String,
        layoutName: String,
        packageName: String,
        isModeView: Boolean,
)
{
    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension

    val activity = VLibraryAcitivityKt(escapeKotlinIdentifier(packageName), activityClass, layoutName, packageName,isModeView)

    // 保存Activity
    save(activity, srcOut.resolve("${activityClass}Activity.${ktOrJavaExt}"))

    // 保存xml
    save(VLibraryActivityXml(packageName, activityClass,isModeView), resOut.resolve("layout/${layoutName}.xml"))

    if (isModeView)
    {
        // 保存viewModel
        save(VLibraryViewModel(packageName, activityClass), srcOut.resolve("model/${activityClass}ViewModel.${ktOrJavaExt}"))
        // 保存bean
        save(VLibraryBean(packageName, activityClass), srcOut.resolve("bean/${activityClass}Bean.${ktOrJavaExt}"))
        // 保存adapter
        save(VLibraryAdapter(escapeKotlinIdentifier(packageName) ,packageName, activityClass,layoutName), srcOut.resolve("adapter/${activityClass}Adapter.${ktOrJavaExt}"))
        // 保存adapterItemXml
        save(VLibraryAdapterItemXml(packageName, activityClass), resOut.resolve("layout/${layoutName}_item.xml"))

    }

    //添加activity到Manifest
    generateManifest(
            moduleData = moduleData,
            activityClass = "${activityClass}Activity",
            activityTitle = activityClass,
            packageName = packageName,
            isLauncher = false,
            hasNoActionBar = false,
            generateActivityTitle = false
    )
}