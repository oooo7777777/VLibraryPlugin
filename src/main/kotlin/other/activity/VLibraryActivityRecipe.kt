package other.activity

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import other.adapter.VLibraryAdapter
import other.adapter.VLibraryAdapterItemXml
import other.utlis.getApplicationPackageFile
import other.utlis.getResourcePrefix
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
        isResourcePrefix: Boolean,
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
    var applicationPackage = projectData.applicationPackage

    //获取包名
    if (applicationPackage.isNullOrEmpty())
    {
        applicationPackage = escapeKotlinIdentifier(packageName)
    }

    //是否约束资源文件命名
    var resourcePrefixClass = ""
    if (isResourcePrefix)
    {
        resourcePrefixClass = getResourcePrefix(applicationPackage).toUpperCase()
    }

    var resourcePrefixXml = ""
    if (isResourcePrefix)
    {
        resourcePrefixXml = getResourcePrefix(applicationPackage).toLowerCase()+"_"
    }

    //获取包名根目录
    val  pkFile =  getApplicationPackageFile(srcOut,applicationPackage)


    // 保存Activity
    save(VLibraryAcitivityKt(applicationPackage, className, layoutName, packageName, isModeView, resourcePrefixClass,resourcePrefixXml,headerString), srcOut.resolve("${className}Activity.${ktOrJavaExt}"))

    // 保存xml
    save(VLibraryActivityXml(applicationPackage, packageName, className, isModeView,resourcePrefixXml, layoutName), resOut.resolve("layout/${resourcePrefixXml}${layoutName}.xml"))

    // 保存titleString
    mergeXml(VLibraryTitleString(layoutName, title,resourcePrefixXml), resOut.resolve("values/strings.xml"))


    if (isModeView)
    {
        // 保存viewModel
        save(VLibraryViewModel(applicationPackage, className, headerString), pkFile.resolve("model/${className}ViewModel.${ktOrJavaExt}"))
        // 保存bean
        save(VLibraryBean(applicationPackage, className, headerString), pkFile.resolve("bean/${className}Bean.${ktOrJavaExt}"))
        // 保存adapter
        save(VLibraryAdapter(applicationPackage, className, "Activity", "${resourcePrefixXml}item_${layoutName}",resourcePrefixClass,resourcePrefixXml,headerString), pkFile.resolve("adapter/${className}ActivityAdapter.${ktOrJavaExt}"))
        // 保存adapterItemXml
        save(VLibraryAdapterItemXml(applicationPackage, packageName, className, "Activity"), resOut.resolve("layout/${resourcePrefixXml}item_${layoutName}.xml"))

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