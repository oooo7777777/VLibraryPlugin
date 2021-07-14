package other.viewmodel


import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import other.utlis.getHeaderString
import java.io.File

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:09
 */
val VLibraryViewModelTemplate
    get() = template {
        revision = 1
        name = "VLibrary ViewModel"
        description = "适用于VLibrary框架的ViewModel"
        minApi = MIN_API
        minBuildApi = MIN_API

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
            name = "ViewModel Name"
            default = "Main"
            help = "只输入名字，不要包含ViewModel"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val isResourcePrefix = booleanParameter {
            name = "Is ResourcePrefix"
            default = true
            help = "是否约束资源文件命名(组件化开发的时候勾选)"
        }


        val author = stringParameter {
            name = "Author"
            default = ""
            help = "开发者"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val classDesc = stringParameter {
            name = "Description"
            default = ""
            help = "描述一下方法的作用"
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
            VLibraryViewModelRecipe(
                    data as ModuleTemplateData,
                    className.value,
                    packageName.value,
                    isResourcePrefix.value,
                    getHeaderString(author.value, classDesc.value)
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

