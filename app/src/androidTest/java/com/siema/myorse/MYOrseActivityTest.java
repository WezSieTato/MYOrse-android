package com.siema.myorse;

import android.content.Context;
import android.preference.PreferenceManager;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

public class MYOrseActivityTest extends ActivityInstrumentationTestCase2<MYOrseActivity> {


    private Solo solo;

    public MYOrseActivityTest() {
        super(MYOrseActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
        Context context = getInstrumentation().getTargetContext();
        context.getSharedPreferences(MYOrseActivity.PreferencesKey, Context.MODE_PRIVATE).edit().clear().commit();
    }

    public void test() throws Exception {
        solo.assertCurrentActivity("wrong activity", MYOrseActivity.class);

        solo.clickOnCheckBox(0);
//        solo.clickOnButton(solo.getString(R.string.pick_person));
//        solo.waitForDialogToClose();
//        solo.goBack();
//        solo.waitForText(solo.getString(R.string.))
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

}