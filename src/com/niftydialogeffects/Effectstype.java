package com.niftydialogeffects;

import com.niftydialogeffects.effects.BaseEffects;
import com.niftydialogeffects.effects.FadeIn;
import com.niftydialogeffects.effects.Fall;
import com.niftydialogeffects.effects.FlipH;
import com.niftydialogeffects.effects.FlipV;
import com.niftydialogeffects.effects.NewsPaper;
import com.niftydialogeffects.effects.RotateBottom;
import com.niftydialogeffects.effects.RotateLeft;
import com.niftydialogeffects.effects.Shake;
import com.niftydialogeffects.effects.SideFall;
import com.niftydialogeffects.effects.SlideBottom;
import com.niftydialogeffects.effects.SlideLeft;
import com.niftydialogeffects.effects.SlideRight;
import com.niftydialogeffects.effects.SlideTop;
import com.niftydialogeffects.effects.Slit;

/**
 * Created by lee on 2014/7/30.
 */
public enum  Effectstype {

    Fadein(FadeIn.class),
    Slideleft(SlideLeft.class),
    Slidetop(SlideTop.class),
    SlideBottom(SlideBottom.class),
    Slideright(SlideRight.class),
    Fall(Fall.class),
    Newspager(NewsPaper.class),
    Fliph(FlipH.class),
    Flipv(FlipV.class),
    RotateBottom(RotateBottom.class),
    RotateLeft(RotateLeft.class),
    Slit(Slit.class),
    Shake(Shake.class),
    Sidefill(SideFall.class);
    private Class effectsClazz;

    private Effectstype(Class mclass) {
        effectsClazz = mclass;
    }

    public BaseEffects getAnimator() {
        try {
            return (BaseEffects) effectsClazz.newInstance();
        } catch (Exception e) {
            throw new Error("Can not init animatorClazz instance");
        }
    }
}
