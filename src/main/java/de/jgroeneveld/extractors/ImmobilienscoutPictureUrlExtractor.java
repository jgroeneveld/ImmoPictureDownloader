package de.jgroeneveld.extractors;

import com.google.gson.Gson;
import de.jgroeneveld.PictureUrlExtractor;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jgroeneveld on 11.12.14.
 */
public class ImmobilienscoutPictureUrlExtractor implements PictureUrlExtractor {

    public static final Pattern GALLERY_DATA_PATTERN = Pattern.compile("^\\s*galleryData:(.*),", Pattern.MULTILINE);

    @Override
    public List<String> extract(Document document) {
        String script = document.head().select("script").get(0).html();
        String galleryDataString = getGalleryDataString(script);

        if(galleryDataString == null) {
            return new ArrayList<>();
        } else {
            return extractPictureUrlsFromGalleryDataString(galleryDataString);
        }
    }

    private String getGalleryDataString(String script) {
        Matcher matcher = GALLERY_DATA_PATTERN.matcher(script);
        if(matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    private List<String> extractPictureUrlsFromGalleryDataString(String galleryDataString) {
        Gson gson = new Gson();
        GalleryItem[] galleryItems = gson.fromJson(galleryDataString, GalleryItem[].class);

        List<String> urls = new ArrayList<>(galleryDataString.length());

        for(GalleryItem i : galleryItems) {
            urls.add(i.getOriginalPictureUrlClean());
        }

        return urls;
    }

    private static class GalleryItem {
        public String originalPictureUrl;

        // strips the version parameter from the url
        public String getOriginalPictureUrlClean() {
            return originalPictureUrl.replaceAll("\\?\\d+", "");
        }
    }

}
