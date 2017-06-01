package com.around25.RNAmplitudeAndroid;

import android.app.Application;

import com.amplitude.api.Amplitude;
import com.amplitude.api.Revenue;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;

import org.json.JSONException;
import org.json.JSONObject;

public class RNAmplitudeAndroidModule extends ReactContextBaseJavaModule {

    private Application mApplication = null;

    public RNAmplitudeAndroidModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.mApplication = null;
    }

//    public RNAmplitudeAndroidModule(ReactApplicationContext reactContext, Application mApplication) {
//        super(reactContext);
//        this.mApplication = mApplication;
//    }

    @Override
    public String getName() {
        return "AmplitudeAndroid";
    }

    @ReactMethod
    public void initializeApiKey(String apiKey) {
        Amplitude.getInstance().trackSessionEvents(true);
        Amplitude.getInstance().initialize(getCurrentActivity(), apiKey).enableForegroundTracking(this.mApplication);
    }

    @ReactMethod
    public void setUserId(String id) {
        Amplitude.getInstance().setUserId(id);
    }

    @ReactMethod
    public void setUserProperties(ReadableMap properties) {
        try {
            JSONObject jProperties = convertReadableToJsonObject(properties);
            Amplitude.getInstance().setUserProperties(jProperties);
        } catch (JSONException e) {
            return;
        }
    }

    @ReactMethod
    public void logOutUser() {
        Amplitude.getInstance().setUserId(null);
    }

    @ReactMethod
    public void logEvent(String identifier) {
        Amplitude.getInstance().logEvent(identifier);
    }

    @ReactMethod
    public void logEventWithProperties(String identifier, ReadableMap properties) {

        try {
            JSONObject jProperties = convertReadableToJsonObject(properties);
            Amplitude.getInstance().logEvent(identifier, jProperties);
        } catch (JSONException e) {
            return;
        }

    }

    @ReactMethod
    public void logRevenue(ReadableMap properties, double price, int quantity) {
        try {
            JSONObject eProperties = convertReadableToJsonObject(properties);
            Revenue revenue = new Revenue().setPrice(price).setQuantity(quantity).setEventProperties(eProperties);
            Amplitude.getInstance().logRevenueV2(revenue);
        } catch (JSONException e) {
            return;
        }
    }

    @ReactMethod
    public void logRevenueWithAllProps(ReadableMap properties, double price, int quantity, int productIdentifier){
        try {
            JSONObject eProperties = convertReadableToJsonObject(properties);
            Revenue revenue = new Revenue().setPrice(price).setQuantity(quantity).setEventProperties(eProperties);
            Amplitude.getInstance().logRevenueV2(revenue);
        } catch (JSONException e) {
            return;
        }
    }

    public static JSONObject convertReadableToJsonObject(ReadableMap map) throws JSONException {
        JSONObject jsonObj = new JSONObject();
        ReadableMapKeySetIterator it = map.keySetIterator();

        while (it.hasNextKey()) {
            String key = it.nextKey();
            ReadableType type = map.getType(key);
            switch (type) {
                case Map:
                    jsonObj.put(key, convertReadableToJsonObject(map.getMap(key)));
                    break;
                case String:
                    jsonObj.put(key, map.getString(key));
                    break;
                case Number:
                    jsonObj.put(key, map.getDouble(key));
                    break;
                case Boolean:
                    jsonObj.put(key, map.getBoolean(key));
                    break;
                case Null:
                    jsonObj.put(key, null);
                    break;
            }
        }
        return jsonObj;
    }
}