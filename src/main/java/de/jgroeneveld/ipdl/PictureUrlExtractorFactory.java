package de.jgroeneveld.ipdl;

import de.jgroeneveld.ipdl.extractors.ImmobilienscoutPictureUrlExtractor;
import de.jgroeneveld.ipdl.extractors.ImmonetPictureUrlExtractor;

import javax.inject.Inject;

/**
 * Created by jgroeneveld on 05.12.14.
 */
public class PictureUrlExtractorFactory {
    @Inject
    public PictureUrlExtractorFactory() {
    }

    public PictureUrlExtractor build(AppArgs appArgs) {
        PictureUrlExtractor pictureUrlExtractor;
        if (appArgs.sourceUrl.contains("immonet")) {
            pictureUrlExtractor = new ImmonetPictureUrlExtractor();
        } else if (appArgs.sourceUrl.contains("immobilienscout")) {
            pictureUrlExtractor = new ImmobilienscoutPictureUrlExtractor();
        } else {
            return null;
        }
        return pictureUrlExtractor;
    }
}
