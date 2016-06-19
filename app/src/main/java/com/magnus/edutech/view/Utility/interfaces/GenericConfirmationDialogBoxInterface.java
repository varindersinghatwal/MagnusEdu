package com.magnus.edutech.view.utility.interfaces;
/**
 * created by jugalkishorjoshi on
 * */
import android.content.DialogInterface;

public interface GenericConfirmationDialogBoxInterface {

    public abstract void PositiveMethod(DialogInterface dialog, int id);

    public abstract void NegativeMethod(DialogInterface dialog, int id);
}