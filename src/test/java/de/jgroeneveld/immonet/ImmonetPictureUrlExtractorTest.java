package de.jgroeneveld.immonet;

import de.jgroeneveld.common.PictureUrlExtractor;
import de.jgroeneveld.testutil.Resources;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertThat;

/**
 * Created by jgroeneveld on 28.11.14.
 */
public class ImmonetPictureUrlExtractorTest {
    PictureUrlExtractor pictureUrlExtractor = new ImmonetPictureUrlExtractor();

    @Test
    public void extract() throws IOException {
        String fixture = Resources.instance.readResource("/fixtures/immonet.html");
        Document doc = Jsoup.parse(fixture);
        List<String> urls = pictureUrlExtractor.extract(doc);

        assertThat(urls, IsIterableContainingInOrder.contains(
                "http://images.immonet.de/71/35/76/323713576.jpg",
                "http://images.immonet.de/71/35/78/323713578.jpg",
                "http://images.immonet.de/71/35/79/323713579.jpg",
                "http://images.immonet.de/71/35/80/323713580.jpg"
        ));
    }

}
