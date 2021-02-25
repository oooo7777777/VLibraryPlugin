package other.activity

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import other.viewmodel.VLibraryAdapter
import other.viewmodel.VLibraryAdapterItemXml
import other.viewmodel.VLibraryBean
import other.viewmodel.VLibraryViewModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:10
 */



fun RecipeExecutor.VLibraryActivityRecipe(
        moduleData: ModuleTemplateData,
        className: String,
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
    val applicationPackage = escapeKotlinIdentifier(packageName)


    val activity = VLibraryAcitivityKt(applicationPackage, className, layoutName, packageName, isModeView, headerString)


    // 保存Activity
    save(activity, srcOut.resolve("${className}Activity.${ktOrJavaExt}"))

    // 保存xml
    save(VLibraryActivityXml(applicationPackage, packageName, className, isModeView), resOut.resolve("layout/${layoutName}.xml"))

    // 保存titleString
    mergeXml(VLibraryTitleString(layoutName, title), resOut.resolve("values/strings.xml"))


    if (isModeView)
    {
        // 保存viewModel
        save(VLibraryViewModel(packageName, className, headerString), srcOut.resolve("model/${className}ViewModel.${ktOrJavaExt}"))
        // 保存bean
        save(VLibraryBean(packageName, className, headerString), srcOut.resolve("bean/${className}Bean.${ktOrJavaExt}"))
        // 保存adapter
        save(VLibraryAdapter(applicationPackage, packageName, className,"Activity", layoutName, headerString), srcOut.resolve("adapter/${className}ActivityAdapter.${ktOrJavaExt}"))
        // 保存adapterItemXml
        save(VLibraryAdapterItemXml(applicationPackage, packageName, className,"Activity"), resOut.resolve("layout/${layoutName}_item.xml"))

    }

    //添加activity到Manifest
    generateManifest(
            moduleData = moduleData,
            activityClass = "${className}Activity",
            activityTitle = className,
            packageName = packageName,
            isLauncher = false,
            hasNoActionBar = false,
            generateActivityTitle = false
    )

    open(srcOut.resolve("${className}Activity.${ktOrJavaExt}"))

}