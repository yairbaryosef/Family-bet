package com.example.family_bet.DB;

import com.example.family_bet.MESSAGES.FcmNotificationsSender;

import junit.framework.TestCase;

public class NBATest extends TestCase {
   volatile boolean sleep;
    public void testGet_games_for_today() throws Exception
    {
        FcmNotificationsSender sender=new FcmNotificationsSender();
        String s="abc";
        sender.sendNotification("all",s);
    }

}