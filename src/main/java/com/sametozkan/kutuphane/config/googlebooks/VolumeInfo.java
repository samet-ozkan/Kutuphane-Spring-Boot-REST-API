package com.sametozkan.kutuphane.config.googlebooks;

import java.util.List;

public record VolumeInfo(String title, List<String> authors, String publishedDate, String description,
                         String language, Integer pageCount, List<String> categories){}
