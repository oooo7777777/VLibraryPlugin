package other.activity


import android.databinding.tool.ext.toCamelCase
import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import other.utlis.getHeaderString
import java.io.File

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:09
 */
val VLibraryActivityTemplate
    get() = template {
        revision = 1
        name = "VLibrary Activity"
        description = "适用于VLibrary框架的Activity"
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
            name = "Activity Name"
            default = "Main"
            help = "只输入名字，不要包含Activity"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val layoutName = stringParameter {
            name = "Layout Name"
            default = "activity_main"
            help = "请输入布局的名字"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { "${activityToLayout(className.value.toCamelCase())}" }
        }

        val isViewMode = booleanParameter {
            name = "Is ViewMode"
            default = true
            help = "是否生成ViewMode代码"
        }

        val isResourcePrefix = booleanParameter {
            name = "Is ResourcePrefix"
            default = true
            help = "是否约束资源文件命名(组件化开发的时候勾选)"
        }

        val title = stringParameter {
            name = "Activity Title"
            default = ""
            help = "Activity标题(有值则显示标题栏否则隐藏)"
        }

        val author = stringParameter {
            name = "Author"
            help = "开发者"
            default = ""
            constraints = listOf(Constraint.NONEMPTY)

        }

        val classDesc = stringParameter {
            name = "Description"
            default = title.value
            help = "描述一下方法的作用"
            constraints = listOf(Constraint.NONEMPTY)
        }


        widgets(
                TextFieldWidget(className),
                CheckBoxWidget(isViewMode),
                CheckBoxWidget(isResourcePrefix),
                TextFieldWidget(title),
                TextFieldWidget(classDesc),
                TextFieldWidget(author),
                PackageNameWidget(packageName)
        )

        thumb { File("template_login_activity.png") }


        recipe = { data: TemplateData ->
            VLibraryActivityRecipe(
                    data as ModuleTemplateData,
                    className.value,
                    "${activityToLayout(className.value.toCamelCase())}",
                    title.value,
                    packageName.value,
                    isViewMode.value,
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


