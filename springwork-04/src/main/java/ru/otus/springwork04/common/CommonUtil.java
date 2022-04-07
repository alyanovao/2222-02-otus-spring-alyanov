package ru.otus.springwork04.common;

public final class CommonUtil {
    public static final String QUESTION_ID = "id";
    public static final String QUESTION = "question";
    public static final String RIGHT_ANSWER = "rightAnswer";
    public static final String MISSING_ANSWER = "missingAnswer";
    public static final String SEPARATOR = ",";

    //SolatLint Major java:S1118
    private CommonUtil() {
        throw new IllegalStateException("Utility class");
    }
}