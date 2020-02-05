package com.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;


public class Speech {
    private static Logger logger = LoggerFactory.getLogger(Speech.class);
    private int id;
    private User speaker;
    private String topic;
    private String suggestedTopic;
    private Conference conference;
    private int startHour;
    private int endHour;


    public Speech(SpeechBuilder builder) {
        this.speaker = builder.speaker;
        this.topic = builder.topic;
        this.suggestedTopic = builder.suggestedTopic;
        this.startHour = builder.startHour;
        this.endHour = builder.endHour;
        this.conference = builder.conference;
        this.id = builder.id;
    }

    public static SpeechBuilder builder() {
        return new SpeechBuilder();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSpeaker() {
        return speaker;
    }

    public void setSpeaker(User speaker) {
        this.speaker = speaker;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSuggestedTopic() {
        return suggestedTopic;
    }

    public void setSuggestedTopic(String suggestedTopic) {
        this.suggestedTopic = suggestedTopic;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public static class SpeechBuilder {
        private User speaker;
        private String topic;
        private String suggestedTopic;
        private int startHour;
        private int endHour;
        private Conference conference;
        private int id;


        public SpeechBuilder() {

        }

        public SpeechBuilder withConference(Conference cOnference) {
            this.conference = cOnference;
            return this;
        }

        public SpeechBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public SpeechBuilder withSpeakerId(User speaker) {
            this.speaker = speaker;
            return this;
        }

        public SpeechBuilder withTopic(String topic) {
            this.topic = topic;
            return this;
        }

        public SpeechBuilder withSuggestedTopic(String suggestedTopic) {
            this.suggestedTopic = suggestedTopic;
            return this;
        }

        public SpeechBuilder withStartHour(int startHour) {
            if (startHour <= 24 && startHour >= 0) {
                this.startHour = startHour;
                return this;

            } else {
                logger.error(String.format("Wrong startHour - [%o]",startHour));
                throw new RuntimeException();
            }
        }

        public SpeechBuilder withEndHour(int endHour) {
            if (endHour <= 24 && endHour >= 0) {
                this.endHour = endHour;
                return this;

            } else {
                logger.error(String.format("Wrong endHour - [%o]",endHour));
                throw new RuntimeException();
            }
        }

        public Speech build() {
            return new Speech(this);
        }


    }

}
