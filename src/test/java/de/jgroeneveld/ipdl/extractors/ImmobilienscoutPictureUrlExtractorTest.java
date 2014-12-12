package de.jgroeneveld.ipdl.extractors;

import de.jgroeneveld.ipdl.PictureUrlExtractor;
import de.jgroeneveld.ipdl.testutil.Resources;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertThat;

/**
 * Created by jgroeneveld on 11.12.14.
 */
public class ImmobilienscoutPictureUrlExtractorTest {
        PictureUrlExtractor pictureUrlExtractor = new ImmobilienscoutPictureUrlExtractor();

        @Test
        public void extract() throws IOException {
            String fixture = Resources.instance.readResource("/fixtures/immobilienscout.html");
            Document doc = Jsoup.parse(fixture);
            List<String> urls = pictureUrlExtractor.extract(doc);

            assertThat(urls, IsIterableContainingInOrder.contains(
                    "http://picture.immobilienscout24.de/pic/orig02/N/313/25/133/313025133-0.jpg",
                    "http://picture.immobilienscout24.de/pic/orig01/N/313/25/144/313025144-0.jpg",
                    "http://picture.immobilienscout24.de/pic/orig02/N/313/25/149/313025149-0.jpg",
                    "http://picture.immobilienscout24.de/pic/orig02/N/313/25/153/313025153-0.jpg",
                    "http://picture.immobilienscout24.de/pic/orig01/N/313/25/160/313025160-0.jpg",
                    "http://picture.immobilienscout24.de/pic/orig02/N/313/25/165/313025165-0.jpg",
                    "http://picture.immobilienscout24.de/pic/orig02/N/313/25/169/313025169-0.jpg",
                    "http://picture.immobilienscout24.de/pic/orig04/N/313/25/171/313025171-0.jpg",
                    "http://picture.immobilienscout24.de/pic/orig04/N/313/25/175/313025175-0.jpg",
                    "http://picture.immobilienscout24.de/pic/orig04/N/313/25/183/313025183-0.jpg",
                    "http://picture.immobilienscout24.de/pic/orig01/N/313/25/188/313025188-0.jpg",
                    "http://picture.immobilienscout24.de/pic/orig03/N/313/25/190/313025190-0.jpg",
                    "http://picture.immobilienscout24.de/pic/orig01/N/313/25/192/313025192-0.jpg",
                    "http://picture.immobilienscout24.de/pic/orig02/N/313/25/197/313025197-0.jpg"
            ));
        }
}
