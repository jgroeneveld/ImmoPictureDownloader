package de.jgroeneveld;

/**
 * Created by jgroeneveld on 02.12.14.
 */
public class AppArgs {
    public String sourceUrl;
    public String targetFolder = ".";

    public AppArgs(String[] args) {
        if (args.length >= 1) {
            sourceUrl = args[0];
        }

        if (args.length >= 2) {
            targetFolder = args[1];
        }
    }

    public boolean hasErrors() {
        return targetFolder == null || sourceUrl == null;
    }

    public String getErrorMessage() {
        String msg = "";

        if (sourceUrl == null) {
            msg += "sourceUrl is missing\n";
        }

        if (targetFolder == null) {
            msg += "targetFolder is missing\n";
        }

        if (msg != "") {
            msg += "Usage: java -jar ImmonetPictureDownloader.jar sourceUrl[targetFolder]";
        }

        return msg;
    }


}
