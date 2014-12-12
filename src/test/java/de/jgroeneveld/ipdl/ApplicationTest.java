package de.jgroeneveld.ipdl;

import de.jgroeneveld.ipdl.testutil.Resources;
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
    public void testImmonetIntegration() throws IOException {
        Mockito.stub(documentFetcher.fetchDocument("http://immonet.de/stuff")).toReturn(immonetFixtureDocument());
        Application application = new Application(presentation, documentFetcher, pictureUrlExtractorFactory, pictureDownloader);

        application.run(new AppArgs("http://immonet.de/stuff", "the_target_folder"));

        Mockito.verify(documentFetcher).fetchDocument("http://immonet.de/stuff");
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://images.immonet.de/71/35/76/323713576.jpg"));
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://images.immonet.de/71/35/78/323713578.jpg"));
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://images.immonet.de/71/35/79/323713579.jpg"));
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://images.immonet.de/71/35/80/323713580.jpg"));
    }

    @Test
    public void testImmobilienscoutIntegration() throws IOException {
        Mockito.stub(documentFetcher.fetchDocument("http://immobilienscout24.de/stuff")).toReturn(immobilienscoutFixtureDocument());
        Application application = new Application(presentation, documentFetcher, pictureUrlExtractorFactory, pictureDownloader);

        application.run(new AppArgs("http://immobilienscout24.de/stuff", "the_target_folder"));

        Mockito.verify(documentFetcher).fetchDocument("http://immobilienscout24.de/stuff");
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://picture.immobilienscout24.de/pic/orig02/N/313/25/133/313025133-0.jpg"));
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://picture.immobilienscout24.de/pic/orig01/N/313/25/144/313025144-0.jpg"));
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://picture.immobilienscout24.de/pic/orig02/N/313/25/149/313025149-0.jpg"));
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://picture.immobilienscout24.de/pic/orig02/N/313/25/153/313025153-0.jpg"));
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://picture.immobilienscout24.de/pic/orig01/N/313/25/160/313025160-0.jpg"));
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://picture.immobilienscout24.de/pic/orig02/N/313/25/165/313025165-0.jpg"));
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://picture.immobilienscout24.de/pic/orig02/N/313/25/169/313025169-0.jpg"));
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://picture.immobilienscout24.de/pic/orig04/N/313/25/171/313025171-0.jpg"));
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://picture.immobilienscout24.de/pic/orig04/N/313/25/175/313025175-0.jpg"));
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://picture.immobilienscout24.de/pic/orig04/N/313/25/183/313025183-0.jpg"));
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://picture.immobilienscout24.de/pic/orig01/N/313/25/188/313025188-0.jpg"));
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://picture.immobilienscout24.de/pic/orig03/N/313/25/190/313025190-0.jpg"));
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://picture.immobilienscout24.de/pic/orig01/N/313/25/192/313025192-0.jpg"));
        Mockito.verify(pictureDownloader).download("the_target_folder", new URL("http://picture.immobilienscout24.de/pic/orig02/N/313/25/197/313025197-0.jpg"));
    }

    private Document immonetFixtureDocument() {
        String fixture = Resources.instance.readResource("/fixtures/immonet.html");
        return Jsoup.parse(fixture);
    }

    private Document immobilienscoutFixtureDocument() {
        String fixture = Resources.instance.readResource("/fixtures/immobilienscout.html");
        return Jsoup.parse(fixture);
    }
}