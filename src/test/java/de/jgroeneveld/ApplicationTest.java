package de.jgroeneveld;

import de.jgroeneveld.testutil.Resources;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.net.URL;

public class ApplicationTest {

    PictureUrlExtractorFactory pictureUrlExtractorFactory = new PictureUrlExtractorFactory();

    @Mock
    PictureDownloader pictureDownloader;

    @Mock
    DocumentFetcher documentFetcher;

    @Mock
    Presentation presentation;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIntegrationWithPictureDownloader() throws IOException {
        Mockito.stub(documentFetcher.fetchDocument("http://immonet.de/stuff")).toReturn(fixtureDocument());
        Application application = new Application(presentation, documentFetcher, pictureUrlExtractorFactory, pictureDownloader);

        application.run(new AppArgs("http://immonet.de/stuff", "the_target_folder"));

        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://images.immonet.de/71/35/76/323713576.jpg"));
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://images.immonet.de/71/35/78/323713578.jpg"));
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://images.immonet.de/71/35/79/323713579.jpg"));
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://images.immonet.de/71/35/80/323713580.jpg"));
    }

    private Document fixtureDocument() {
        String fixture = Resources.instance.readResource("/fixtures/immonet.html");
        return Jsoup.parse(fixture);
    }
}