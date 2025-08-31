package com.orangehrm.utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtilities {

    static String projectpath = System.getProperty("user.dir");

    public static String Capture(WebDriver driver, String testName) throws IOException {
        // sanitize test name for file system
        String safeName = testName.replaceAll("[^a-zA-Z0-9-_]", "_");

        // timestamp (yyyyMMdd_HHmmss)
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        // Take screenshot
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Build folder path
        File dir = new File(projectpath + File.separator + "src"
                + File.separator + "test"
                + File.separator + "resources"
                + File.separator + "ScreenShots");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Build file path with unique name
        File dest = new File(dir, safeName + "_" + timestamp + ".png");

        FileUtils.copyFile(src, dest);

        return dest.getAbsolutePath(); // return full path for reports
    }
}
