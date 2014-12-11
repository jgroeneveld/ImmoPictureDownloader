package de.jgroeneveld;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by jgroeneveld on 28.11.14.
 */
public interface PictureUrlExtractor {
    public List<String> extract(Document document);
}
