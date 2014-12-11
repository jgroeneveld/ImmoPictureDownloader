package de.jgroeneveld;

import de.jgroeneveld.extractors.ImmonetPictureUrlExtractor;

/**
 * Created by jgroeneveld on 05.12.14.
 */
public class PictureUrlExtractorFactory {
    public PictureUrlExtractor build(AppArgs appArgs) {
        PictureUrlExtractor pictureUrlExtractor;
        if (appArgs.sourceUrl.contains("immonet")) {
            pictureUrlExtractor = new ImmonetPictureUrlExtractor();
        } else {
            return null;
        }
        return pictureUrlExtractor;
    }
}
