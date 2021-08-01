# AndroidWifiScanner

### Note
There is more restriction for Android 8 and 9 and 10

`startScan` deprecated but still able to use

`startScan` will return boolean. There is a limitation of `startScan` in background and foreground. For example,

> **Each** foreground app can scan four times in a 2-minute period. This allows for a burst of scans in a short time.
> All background apps combined can scan one time in a 30-minute period.

If we exceed 4 times, `startScan` will **return false** BUT we **still ABLE to receive the latest** `Broadcast Receiver`.
However, it may be **out up date**.

If we use only 1 app for scan, after 4 scan in 2 minutes, `startScan` will fail until 2 minutes pass (we need to handle this case to prevent user don't see the wifi during 2 minutes).
If we using another app for scan during this 2 minutes, we can scan success. After it success, if
we go back to first app, `startScan` still failed but we will see the **latest** list wifi (look like it share the same data and
document said limitation for each application is CORRECT)

For testing purpose, we can disable this limitation in `Developer Options > Networking > Wi-Fi scan throttling`
### Reference
https://developer.android.com/guide/topics/connectivity/wifi-scan