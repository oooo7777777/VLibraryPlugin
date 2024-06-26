package other.net

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:09
 */
import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import other.utlis.defaultPackageNameParameter
import other.utlis.getHeaderString
import java.io.File


val VLibraryNetTemplate
    get() = template {
        name = "VLibrary Net"
        description = "适用于VLibrary框架的自定义网络请求"


        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule)

        val packageName = defaultPackageNameParameter

        val author = stringParameter {
            name = "Author"
            help = "开发者"
            default = ""
            constraints = listOf(Constraint.NONEMPTY)
        }

        val classDesc = stringParameter {
            name = "Description"
            default = "自定义网络请求"
            help = "描述一下方法的作用"
            constraints = listOf(Constraint.NONEMPTY)

        }


        widgets(
            TextFieldWidget(classDesc),
            TextFieldWidget(author),
            PackageNameWidget(packageName),
            LanguageWidget()
        )

        thumb { File("template_blank_activity.png") }

        recipe = { data: TemplateData ->
            VLibraryNetRecipe(
                data as ModuleTemplateData,
                packageName.value,
                getHeaderString(author.value, classDesc.value))
        }
    }

