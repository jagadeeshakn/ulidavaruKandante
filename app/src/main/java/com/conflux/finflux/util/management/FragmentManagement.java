package com.conflux.finflux.util.management;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Praveen J U on 7/10/2016.
 */
public class FragmentManagement {

        public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment, boolean addToBackStack, int containerId) {
            String backStateName = fragment.getClass().getName();
            boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStateName,
                    0);

            if (!fragmentPopped && fragmentManager.findFragmentByTag(backStateName) ==
                    null) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(containerId, fragment, backStateName);
                if (addToBackStack) {
                    transaction.addToBackStack(backStateName);
                }
                transaction.commit();
            }
        }
}
