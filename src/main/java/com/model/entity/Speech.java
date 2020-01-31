package com.model.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;


public class Speech {
    private static Logger logger = LoggerFactory.getLogger(Speech.class);
    private int id;
    private int speakerId;
    private String topic;
    private String suggestedTopic;
    private Conference conference;
    private int startHour;
    private int endHour;

    public Speech(SpeechBuilder builder) {
        this.speakerId = builder.speakerId;
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

    public int getSpeakerId() {
        return speakerId;
    }

    public String getTopic() {
        return topic;
    }

    public String getSuggestedTopic() {
        return suggestedTopic;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public Conference getConference() {
        return conference;
    }

    @Override
    public String toString() {
        return "Speech{" +
                "speakerId=" + speakerId +
                ", topic='" + topic + '\'' +
                ", suggestedTopic='" + suggestedTopic + '\'' +
                ", conference=" + conference +
                ", startHour=" + startHour +
                ", endHour=" + endHour +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speech speech = (Speech) o;
        return speakerId == speech.speakerId &&
                startHour == speech.startHour &&
                endHour == speech.endHour &&
                Objects.equals(topic, speech.topic) &&
                Objects.equals(suggestedTopic, speech.suggestedTopic) &&
                Objects.equals(conference, speech.conference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(speakerId, topic, suggestedTopic, conference, startHour, endHour);
    }

    public static class SpeechBuilder {
        private int speakerId;
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

        public SpeechBuilder withSpeakerId(int id) {
            this.speakerId = id;
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
            if (startHour < 24 && startHour > 0) {
                this.startHour = startHour;
                return this;

            } else {
                logger.error("setted StartHour " + startHour);
                throw new RuntimeException();
            }
        }

        public SpeechBuilder withEndHour(int endHour) {
            if (endHour < 24 && endHour > 0) {
                this.endHour = endHour;
                return this;

            } else {
                logger.error("setted endHour " + endHour);
                throw new RuntimeException();
            }
        }

        public Speech build() {
            return new Speech(this);
        }


    }

}
