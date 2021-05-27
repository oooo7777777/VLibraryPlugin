package other.activity

/**
 * @Author : ww
 * desc    :
 * time    : 2021/5/27 15:41
 */
/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.android.tools.idea.wizard.template.impl.activities.common.commonActivityBody
fun androidManifestXml(
    activityClass: String
): String {
    return """
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <application>
        <activity android:name="${activityClass}"/>
    </application>
</manifest>
"""
}