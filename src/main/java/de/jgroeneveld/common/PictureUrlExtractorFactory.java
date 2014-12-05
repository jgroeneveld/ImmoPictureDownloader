package de.jgroeneveld.common;

import de.jgroeneveld.AppArgs;
import de.jgroeneveld.immonet.ImmonetPictureUrlExtractor;

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
