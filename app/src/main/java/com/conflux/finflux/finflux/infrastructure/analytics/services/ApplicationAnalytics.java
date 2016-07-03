package com.conflux.finflux.finflux.infrastructure.analytics.services;

import com.conflux.finflux.finflux.infrastructure.analytics.data.FabricIoConstants;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;

/**
 * Created by praveen on 6/29/2016.
 */
public class ApplicationAnalytics {
    public static void sendActivationStatus(String activationStatus,String activationKey){
        Answers.getInstance().logCustom(new CustomEvent(FabricIoConstants.ACTIVATION)
                .putCustomAttribute(FabricIoConstants.ACTIVATION_STATUS,activationStatus)
                .putCustomAttribute(FabricIoConstants.ACTIVATION_KEY,activationKey));
    }

    public static void sendApplicationLaunchedInformation(String applicationActiveStatus){
        Answers.getInstance().logCustom(new CustomEvent(FabricIoConstants.APPLICATION_LAUNCED)
                .putCustomAttribute(FabricIoConstants.ACTIVATION_STATUS, applicationActiveStatus));
    }

    public static void sendLoginStatus(String loginStatus){
        Answers.getInstance().logCustom(new CustomEvent(FabricIoConstants.APPLICATION_LAUNCED)
                .putCustomAttribute(FabricIoConstants.ACTIVATION_STATUS, loginStatus));
    }
}
