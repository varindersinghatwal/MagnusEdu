package com.magnus.edutech.interfaces;
/**
 * created by jugalkishorjoshi on
 * */
import android.content.DialogInterface;

public interface GenericPromptDialogBoxInterface {

    public abstract void PositiveMethod(DialogInterface dialog, int id,String value);

    public abstract void NegativeMethod(DialogInterface dialog, int id);
}