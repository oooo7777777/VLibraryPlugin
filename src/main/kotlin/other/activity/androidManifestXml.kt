package other.activity

/**
 * @Author : ww
 * desc    :
 * time    : 2021/5/27 15:41
 */

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