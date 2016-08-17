/*******************************************************************************
 * Copyright 2015 ShopGun
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.shopgun.android.utils;

/**
 * For now this class remains empty, too many unused imports.
 */
public class ServiceUtils {

    public static final String TAG = Tag.from(ServiceUtils.class);

    private ServiceUtils() {
        // private constructor
    }

//    public static AccessibilityManager getAccessibilityManager(Context ctx) {
//        return (AccessibilityManager) ctx.getSystemService(Context.ACCESSIBILITY_SERVICE);
//    }
//
//    public static AccountManager getAccountManager(Context ctx) {
//        return (AccountManager) ctx.getSystemService(Context.ACCOUNT_SERVICE);
//    }
//
//    public static ActivityManager getActivityManager(Context ctx) {
//        return (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
//    }
//
//    public static AlarmManager getAlarmManager(Context ctx) {
//        return (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
//    }
//
//    public static AudioManager getAudioManager(Context ctx) {
//        return (AudioManager) ctx.getSystemService(Context.AUDIO_SERVICE);
//    }
//
//    public static ClipboardManager getClipboardManager(Context ctx) {
//        return (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
//    }
//
//    public static ConnectivityManager getConnectivityManager(Context ctx) {
//        return (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
//    }
//
//    public static InputMethodManager getInputMethodManager(Context ctx) {
//        return (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
//    }
//
//    public static KeyguardManager getKeyguardManager(Context ctx) {
//        return (KeyguardManager) ctx.getSystemService(Context.KEYGUARD_SERVICE);
//    }
//
//    public static LayoutInflater getLayoutInflater(Context ctx) {
//        return (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    public static LocationManager getLocationManager(Context ctx) {
//        return (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
//    }
//
//    public static NotificationManager getNotificationManager(Context ctx) {
//        return (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
//    }
//
//    public static PowerManager getPowerManager(Context ctx) {
//        return (PowerManager) ctx.getSystemService(Context.POWER_SERVICE);
//    }
//
//    public static SearchManager getSearchManager(Context ctx) {
//        return (SearchManager) ctx.getSystemService(Context.SEARCH_SERVICE);
//    }
//
//    public static SensorManager getSensorManager(Context ctx) {
//        return (SensorManager) ctx.getSystemService(Context.SENSOR_SERVICE);
//    }
//
//    public static TelephonyManager getTelephonyManager(Context ctx) {
//        return (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
//    }
//
//    public static Vibrator getVibrator(Context ctx) {
//        return (Vibrator) ctx.getSystemService(Context.VIBRATOR_SERVICE);
//    }
//
//    public static WallpaperManager getWallpaperManager(Context ctx) {
//        return WallpaperManager.getInstance(ctx);
//    }
//
//    public static WifiManager getWifiManager(Context ctx) {
//        return (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
//    }
//
//    public static WindowManager getWindowManager(Context ctx) {
//        return (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
//    }
//
//    @TargetApi(8)
//    public static DropBoxManager getDropBoxManager(Context ctx) {
//        return (DropBoxManager) ctx.getSystemService(Context.DROPBOX_SERVICE);
//    }
//
//    @TargetApi(8)
//    public static UiModeManager getUiModeManager(Context ctx) {
//        return (UiModeManager) ctx.getSystemService(Context.UI_MODE_SERVICE);
//    }
//
//    @TargetApi(8)
//    public static DevicePolicyManager getDevicePolicyManager(Context ctx) {
//        return (DevicePolicyManager) ctx.getSystemService(Context.DEVICE_POLICY_SERVICE);
//    }
//
//    @TargetApi(9)
//    public static StorageManager getStorageManager(Context ctx) {
//        return (StorageManager) ctx.getSystemService(Context.STORAGE_SERVICE);
//    }
//
//    @TargetApi(9)
//    public static DownloadManager getDownloadManager(Context ctx) {
//        return (DownloadManager) ctx.getSystemService(Context.DOWNLOAD_SERVICE);
//    }
//
//    @TargetApi(10)
//    public static NfcManager getNfcManager(Context ctx) {
//        return (NfcManager) ctx.getSystemService(Context.NFC_SERVICE);
//    }
//
//    @TargetApi(12)
//    public static UsbManager getUsbManager(Context ctx) {
//        return (UsbManager) ctx.getSystemService(Context.USB_SERVICE);
//    }
//
//    @TargetApi(14)
//    public static TextServicesManager getTextServicesManager(Context ctx) {
//        return (TextServicesManager) ctx.getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE);
//    }
//
//    @TargetApi(14)
//    public static WifiP2pManager getWifiP2pManager(Context ctx) {
//        return (WifiP2pManager) ctx.getSystemService(Context.WIFI_P2P_SERVICE);
//    }
//
//    @TargetApi(16)
//    public static InputManager getInputManager(Context ctx) {
//        return (InputManager) ctx.getSystemService(Context.INPUT_SERVICE);
//    }
//
//    @TargetApi(16)
//    public static NsdManager getNsdManager(Context ctx) {
//        return (NsdManager) ctx.getSystemService(Context.NSD_SERVICE);
//    }
//
//    @TargetApi(16)
//    public static MediaRouter getMediaRouter(Context ctx) {
//        return (MediaRouter) ctx.getSystemService(Context.MEDIA_ROUTER_SERVICE);
//    }
//
//    @TargetApi(17)
//    public static DisplayManager getDisplayManager(Context ctx) {
//        return (DisplayManager) ctx.getSystemService(Context.DISPLAY_SERVICE);
//    }
//
//    @TargetApi(17)
//    public static UserManager getUserManager(Context ctx) {
//        return (UserManager) ctx.getSystemService(Context.USER_SERVICE);
//    }
//
//    @TargetApi(18)
//    public static BluetoothManager getBluetoothManager(Context ctx) {
//        return (BluetoothManager) ctx.getSystemService(Context.BLUETOOTH_SERVICE);
//    }
//
//    @TargetApi(19)
//    public static AppOpsManager getAppOpsManager(Context ctx) {
//        return (AppOpsManager) ctx.getSystemService(Context.APP_OPS_SERVICE);
//    }
//
//    @TargetApi(19)
//    public static ConsumerIrManager getConsumerIrManager(Context ctx) {
//        return (ConsumerIrManager) ctx.getSystemService(Context.CONSUMER_IR_SERVICE);
//    }
//
//    @TargetApi(19)
//    public static CaptioningManager getCaptioningManager(Context ctx) {
//        return (CaptioningManager) ctx.getSystemService(Context.CAPTIONING_SERVICE);
//    }
//
//    @TargetApi(19)
//    public static PrintManager getPrintManager(Context ctx) {
//        return (PrintManager) ctx.getSystemService(Context.PRINT_SERVICE);
//    }
//
//    @TargetApi(21)
//    public static AppWidgetManager getAppWidgetManager(Context ctx) {
//        return (AppWidgetManager) ctx.getSystemService(Context.APPWIDGET_SERVICE);
//    }
//
//    @TargetApi(21)
//    public static BatteryManager getBatteryManager(Context ctx) {
//        return (BatteryManager) ctx.getSystemService(Context.BATTERY_SERVICE);
//    }
//
//    @TargetApi(21)
//    public static CameraManager getCameraManager(Context ctx) {
//        return (CameraManager) ctx.getSystemService(Context.CAMERA_SERVICE);
//    }
//
//    @TargetApi(21)
//    public static JobScheduler getJobScheduler(Context ctx) {
//        return (JobScheduler) ctx.getSystemService(Context.JOB_SCHEDULER_SERVICE);
//    }
//
//    @TargetApi(21)
//    public static LauncherApps getLauncherApps(Context ctx) {
//        return (LauncherApps) ctx.getSystemService(Context.LAUNCHER_APPS_SERVICE);
//    }
//
//    @TargetApi(21)
//    public static MediaProjectionManager getMediaProjectionManager(Context ctx) {
//        return (MediaProjectionManager) ctx.getSystemService(Context.MEDIA_PROJECTION_SERVICE);
//    }
//
//    @TargetApi(21)
//    public static MediaSessionManager getMediaSessionManager(Context ctx) {
//        return (MediaSessionManager) ctx.getSystemService(Context.MEDIA_SESSION_SERVICE);
//    }
//
//    @TargetApi(21)
//    public static RestrictionsManager getRestrictionsManager(Context ctx) {
//        return (RestrictionsManager) ctx.getSystemService(Context.RESTRICTIONS_SERVICE);
//    }
//
//    @TargetApi(21)
//    public static TelecomManager getTelecomManager(Context ctx) {
//        return (TelecomManager) ctx.getSystemService(Context.TELECOM_SERVICE);
//    }
//
//    @TargetApi(21)
//    public static TvInputManager getTvInputManager(Context ctx) {
//        return (TvInputManager) ctx.getSystemService(Context.TV_INPUT_SERVICE);
//    }
//
//    @TargetApi(22)
//    public static SubscriptionManager getSubscriptionManager(Context ctx) {
//        return (SubscriptionManager) ctx.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
//    }
//
//    @TargetApi(22)
//    public static UsageStatsManager getUsageStatsManager(Context ctx) {
//        return (UsageStatsManager) ctx.getSystemService(Context.USAGE_STATS_SERVICE);
//    }
//
//    @TargetApi(23)
//    public static CarrierConfigManager getCarrierConfigManager(Context ctx) {
//        return (CarrierConfigManager) ctx.getSystemService(Context.CARRIER_CONFIG_SERVICE);
//    }
//
//    @TargetApi(23)
//    public static FingerprintManager getFingerprintManager(Context ctx) {
//        return (FingerprintManager) ctx.getSystemService(Context.FINGERPRINT_SERVICE);
//    }
//
//    @TargetApi(23)
//    public static MidiManager getMidiManager(Context ctx) {
//        return (MidiManager) ctx.getSystemService(Context.MIDI_SERVICE);
//    }
//
//    @TargetApi(23)
//    public static NetworkStatsManager getNetworkStatsManager(Context ctx) {
//        return (NetworkStatsManager) ctx.getSystemService(Context.NETWORK_STATS_SERVICE);
//    }

}
