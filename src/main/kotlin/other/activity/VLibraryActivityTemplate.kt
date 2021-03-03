package other.activity

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:09
 */
import android.databinding.tool.ext.toCamelCase
import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import java.io.File


val VLibraryActivityTemplate
    get() = template {
        revision = 1
        name = "VLibrary Activity"
        description = "适用于VLibrary框架的Activity"
        minApi = MIN_API
        minBuildApi = MIN_API

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)

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

        val isModeView = booleanParameter {
            name = "Is ViewMode"
            default = true
            help = "是否生成ViewMode代码"
        }

        val isResourcePrefix = booleanParameter {
            name = "Is ResourcePrefix"
            default = true
            help = "是否约束资源文件命名"
        }

        val title = stringParameter {
            name = "Activity Title"
            default = layoutName.value
            help = "Activity标题"
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { "${activityToLayout(className.value.toCamelCase())}" }
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
                CheckBoxWidget(isModeView),
                CheckBoxWidget(isResourcePrefix),
                PackageNameWidget(packageName),
                TextFieldWidget(title),
                TextFieldWidget(classDesc),
                TextFieldWidget(author)
        )

        thumb { File("template_login_activity.png") }

        recipe = { data: TemplateData ->
            VLibraryActivityRecipe(
                    data as ModuleTemplateData,
                    className.value,
                    "${activityToLayout(className.value.toCamelCase())}",
                    packageName.value,
                    isModeView.value,
                    isResourcePrefix.value,
                    title.value,
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


