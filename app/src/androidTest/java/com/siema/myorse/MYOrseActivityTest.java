package com.siema.myorse;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

public class MYOrseActivityTest extends ActivityInstrumentationTestCase2<MYOrseActivity> {


    private Solo solo;

    public MYOrseActivityTest() {
        super(MYOrseActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void test() throws Exception {
        solo.assertCurrentActivity("wrong activity", MYOrseActivity.class);

        solo.clickOnButton(solo.getString(R.string.pick_person));
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

}