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
        jspMap.put(new JspKey(null, Stage.LOGIN), "/pages/login.jsp");
        jspMap.put(new JspKey(Role.MODERATOR, Stage.CONFERENCE_FINISHED), "/pages/conferencesFinished");
        jspMap.put(new JspKey(Role.ADMIN, Stage.CONFERENCE_FINISHED), "/pages/conferencesFinished");
        jspMap.put(new JspKey(Role.VISITOR, Stage.CONFERENCE_FINISHED), "/pages/conferencesFinished");
        jspMap.put(new JspKey(Role.SPEAKER, Stage.CONFERENCE_FINISHED), "/pages/conferencesFinished");


        jspMap.put(new JspKey(Role.ADMIN, Stage.CONFERENCES_COMING), "/pages/conferencesWithEdit.jsp"); // якщо юзер адмін або модератор то ми даєму іншу джіеспі
        jspMap.put(new JspKey(Role.MODERATOR, Stage.CONFERENCES_COMING), "/pages/conferencesWithEdit.jsp"); // якщо юзер адмін або модератор то ми даєму іншу джіеспі
        jspMap.put(new JspKey(Role.VISITOR, Stage.CONFERENCES_COMING), "/pages/conferencesForLook.jsp"); // якщо юзер адмін або модератор то ми даєму іншу джіеспі
        jspMap.put(new JspKey(Role.SPEAKER, Stage.CONFERENCES_COMING), "/pages/conferencesForLook.jsp"); // якщо юзер адмін або модератор то ми даєму іншу джіеспі


        jspMap.put(new JspKey(Role.MODERATOR, Stage.EDIT_CONFERENCE), "/pages/conferenceEdit.jsp");
        jspMap.put(new JspKey(Role.ADMIN, Stage.EDIT_CONFERENCE), "/pages/editConference.jsp");

        jspMap.put(new JspKey(Role.VISITOR, Stage.SPEECHES), "/pages/visitorSpeechesPage.jsp");
        jspMap.put(new JspKey(Role.SPEAKER, Stage.SPEECHES), "/pages/speakerSpeechesPage.jsp");
        jspMap.put(new JspKey(Role.ADMIN, Stage.SPEECHES), "/pages/adminSpeechesPage.jsp");
        jspMap.put(new JspKey(Role.MODERATOR, Stage.SPEECHES), "/pages/moderatorSpeechesPage.jsp");

        jspMap.put(new JspKey(Role.MODERATOR, Stage.MODERATOR_EDIT_SPEECHES), "/pages/moderatorSpeechEdit.jsp");
        jspMap.put(new JspKey(Role.ADMIN, Stage.ADMIN_EDIT_SPEECHES), "/pages/SpeechesEditPage.jsp");
        jspMap.put(new JspKey(Role.SPEAKER, Stage.SPEAKER_EDIT_SPEECHES), "/pages/speakerSpeechesEditPage.jsp");

        jspMap.put(new JspKey(Role.ADMIN, Stage.SPEAKER_RATINGS), "/pages/editSpeakerRating.jsp");


    }
}