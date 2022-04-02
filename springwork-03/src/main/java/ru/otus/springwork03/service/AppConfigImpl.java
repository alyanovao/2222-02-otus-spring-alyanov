package ru.otus.springwork03.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfigImpl implements LocalizationService, PathSourcesProvider {

    @Value("${localization.language}")
    private final String language;

    @Value("${file.path.englishLocate}")
    private final String filePathEn;

    @Value("${file.path.russianLocate}")
    private final String filePathRu;

    public AppConfigImpl(@Value("{localization.language}") String language,
                         @Value("${file.path.englishLocate}") String filePathEn,
                         @Value("${file.path.russianLocate}") String filePathRu) {
        this.language = language;
        this.filePathEn = filePathEn;
        this.filePathRu = filePathRu;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public String getPath() {
        return language.equals("ru") ? filePathRu : filePathEn;
    }
}
