package other

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:09
 */
import android.databinding.tool.ext.toCamelCase
import com.android.tools.idea.res.getResourceName
import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import com.intellij.openapi.util.IconLoader
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

        val activityClass = stringParameter {
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
            suggest = { "${activityToLayout(activityClass.value.toCamelCase())}" }
        }

        val isModeView = booleanParameter {
            name = "Is ModeView"
            default = true
            help = "是否生成ModeView代码"
        }


        widgets(
                TextFieldWidget(activityClass),
                CheckBoxWidget(isModeView),
                TextFieldWidget(layoutName),
                PackageNameWidget(packageName),
        )

        recipe = { data: TemplateData ->
            VLibraryActivityRecipe(
                    data as ModuleTemplateData,
                    activityClass.value,
                    layoutName.value,
                    packageName.value,
                    isModeView.value)
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


