package com.service.util.Jsp;

import com.entity.Role;

import java.util.HashMap;
import java.util.Map;

public class JspMap {
    private static Map<JspKey, String> jspMap;


    private JspMap() {


    }

    public static String getJspUrl(Role role, Stage stage) {
        if (jspMap == null) {
            jspMap = new HashMap<>();
            init();
        }
        return jspMap.get(new JspKey(role, stage));
    }

    private static void init() {
        jspMap.put(new JspKey(Role.MODERATOR, Stage.CONFERENCE_FINISHED), "conferencesFinished");
        jspMap.put(new JspKey(Role.ADMIN, Stage.CONFERENCE_FINISHED), "conferencesFinished");
        jspMap.put(new JspKey(Role.VISITOR, Stage.CONFERENCE_FINISHED), "conferencesFinished");
        jspMap.put(new JspKey(Role.SPEAKER, Stage.CONFERENCE_FINISHED), "conferencesFinished");


        jspMap.put(new JspKey(Role.ADMIN, Stage.CONFERENCES_COMING), "conferencesWithEdit"); // якщо юзер адмін або модератор то ми даєму іншу джіеспі
        jspMap.put(new JspKey(Role.MODERATOR, Stage.CONFERENCES_COMING), "conferencesWithEdit"); // якщо юзер адмін або модератор то ми даєму іншу джіеспі
        jspMap.put(new JspKey(Role.VISITOR, Stage.CONFERENCES_COMING), "conferencesForLook"); // якщо юзер адмін або модератор то ми даєму іншу джіеспі
        jspMap.put(new JspKey(Role.SPEAKER, Stage.CONFERENCES_COMING), "conferencesForLook"); // якщо юзер адмін або модератор то ми даєму іншу джіеспі


        jspMap.put(new JspKey(Role.MODERATOR, Stage.EDIT_CONFERENCE), "conferenceEdit");
        jspMap.put(new JspKey(Role.ADMIN, Stage.EDIT_CONFERENCE), "conferenceEdit");

        jspMap.put(new JspKey(Role.VISITOR, Stage.SPEECHES), "SpeechesForLook");
        jspMap.put(new JspKey(Role.SPEAKER, Stage.SPEECHES), "SpeechesWithEdit");
        jspMap.put(new JspKey(Role.ADMIN, Stage.SPEECHES), "SpeechesWithEdit");
        jspMap.put(new JspKey(Role.MODERATOR, Stage.SPEECHES), "SpeechesWithEdit");

        jspMap.put(new JspKey(Role.MODERATOR, Stage.MODERATOR_EDIT_SPEECHES), "moderatorSpeechEdit");
        jspMap.put(new JspKey(Role.ADMIN, Stage.ADMIN_EDIT_SPEECHES), "SpeechesEditPage");
        jspMap.put(new JspKey(Role.SPEAKER, Stage.SPEAKER_EDIT_SPEECHES), "speakerSpeechesEditPage");

        jspMap.put(new JspKey(Role.ADMIN, Stage.SPEAKER_RATINGS), "editSpeakerRating");


    }
}