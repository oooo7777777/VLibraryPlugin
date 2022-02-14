package other.adapter


import android.databinding.tool.ext.toCamelCase
import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import org.jetbrains.kotlin.util.capitalizeDecapitalize.toLowerCaseAsciiOnly
import other.utlis.getHeaderString
import java.io.File

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:09
 */
val VLibraryAdapterTemplate
    get() = template {
        name = "VLibrary Adapter"
        description = "适用于VLibrary框架的Adapter"


        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        val packageName = defaultPackageNameParameter

        val className = stringParameter {
            name = "Adapter Name"
            default = "Main"
            help = "只输入名字，不要包含Adapter"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val isResourcePrefix = booleanParameter {
            name = "Is ResourcePrefix"
            default = true
            help = "是否约束资源文件命名(自动识别,组件化开发的时候勾选)"
        }

        val resourcePrefixName = stringParameter {
            name = "ResourcePrefix Name"
            default = ""
            help = "约束资源文件命名(手动输入的优先级高)"
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
            TextFieldWidget(resourcePrefixName),
            TextFieldWidget(classDesc),
            TextFieldWidget(author),
            PackageNameWidget(packageName),
            LanguageWidget()
        )

        thumb { File("template_login_activity.png") }

        recipe = { data: TemplateData ->
            VLibraryAdapterRecipe(
                data as ModuleTemplateData,
                className.value,
                className.value.toLowerCaseAsciiOnly(),
                packageName.value,
                isResourcePrefix.value,
                getHeaderString(author.value, classDesc.value),
                resourcePrefixName.value
            )
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


