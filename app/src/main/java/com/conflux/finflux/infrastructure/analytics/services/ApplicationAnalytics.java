package com.conflux.finflux.infrastructure.analytics.services;

import com.conflux.finflux.infrastructure.analytics.data.FabricIoConstants;
import com.conflux.finflux.util.PrefManager;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.crashlytics.android.answers.LoginEvent;

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

    public static void sendLoginStatus(boolean status,String username,String message){
        Answers.getInstance().logLogin(new LoginEvent()
                .putMethod("Login")
                .putSuccess(status)
                .putCustomAttribute(FabricIoConstants.USERNAME, username)
                .putCustomAttribute(FabricIoConstants.MESSAGE, message)
                .putCustomAttribute(FabricIoConstants.ORGANIZATION_NAME, PrefManager.getOrganization()));
    }

    public static void sendEventLogs(String eventName,String message){
        String organizationName=PrefManager.getOrganization();
        Answers.getInstance().logCustom(new CustomEvent(organizationName)
                .putCustomAttribute(organizationName+"_"+eventName,message));
    }
}
