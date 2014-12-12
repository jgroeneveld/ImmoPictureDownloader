package de.jgroeneveld.config;


import dagger.Module;
import dagger.Provides;
import de.jgroeneveld.DocumentFetcher;
import de.jgroeneveld.PictureUrlExtractorFactory;
import de.jgroeneveld.Presentation;

/**
 * Created by jgroeneveld on 05.12.14.
 */
@Module
public class ApplicationModule {
    @Provides
    Presentation presentation() {
        return new Presentation();
    }

    @Provides
    DocumentFetcher documentFetcher() {
        return new DocumentFetcher();
    }

    @Provides
    PictureUrlExtractorFactory pictureUrlExtractorFactory() {
        return new PictureUrlExtractorFactory();
    }
}
