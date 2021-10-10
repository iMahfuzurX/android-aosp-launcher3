/*
 * Copyright (C) 2020 The Android Open Source Project
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
package com.android.launcher3.util;

import static org.mockito.Mockito.mock;

import android.app.Application;

import com.android.launcher3.shadows.ShadowMainThreadInitializedObject;
import com.android.launcher3.shadows.ShadowOverrides;
import com.android.launcher3.uioverrides.plugins.PluginManagerWrapper;

import org.robolectric.TestLifecycleApplication;
import org.robolectric.shadows.ShadowLog;

import java.lang.reflect.Method;

public class LauncherTestApplication extends Application implements TestLifecycleApplication {

    @Override
    public void beforeTest(Method method) {
        ShadowLog.stream = System.out;

        // Disable plugins
        PluginManagerWrapper.INSTANCE.initializeForTesting(mock(PluginManagerWrapper.class));
    }

    @Override
    public void prepareTest(Object test) { }

    @Override
    public void afterTest(Method method) {
        ShadowLog.stream = null;
        ShadowMainThreadInitializedObject.resetInitializedObjects();
        ShadowOverrides.clearProvider();
    }
}
