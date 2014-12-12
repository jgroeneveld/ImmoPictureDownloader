package de.jgroeneveld.ipdl.config;


import dagger.Module;
import dagger.Provides;
import de.jgroeneveld.ipdl.DocumentFetcher;
import de.jgroeneveld.ipdl.PictureUrlExtractorFactory;
import de.jgroeneveld.ipdl.Presentation;

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
