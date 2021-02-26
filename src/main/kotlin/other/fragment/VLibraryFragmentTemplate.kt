package other.fragment

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:09
 */
import android.databinding.tool.ext.toCamelCase
import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import java.io.File


val VLibraryFragmentTemplate
    get() = template {
        revision = 1
        name = "VLibrary Fragment"
        description = "适用于VLibrary框架的Fragment"
        minApi = MIN_API
        minBuildApi = MIN_API

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)

        val packageName = defaultPackageNameParameter

        val className = stringParameter {
            name = "Fragment Name"
            default = "Main"
            help = "只输入名字，不要包含Fragment"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val layoutName = stringParameter {
            name = "Layout Name"
            default = "fragment_main"
            help = "请输入布局的名字"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { "${fragmentToLayout(className.value.toCamelCase())}" }
        }

        val isModeView = booleanParameter {
            name = "Is ViewMode"
            default = true
            help = "是否生成ViewMode代码"
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
                PackageNameWidget(packageName),
                TextFieldWidget(classDesc),
                TextFieldWidget(author)
        )

        thumb { File("template_login_activity.png") }

        recipe = { data: TemplateData ->
            VLibraryFragmentRecipe(
                    data as ModuleTemplateData,
                    className.value,
                    "${activityToLayout(className.value.toCamelCase())}",
                    packageName.value,
                    isModeView.value,
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

