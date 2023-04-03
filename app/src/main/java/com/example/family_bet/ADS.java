package com.example.family_bet;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.yodo1.mas.banner.Yodo1MasBannerAdListener;
import com.yodo1.mas.banner.Yodo1MasBannerAdView;
import com.yodo1.mas.error.Yodo1MasError;
import com.yodo1.mas.interstitial.Yodo1MasInterstitialAd;
import com.yodo1.mas.interstitial.Yodo1MasInterstitialAdListener;
import com.yodo1.mas.reward.Yodo1MasRewardAd;
import com.yodo1.mas.reward.Yodo1MasRewardAdListener;

public class ADS {
    /**
     * show banner on activity
     * @param context-activity for the banner
     */
    public static void show_Banner(Activity context){



        Yodo1MasBannerAdView bannerAdView=context.findViewById(R.id.yodo1_mas_banner);
        bannerAdView.loadAd();
        bannerAdView.setAdListener(new Yodo1MasBannerAdListener() {
            @Override
            public void onBannerAdLoaded(Yodo1MasBannerAdView bannerAdView) {

            }

            @Override
            public void onBannerAdFailedToLoad(Yodo1MasBannerAdView bannerAdView, @NonNull Yodo1MasError error) {

            }

            @Override
            public void onBannerAdOpened(Yodo1MasBannerAdView bannerAdView) {

            }

            @Override
            public void onBannerAdFailedToOpen(Yodo1MasBannerAdView bannerAdView, @NonNull Yodo1MasError error) {

            }

            @Override
            public void onBannerAdClosed(Yodo1MasBannerAdView bannerAdView) {

            }
        });

    }
    /**
     * show interstitial on activity
     * @param context-activity for the Interstitial
     */
    public static void show_Interstitial(Activity context){
        Yodo1MasInterstitialAd.getInstance().setAdListener(new Yodo1MasInterstitialAdListener() {

            @Override
            public void onInterstitialAdLoaded(Yodo1MasInterstitialAd ad) {
                Yodo1MasInterstitialAd.getInstance().loadAd(context);
                boolean isLoaded = Yodo1MasInterstitialAd.getInstance().isLoaded();
                if(isLoaded) Yodo1MasInterstitialAd.getInstance().showAd(context);
            }

            @Override
            public void onInterstitialAdFailedToLoad(Yodo1MasInterstitialAd ad, @NonNull Yodo1MasError error) {

            }

            @Override
            public void onInterstitialAdOpened(Yodo1MasInterstitialAd ad) {

            }

            @Override
            public void onInterstitialAdFailedToOpen(Yodo1MasInterstitialAd ad, @NonNull Yodo1MasError error) {
                ad.loadAd(context);
            }

            @Override
            public void onInterstitialAdClosed(Yodo1MasInterstitialAd ad) {
                ad.loadAd(context);
            }
        });

    }

    /**
     * show reward ad
     * @param context-activity for the reward
     */
    public static void Reward_ad(Activity context){
        Yodo1MasRewardAd.getInstance().setAdListener(new Yodo1MasRewardAdListener() {

            @Override
            public void onRewardAdLoaded(Yodo1MasRewardAd ad) {
                ad.showAd(context);
            }

            @Override
            public void onRewardAdFailedToLoad(Yodo1MasRewardAd ad, @NonNull Yodo1MasError error) {

            }

            @Override
            public void onRewardAdOpened(Yodo1MasRewardAd ad) {

            }

            @Override
            public void onRewardAdFailedToOpen(Yodo1MasRewardAd ad, @NonNull Yodo1MasError error) {
                ad.loadAd(context);
            }

            @Override
            public void onRewardAdClosed(Yodo1MasRewardAd ad) {
                ad.loadAd(context);
            }

            @Override
            public void onRewardAdEarned(Yodo1MasRewardAd ad) {

            }
        });
        Yodo1MasRewardAd.getInstance().loadAd(context);
        boolean isLoaded = Yodo1MasRewardAd.getInstance().isLoaded();
        if(isLoaded) Yodo1MasRewardAd.getInstance().showAd(context);
    }
}
