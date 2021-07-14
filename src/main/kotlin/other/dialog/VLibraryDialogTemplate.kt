package other.dialog


import android.databinding.tool.ext.toCamelCase
import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.AssetNameConverter.Type
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import org.jetbrains.kotlin.util.capitalizeDecapitalize.toLowerCaseAsciiOnly
import other.utlis.getHeaderString
import java.io.File

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:09
 */
val VLibraryDialogTemplate
    get() = template {
        revision = 1
        name = "VLibrary Dialog"
        description = "适用于VLibrary框架的Dialog"
        minApi = MIN_API
        minBuildApi = MIN_API

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)

        val packageName = defaultPackageNameParameter

        val className = stringParameter {
            name = "Dialog Name"
            default = "Main"
            help = "只输入名字，不要包含Dialog"
            constraints = listOf(Constraint.NONEMPTY)
        }
        val isResourcePrefix = booleanParameter {
            name = "Is ResourcePrefix"
            default = true
            help = "是否约束资源文件命名(组件化开发的时候勾选)"
        }
        val layoutName = stringParameter {
            name = "Layout Name"
            default = "dialog_main"
            help = "请输入布局的名字"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { "${dialogToLayout(className.value.toCamelCase())}" }

        }

        val author = stringParameter {
            name = "Author"
            help = "开发者"
            default = ""
            constraints = listOf(Constraint.NONEMPTY)

        }

        val classDesc = stringParameter {
            name = "Description"
            help = "描述一下方法的作用"
            default = ""
            constraints = listOf(Constraint.NONEMPTY)

        }

        widgets(
                TextFieldWidget(className),
                CheckBoxWidget(isResourcePrefix),
                TextFieldWidget(classDesc),
                TextFieldWidget(author),
                PackageNameWidget(packageName)
        )

        thumb { File("template_login_activity.png") }

        recipe = { data: TemplateData ->
            VLibraryDialogRecipe(
                    data as ModuleTemplateData,
                    className.value,
                    "${dialogToLayout(className.value.toCamelCase())}",
                    packageName.value,
                    isResourcePrefix.value,
                    getHeaderString(author.value, classDesc.value))
        }
    }

val defaultPackageNameParameter
    get() = stringParameter {
        name = "Package name"
        visible = { !isNewModule }
        default = "com.v.app"
        constraints = listOf(Constraint.PACKAGE)
        suggest = { packageName }
    }

fun dialogToLayout(dialogName: String, layoutName: String? = null): String =
        if (dialogName.isNotEmpty())
            AssetNameConverter(Type.FRAGMENT, dialogName)
                    .overrideLayoutPrefix(layoutName ?: "dialog")
                    .getValue(Type.LAYOUT)
        else
            ""



