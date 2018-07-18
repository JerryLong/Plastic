package com.conduit.plastic.view.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.conduit.plastic.view.banner.transformer.AccordionTransformer;
import com.conduit.plastic.view.banner.transformer.BackgroundToForegroundTransformer;
import com.conduit.plastic.view.banner.transformer.CubeInTransformer;
import com.conduit.plastic.view.banner.transformer.CubeOutTransformer;
import com.conduit.plastic.view.banner.transformer.DefaultTransformer;
import com.conduit.plastic.view.banner.transformer.DepthPageTransformer;
import com.conduit.plastic.view.banner.transformer.FlipHorizontalTransformer;
import com.conduit.plastic.view.banner.transformer.FlipVerticalTransformer;
import com.conduit.plastic.view.banner.transformer.ForegroundToBackgroundTransformer;
import com.conduit.plastic.view.banner.transformer.RotateDownTransformer;
import com.conduit.plastic.view.banner.transformer.RotateUpTransformer;
import com.conduit.plastic.view.banner.transformer.ScaleInOutTransformer;
import com.conduit.plastic.view.banner.transformer.StackTransformer;
import com.conduit.plastic.view.banner.transformer.TabletTransformer;
import com.conduit.plastic.view.banner.transformer.ZoomInTransformer;
import com.conduit.plastic.view.banner.transformer.ZoomOutSlideTransformer;
import com.conduit.plastic.view.banner.transformer.ZoomOutTranformer;


public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
