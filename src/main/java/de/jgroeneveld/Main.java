package de.jgroeneveld;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.jgroeneveld.config.ApplicationModule;

public class Main {
    public static void main(String[] args) {
        AppArgs appArgs = new AppArgs(args);
//        appArgs.sourceUrl = "http://www.immonet.de/angebot/22740920?drop=sel";
        appArgs.sourceUrl = "http://www.immobilienscout24.de/expose/78028043?referrer=RESULT_LIST_LISTING&navigationServiceUrl=%2FSuche%2Fcontroller%2FexposeNavigation%2Fnavigate.go%3FsearchUrl%3D%2FSuche%2FS-T%2FWohnung-Miete%2FHamburg%2FHamburg%26exposeId%3D78028043&navigationHasPrev=false&navigationHasNext=true&navigationBarType=RESULT_LIST&searchId=f8d8ba26-1d98-3d43-b9bc-7ef8c1aaa0a2&resultListPosition=1";
        appArgs.targetFolder = "./temp";

        if (appArgs.hasErrors()) {
            System.err.println("Argument Error: " + appArgs.getErrorMessage());
            return;
        }

        Injector injector = Guice.createInjector(new ApplicationModule());
        Application application = injector.getInstance(Application.class);

        application.run(appArgs);
    }
}
