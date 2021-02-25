package other.activity

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:10
 */

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifestStrings
import java.text.SimpleDateFormat
import java.util.*


fun RecipeExecutor.VLibraryActivityRecipe(
        moduleData: ModuleTemplateData,
        activityClass: String,
        layoutName: String,
        packageName: String,
        isModeView: Boolean,
        title: String,
        author: String,
        classDesc: String
)
{

    var date = Date(System.currentTimeMillis())
    var format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    val headerString = "" +
            "/**\n" +
            " * @Author : ${author}\n" +
            " * desc    : ${classDesc} \n" +
            " * time    : ${format.format(date)} \n" +
            " */"


    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension

    val activity = VLibraryAcitivityKt(escapeKotlinIdentifier(packageName), activityClass, layoutName, packageName, isModeView, headerString)

    // 保存Activity
    save(activity, srcOut.resolve("${activityClass}Activity.${ktOrJavaExt}"))

    // 保存xml
    save(VLibraryActivityXml(escapeKotlinIdentifier(packageName),packageName, activityClass, isModeView), resOut.resolve("layout/${layoutName}.xml"))

    // 保存titleString
    mergeXml(VLibraryTitleString(layoutName, title), resOut.resolve("values/strings.xml"))

    if (isModeView)
    {
        // 保存viewModel
        save(VLibraryViewModel(packageName, activityClass, headerString), srcOut.resolve("model/${activityClass}ViewModel.${ktOrJavaExt}"))
        // 保存bean
        save(VLibraryBean(packageName, activityClass, headerString), srcOut.resolve("bean/${activityClass}Bean.${ktOrJavaExt}"))
        // 保存adapter
        save(VLibraryAdapter(escapeKotlinIdentifier(packageName), packageName, activityClass, layoutName, headerString), srcOut.resolve("adapter/${activityClass}Adapter.${ktOrJavaExt}"))
        // 保存adapterItemXml
        save(VLibraryAdapterItemXml(escapeKotlinIdentifier(packageName),packageName, activityClass), resOut.resolve("layout/${layoutName}_item.xml"))

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

    open(srcOut.resolve("${activityClass}Activity.${ktOrJavaExt}"))

}