package com.shopgun.android.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import androidx.core.content.ContextCompat;

import java.util.concurrent.TimeUnit;

public class LocationUtils {

    public static Location getLastKnownLocation(Context context) {
        Location bestLastKnown = null;
        if (hasAnyLocationPermission(context)) {
            LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            if (manager != null) {
                for (String provider : manager.getProviders(true)) {
                    Location l = manager.getLastKnownLocation(provider);
                    if (LocationUtils.isValidAndBetter(l, bestLastKnown)) {
                        bestLastKnown = l;
                    }
                }
            }
        }
        return bestLastKnown;
    }

    public static boolean hasAnyLocationPermission(Context context) {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isValidAndBetter(Location newLocation, Location current) {
        return (isValidLocation(newLocation) && isBetterLocation(newLocation, current));
    }

    public static boolean isValidLocation(Location l) {
        return l != null && (l.getLatitude() != 0.0d && l.getLongitude() != 0.0d);
    }

    /**
     * Determines whether one Location reading is better than the current
     * Location fix
     *
     * @param newLocation     The new Location that you want to evaluate
     * @param currentLocation The current Location fix, to which you want to compare the new
     *                        one
     * @return {@code true} if the {@code newLocation} is better than the {@code currentLocation}, else {@code false}
     */
    public static boolean isBetterLocation(Location newLocation, Location currentLocation) {

        // A new location is always better than no location
        if (currentLocation == null) {
            return true;
        } else if (newLocation == null) {
            return false;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = newLocation.getTime() - currentLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TimeUnit.MINUTES.toMillis(3);
        boolean isSignificantlyOlder = timeDelta < -TimeUnit.MINUTES.toMillis(3);
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use
        // the new location
        // because the user has likely moved
        if (isSignificantlyNewer) {
            return true;
            // If the new location is more than two minutes older, it must be
            // worse
        } else if (isSignificantlyOlder) {
            return false;
        }

        // If we've moved less than 1 meters we'll ignore the update
        float distance = newLocation.distanceTo(currentLocation);
        if (distance < 1) {
            return false;
        }

        // If we moved a significant distance, and have a lower than distance accuracy
        float distVsAccuracy = distance - newLocation.getAccuracy();
        if (distVsAccuracy > 0) {
            return true;
        }

        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (newLocation.getAccuracy() - currentLocation
                .getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(newLocation.getProvider(),
                currentLocation.getProvider());

        // Determine location quality using a combination of timeliness and
        // accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }

        return false;
    }

    /**
     * Checks whether two providers are the same
     * @param provider1 a provider
     * @param provider2 another provider
     * @return {@code true} if the providers are the same, else {@code false}
     */
    public static boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }

}
