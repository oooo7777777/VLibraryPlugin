package other.adapter

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:09
 */
import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import java.io.File


val VLibraryAdapterTemplate
    get() = template {
        revision = 1
        name = "VLibrary Adapter"
        description = "适用于VLibrary框架的Adapter"
        minApi = MIN_API
        minBuildApi = MIN_API

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)

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
            help = "是否约束资源文件命名"
        }


        val author = stringParameter {
            name = "Author"
            default = " "
            help = "开发者"
            constraints = listOf(Constraint.NONEMPTY)

        }

        val classDesc = stringParameter {
            name = "Description"
            default = " "
            help = "描述一下方法的作用"
            constraints = listOf(Constraint.NONEMPTY)

        }


        widgets(
                TextFieldWidget(className),
                CheckBoxWidget(isResourcePrefix),
                PackageNameWidget(packageName),
                TextFieldWidget(classDesc),
                TextFieldWidget(author)
        )

        thumb { File("template_login_activity.png") }

        recipe = { data: TemplateData ->
            VLibraryAdapterRecipe(
                    data as ModuleTemplateData,
                    className.value,
                    isResourcePrefix.value,
                    packageName.value,
                    author.value,
                    classDesc.value)
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


