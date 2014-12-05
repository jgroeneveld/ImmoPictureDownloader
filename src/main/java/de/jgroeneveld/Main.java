package de.jgroeneveld;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    public static void main(String[] args) {
        AppArgs appArgs = new AppArgs(args);
        appArgs.sourceUrl = "http://www.immonet.de/angebot/22740920?drop=sel";
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
